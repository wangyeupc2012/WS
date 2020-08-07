package application.ApiWs.service;

import application.AliPay.config.AlipayConfig;
import application.ApiWs.dao.ApiWsDao;
import application.ApiWs.vo.*;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import framework.util.ConstantUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiWsService {

    public static final String STORE_SALESMAN = "0";
    public static final String STORE_MANAGER = "1";
    public static final String FLAG_Y = "Y";

    @Resource
    private ApiWsDao apiWsDao;

    /**
     * 用户登陆验证
     * @param userLoginRequestVo
     * @return
     * @throws Exception
     */
    public boolean getUserLoginVerified(UserLoginRequestVo userLoginRequestVo) throws Exception {
        int cnt = this.apiWsDao.getUserVerifiedCnt(userLoginRequestVo);
        if (cnt == 0) {
            return false;
        } else if (cnt == 1) {
            return true;
        } else {
            throw new Exception("找到多个相同用户，请联系系统管理员！");
        }
    }

    /**
     * 用户登陆返回用户信息
     * @param userLoginRequestVo
     * @return
     * @throws Exception
     */
    public UserLoginResponseVo getUserLoginInfo(UserLoginRequestVo userLoginRequestVo) throws Exception {
        UserLoginResponseVo userLoginResponseVo = this.apiWsDao.getUserLoginInfo(userLoginRequestVo);
        userLoginResponseVo.setUserBelongs("三元及递商城");
        //获取业务员管理门店列表
        if (STORE_SALESMAN.equals(userLoginResponseVo.getUserType())) {
            List<StoreVo> storeVos = this.apiWsDao.getUserLoginStoreInfoList(userLoginResponseVo.getUserId());
            userLoginResponseVo.setStoreVoList(storeVos);
        } else {
            //获取门店店长管理门店
            List<StoreVo> storeVos = this.apiWsDao.getUserLoginStoreInfoListForManager(userLoginResponseVo.getUserId());
            userLoginResponseVo.setStoreVoList(storeVos);
        }
        return userLoginResponseVo;
    }

    /**
     * 判断用户是否已注册
     * @param mobile
     * @return
     * @throws Exception
     */
    public boolean getUserRegistedFlag(String mobile) throws Exception {
        int cnt = this.apiWsDao.getUserRegistedFlag(mobile);
        if (cnt == 1) {
            return true;
        } else if (cnt == 0) {
            return false;
        } else {
            throw new Exception("找到多个相同用户，请联系系统管理员！");
        }
    }

    /**
     * 用户密码更新&重置
     * @param userResetPasswordRequestVo
     * @return
     * @throws Exception
     */
    public UserResetPasswordResponseVo getUserPasswordReset(UserResetPasswordRequestVo userResetPasswordRequestVo) throws Exception {
        UserResetPasswordResponseVo userResetPasswordResponseVo = new UserResetPasswordResponseVo();
        this.apiWsDao.getUserPasswordReset(userResetPasswordRequestVo);
        UserVo userVo = this.getUserInfoByMobile(userResetPasswordRequestVo.getUserName());
        BeanUtils.copyProperties(userVo,userResetPasswordResponseVo);
        return userResetPasswordResponseVo;
    }

    /**
     * 获取用户信息
     * @param mobile
     * @return
     * @throws Exception
     */
    public UserVo getUserInfoByMobile(String mobile) throws Exception {
        return this.apiWsDao.getUserInfoByMobile(mobile);
    }

    /**
     * 获取商品列表
     * @param itemListRequestVo
     * @return
     * @throws Exception
     */
    public ItemListResponseVo getItemList(ItemListRequestVo itemListRequestVo) throws Exception {
        if (StringUtils.isNotBlank(itemListRequestVo.getItemName())) {
            this.hotSearchCounter(itemListRequestVo.getItemName());
        }
        ItemListResponseVo itemListResponseVo = new ItemListResponseVo();
        int totalCount = this.apiWsDao.getItemListCount(itemListRequestVo);
        int pageSize = Integer.parseInt(itemListRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        itemListResponseVo.setCurrentPage(itemListRequestVo.getPageNo());
        itemListResponseVo.setTotalCount(String.valueOf(totalCount));
        itemListResponseVo.setTotalPage(String.valueOf(totalPage));
        itemListResponseVo.setItemVoList(this.apiWsDao.getItemList(itemListRequestVo));
        return itemListResponseVo;
    }

    /**
     * 获取商品类型
     * @param
     * @return
     * @throws Exception
     */
    public List<ItemTypeListResponseVo> getItemTypeList() throws Exception {
        List<ItemTypeListResponseVo> itemTypeListResponseVos = this.apiWsDao.getItemTypeList();
        for (ItemTypeListResponseVo itemTypeListResponseVo : itemTypeListResponseVos) {
            List<ItemTypeVo> itemTypeVos = this.apiWsDao.getItemListByType(itemTypeListResponseVo.getItemTypeCode());
            for (ItemTypeVo itemTypeVo : itemTypeVos) {
                itemTypeVo.setItemSaleQuantity(String.valueOf(this.apiWsDao.getItemSaleQuantityById(itemTypeVo.getItemId())));
            }
            itemTypeListResponseVo.setItemTypeVoList(itemTypeVos);
        }
        return itemTypeListResponseVos;
    }

    /**
     * 获取商品详情页
     * @param itemDetailRequestVo
     * @return
     * @throws Exception
     */
    public ItemDetailResponseVo getItemDetail(ItemDetailRequestVo itemDetailRequestVo) throws Exception {
        ItemDetailResponseVo itemDetailResponseVo = new ItemDetailResponseVo();
        ItemVo itemVo = this.apiWsDao.getItemDetail(itemDetailRequestVo);
        Map map = new HashMap();
        map.put("itemId",itemDetailRequestVo.getItemId());
        map.put("userId",itemDetailRequestVo.getUserId());
        boolean isCollected = this.apiWsDao.getUserItemCollectedFlag(map) > 0;
        if (isCollected) {
            itemVo.setIsCollected("Y");
        } else {
            itemVo.setIsCollected("N");
        }
        List<ItemPromotionVo> itemPromotionVos = this.apiWsDao.getItemPromotionListByItemId(itemVo.getItemId());
        itemVo.setItemPromotionVoList(itemPromotionVos);
        List<String> carousels = new ArrayList<>(3);
        carousels.add(itemVo.getItemPic());
        carousels.add(itemVo.getItemPic());
        carousels.add(itemVo.getItemPic());
        itemVo.setCarousels(carousels);
        BeanUtils.copyProperties(itemVo,itemDetailResponseVo);
        return itemDetailResponseVo;
    }

    /**
     * 获取购物车列表
     * @param shoppingCartListRequestVo
     * @return
     * @throws Exception
     */
    public ShoppingCartListResponseVo getShippingCartList(ShoppingCartListRequestVo shoppingCartListRequestVo) throws Exception {
        ShoppingCartListResponseVo shoppingCartListResponseVo = new ShoppingCartListResponseVo();
        int totalCount = this.apiWsDao.getShippingCartListCount(shoppingCartListRequestVo);
        int pageSize = Integer.parseInt(shoppingCartListRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        shoppingCartListResponseVo.setCurrentPage(shoppingCartListRequestVo.getPageNo());
        shoppingCartListResponseVo.setTotalCount(String.valueOf(totalCount));
        shoppingCartListResponseVo.setTotalPage(String.valueOf(totalPage));
        List<ShoppingCartVo> shoppingCartVos = this.apiWsDao.getShippingCartList(shoppingCartListRequestVo);
        shoppingCartListResponseVo.setShoppingCartListResponseVoList(shoppingCartVos);
        return shoppingCartListResponseVo;
    }

    /**
     * 更新购物车
     * @return
     * @throws Exception
     */
    public ShoppingCartModifiedResponseVo getShoppingCartModified(ShoppingCartModifiedRequestVo shoppingCartModifiedRequestVo) throws Exception {
//        //更新购物车行
//        for (ShoppingCartItemVo shoppingCartItemVo : shoppingCartModifiedRequestVo.getShoppingCartItemVoList()) {
//            //根据ID判断行是否存在 选择做插入还是更新
//            boolean isExist = this.apiWsDao.getShippingCartItemExist(shoppingCartItemVo.getShoppingItemId()) > 0;
//            if (isExist) {
//                shoppingCartItemVo.setLastModifiedBy(shoppingCartModifiedRequestVo.getUserId());
//                if (ConstantUtil.INTEGER_ZERO == Integer.parseInt(shoppingCartItemVo.getItemQuantity())) {
//                    this.apiWsDao.getShoppingCartItemDelete(shoppingCartItemVo);
//                } else {
//                    this.apiWsDao.getShoppingCartItemModified(shoppingCartItemVo);
//                }
//            } else {
//                shoppingCartItemVo.setShoppingCartId(shoppingCartModifiedRequestVo.getShoppingCartId());
//                shoppingCartItemVo.setCreatedBy(shoppingCartModifiedRequestVo.getUserId());
//                this.apiWsDao.getShoppingCartItemInsert(shoppingCartItemVo);
//            }
//        }
        ShoppingCartModifiedResponseVo shoppingCartModifiedResponseVo = new ShoppingCartModifiedResponseVo();
        if (ConstantUtil.INTEGER_ZERO == Integer.parseInt(shoppingCartModifiedRequestVo.getItemQuantity())) {
            this.apiWsDao.getShoppingCartDelete(shoppingCartModifiedRequestVo.getShoppingCartId());
            return null;
        } else {
            this.apiWsDao.getShoppingCartModified(shoppingCartModifiedRequestVo);
            ShoppingCartVo shoppingCartVo = this.apiWsDao.getShoppingCartById(shoppingCartModifiedRequestVo.getShoppingCartId());
            shoppingCartModifiedResponseVo.setStoreId(shoppingCartVo.getStoreId());
            shoppingCartModifiedResponseVo.setItemId(shoppingCartVo.getItemId());
            shoppingCartModifiedResponseVo.setItemQuantity(shoppingCartVo.getItemQuantity());
            shoppingCartModifiedResponseVo.setShoppingCartId(shoppingCartVo.getShoppingCartId());
            return shoppingCartModifiedResponseVo;
        }
//        List<ShoppingCartItemVo> shoppingCartItemVos = this.apiWsDao.getShippingCartItemList(shoppingCartModifiedRequestVo.getShoppingCartId());
//        ShoppingCartVo shoppingCartVo = this.apiWsDao.getShoppingCartById(shoppingCartModifiedRequestVo.getShoppingCartId());
//        shoppingCartVo.setShoppingCartItemVoList(shoppingCartItemVos);
//        return shoppingCartVo;
    }

    /**
     * 新增购物车
     * @param shoppingCartAddRequestVo
     * @return
     * @throws Exception
     */
    public ShoppingCartAddResponseVo getShoppingCartAdd(ShoppingCartAddRequestVo shoppingCartAddRequestVo) throws Exception {
        ShoppingCartAddResponseVo shoppingCartAddResponseVo = new ShoppingCartAddResponseVo();
        boolean isExist = this.apiWsDao.getShippingCartExist(shoppingCartAddRequestVo) > 0;
        if (isExist) {
            //已存在该商品，则执行更新，quantity +
            this.apiWsDao.getShoppingCartUpdate(shoppingCartAddRequestVo);
        } else {
            this.apiWsDao.getShoppingCartInsert(shoppingCartAddRequestVo);
        }
        shoppingCartAddResponseVo.setShoppingCartId(shoppingCartAddRequestVo.getShoppingCartId());
        shoppingCartAddResponseVo.setItemId(shoppingCartAddRequestVo.getItemId());
        shoppingCartAddResponseVo.setStoreId(shoppingCartAddRequestVo.getStoreId());
        shoppingCartAddResponseVo.setItemQuantity(shoppingCartAddRequestVo.getItemQuantity());
        return shoppingCartAddResponseVo;


//        List<ShoppingCartItemVo> shoppingCartItemVos = shoppingCartAddRequestVo.getShoppingCartItemVoList();
//        if (shoppingCartItemVos.size() > 0) {
//            ShoppingCartAddResponseVo shoppingCartAddResponseVo = new ShoppingCartAddResponseVo();
//            //判断该新增的购物车是否已存在
//            boolean isExist = this.apiWsDao.getShippingCartExist(shoppingCartAddRequestVo) > 0;
//            if (isExist) {
//                shoppingCartAddRequestVo.setShoppingCartId(this.apiWsDao.getShoppingCartId(shoppingCartAddRequestVo));
//                for (ShoppingCartItemVo shoppingCartItemVo : shoppingCartItemVos) {
//                    shoppingCartItemVo.setShoppingCartId(shoppingCartAddRequestVo.getShoppingCartId());
//                    this.apiWsDao.getShoppingCartItemInsert(shoppingCartItemVo);
//                }
//            } else {
//                this.apiWsDao.getShoppingCartInsert(shoppingCartAddRequestVo);
//                if (StringUtils.isNotBlank(shoppingCartAddRequestVo.getShoppingCartId())) {
//                    for (ShoppingCartItemVo shoppingCartItemVo : shoppingCartItemVos) {
//                        shoppingCartItemVo.setShoppingCartId(shoppingCartAddRequestVo.getShoppingCartId());
//                        this.apiWsDao.getShoppingCartItemInsert(shoppingCartItemVo);
//                    }
//                } else {
//                    throw new Exception("新增货单头表失败，请联系接口管理员！");
//                }
//            }
//            List<ShoppingCartItemVo> shippingCartItemList = this.apiWsDao.getShippingCartItemList(shoppingCartAddRequestVo.getShoppingCartId());
//            ShoppingCartVo shoppingCartVo = this.apiWsDao.getShoppingCartById(shoppingCartAddRequestVo.getShoppingCartId());
//            shoppingCartVo.setShoppingCartItemVoList(shippingCartItemList);
//            BeanUtils.copyProperties(shoppingCartVo,shoppingCartAddResponseVo);
//            return shoppingCartAddResponseVo;
//        } else {
//            throw new Exception("货单行为空，不可新增");
//        }
    }

    /**
     * 确认购物车
     * @param shoppingCartConfirmRequestVo
     * @return
     * @throws Exception
     */
    public ShoppingCartConfirmResponseVo getShoppingCartConfirm(ShoppingCartConfirmRequestVo shoppingCartConfirmRequestVo) throws Exception {
        Map requestMap = new HashMap();
        //获取本次操作批次ID
        String batchId = this.apiWsDao.getBatchId();
        //先将List存储进一张临时表，然后程序包从临时表读取数据
        for (ShoppingCartIdVo shoppingCartId : shoppingCartConfirmRequestVo.getShoppingCartIds()) {
            Map map = new HashMap();
            map.put("batchId",batchId);
            map.put("shoppingCartId",shoppingCartId.getShoppingCartId());
            this.apiWsDao.insertIntoShoppingCartTemp(map);
        }
        requestMap.put("batchId",batchId);
        requestMap.put("userId",shoppingCartConfirmRequestVo.getUserId());
        requestMap.put("shoppingDate",shoppingCartConfirmRequestVo.getShoppingDate());
        requestMap.put("orderId","");
        requestMap.put("flag","");
        requestMap.put("msg","");
        this.apiWsDao.getShoppingCartConfirm(requestMap);
        if (FLAG_Y.equals(requestMap.get("flag"))) {
            if (!StringUtils.isBlank(requestMap.get("orderId").toString())) {
                OrderVo orderVo = this.apiWsDao.getOrderById(requestMap.get("orderId").toString());
                orderVo.setOrderItemVoList(this.apiWsDao.getOrderItemListByOrders(requestMap.get("orderId").toString()));
                ShoppingCartConfirmResponseVo shoppingCartConfirmResponseVo = new ShoppingCartConfirmResponseVo();
                BeanUtils.copyProperties(orderVo,shoppingCartConfirmResponseVo);
                return shoppingCartConfirmResponseVo;
            } else {
                throw new Exception("确认购物车出错：程序包返回值出错，请联系系统管理员！");
            }
        } else {
            throw new Exception(requestMap.get("msg").toString());
        }
    }

    /**
     * 获取用户我的信息
     * @param userInfoRequestVo
     * @return
     * @throws Exception
     */
    public UserInfoResponseVo getUserInfo(UserInfoRequestVo userInfoRequestVo) throws Exception {
        return this.apiWsDao.getUserInfo(userInfoRequestVo);
    }

    /**
     * 获取订单列表
     * @param orderListRequestVo
     * @return
     * @throws Exception
     */
    public OrderListResponseVo getOrderList(OrderListRequestVo orderListRequestVo) throws Exception {
        OrderListResponseVo orderListResponseVo = new OrderListResponseVo();
        int totalCount = this.apiWsDao.getOrderListCount(orderListRequestVo);
        int pageSize = Integer.parseInt(orderListRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        orderListResponseVo.setCurrentPage(orderListRequestVo.getPageNo());
        orderListResponseVo.setTotalCount(String.valueOf(totalCount));
        orderListResponseVo.setTotalPage(String.valueOf(totalPage));
        List<OrderVo> orderVos = this.apiWsDao.getOrderList(orderListRequestVo);
        for (OrderVo orderVo : orderVos) {
            orderVo.setOrderItemVoList(this.apiWsDao.getOrderItemListByOrders(orderVo.getOrderId()));
        }
        orderListResponseVo.setOrderVoList(orderVos);
        return orderListResponseVo;
    }

    /**
     * 续单
     * @return
     * @throws Exception
     */
    public OrderCopyResponseVo getOrderCopy(OrderCopyRequestVo orderCopyRequestVo) throws Exception {
        Map requestMap = new HashMap();
        requestMap.put("orderId",orderCopyRequestVo.getOrderId());
        requestMap.put("userId",orderCopyRequestVo.getUserId());
        requestMap.put("code","");
        requestMap.put("message","");
        this.apiWsDao.getOrderCopyByPkg(requestMap);
        if (FLAG_Y.equals(requestMap.get("code"))) {
            return null;
        } else {
            throw new Exception("续单出错：" + requestMap.get("message"));
        }
//        OrderCopyResponseVo orderCopyResponseVo = new OrderCopyResponseVo();
//        OrderVo orderVo = this.apiWsDao.getOrderById(orderCopyRequestVo.getOrderId());
//        List<OrderItemVo> orderItemVos = this.apiWsDao.getOrderItemListByOrders(orderCopyRequestVo.getOrderId());
//        ShoppingCartAddRequestVo shoppingCartAddRequestVo = new ShoppingCartAddRequestVo();
//        shoppingCartAddRequestVo.setCreatedBy(orderCopyRequestVo.getUserId());
//        shoppingCartAddRequestVo.setStoreId(orderVo.getStoreId());
//        shoppingCartAddRequestVo.setShoppingDate(orderCopyRequestVo.getShoppingDate());
//        this.apiWsDao.getShoppingCartInsert(shoppingCartAddRequestVo);
//        if (!StringUtils.isBlank(shoppingCartAddRequestVo.getShoppingCartId())) {
//            for (OrderItemVo orderItemVo : orderItemVos) {
//                ShoppingCartItemVo shoppingCartItemVo = new ShoppingCartItemVo();
//                shoppingCartItemVo.setShoppingCartId(shoppingCartAddRequestVo.getShoppingCartId());
//                shoppingCartItemVo.setCreatedBy(orderCopyRequestVo.getUserId());
//                shoppingCartItemVo.setItemId(orderItemVo.getItemId());
//                shoppingCartItemVo.setItemQuantity(orderItemVo.getItemQuantity());
//                shoppingCartItemVo.setItemSn(orderItemVo.getItemSn());
//                shoppingCartItemVo.setItemPrice(orderItemVo.getItemPrice());
//                this.apiWsDao.getShoppingCartItemInsert(shoppingCartItemVo);
//            }
//            ShoppingCartVo shoppingCartVo = this.apiWsDao.getShoppingCartById(shoppingCartAddRequestVo.getShoppingCartId());
//            List<ShoppingCartItemVo> shoppingCartItemVos = this.apiWsDao.getShippingCartItemList(shoppingCartVo.getShoppingCartId());
//            shoppingCartVo.setShoppingCartItemVoList(shoppingCartItemVos);
//            BeanUtils.copyProperties(shoppingCartVo,orderCopyResponseVo);
//            return orderCopyResponseVo;
//        } else {
//            throw new Exception("续单操作失败，请联系系统管理员！");
//        }
    }

    /**
     * 获取钱包流水
     * @param walletLogRequestVo
     * @return
     * @throws Exception
     */
    public WalletLogResponseVo getWalletLog(WalletLogRequestVo walletLogRequestVo) throws Exception {
        WalletLogResponseVo walletLogResponseVo = new WalletLogResponseVo();
        walletLogResponseVo.setUserId(walletLogRequestVo.getUserId());
        int totalCount = this.apiWsDao.getWalletLogCount(walletLogRequestVo);
        int pageSize = Integer.parseInt(walletLogRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        walletLogResponseVo.setCurrentPage(walletLogRequestVo.getPageNo());
        walletLogResponseVo.setTotalCount(String.valueOf(totalCount));
        walletLogResponseVo.setTotalPage(String.valueOf(totalPage));
        walletLogResponseVo.setWalletLogVoList(this.apiWsDao.getWalletLogList(walletLogRequestVo));
        return walletLogResponseVo;
    }

    /**
     * 获取推送消息
     * @param pushMessageListRequestVo
     * @return
     * @throws Exception
     */
    public PushMessageListResponseVo getPushMessage(PushMessageListRequestVo pushMessageListRequestVo) throws Exception {
        PushMessageListResponseVo pushMessageListResponseVo = new PushMessageListResponseVo();
        int totalCount = this.apiWsDao.getPushMessageCount();
        int pageSize = Integer.parseInt(pushMessageListRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        pushMessageListResponseVo.setCurrentPage(pushMessageListRequestVo.getPageNo());
        pushMessageListResponseVo.setTotalCount(String.valueOf(totalCount));
        pushMessageListResponseVo.setTotalPage(String.valueOf(totalPage));
        List<PushMessageVo> pushMessageVos = this.apiWsDao.getPushMessageList(pushMessageListRequestVo);
        pushMessageListResponseVo.setPushMessageVoList(pushMessageVos);
        return pushMessageListResponseVo;
    }

    /**
     * 获取推送消息详情
     * @param pushMessageDetailRequestVo
     * @return
     * @throws Exception
     */
    public PushMessageDetailResponseVo getPushMessageDetail(PushMessageDetailRequestVo pushMessageDetailRequestVo) throws Exception {
        PushMessageDetailResponseVo pushMessageDetailResponseVo = new PushMessageDetailResponseVo();
        PushMessageVo pushMessageVo = this.apiWsDao.getPushMessageDetail(pushMessageDetailRequestVo.getMessageId());
        //插入数据 使得本条数据 本用户显示已读
        this.apiWsDao.getPushMessageReaded(pushMessageDetailRequestVo);
        //获取上方轮播图
        pushMessageVo.setItemAdVoList(this.apiWsDao.getMessageAdListByMessageId(pushMessageVo.getMessageId()));
        BeanUtils.copyProperties(pushMessageVo,pushMessageDetailResponseVo);
        return pushMessageDetailResponseVo;
    }

    /**
     * 获取系统热搜词
     * @return
     * @throws Exception
     */
    public HotSearchListResponseVo getHotSearch() throws Exception {
        HotSearchListResponseVo hotSearchListResponseVo = new HotSearchListResponseVo();
        List<HotSearchVo> hotSearchVos = this.apiWsDao.getHotSearchList();
        hotSearchListResponseVo.setHotSearchVoList(hotSearchVos);
        return hotSearchListResponseVo;
    }

    /**
     * 热搜计数器
     * 判断该搜索是否已存在；已存在 count+1，不存在insert
     */
    public void hotSearchCounter(String content) throws Exception {
        int cnt = this.apiWsDao.getHotSearchCnt(content);
        if (ConstantUtil.INTEGER_ZERO == cnt) {
            this.apiWsDao.getHotSearchInsert(content);
        } else {
            this.apiWsDao.getHotSearchUpdate(content);
        }
    }

    /**
     * 获取热卖商品
     * @return
     * @throws Exception
     */
    public HotItemResponseVo getHotItemList() throws Exception {
        HotItemResponseVo hotItemResponseVo = new HotItemResponseVo();
        List<HotItemVo> hotItemVos = this.apiWsDao.getHotItemList();
        hotItemResponseVo.setHotItemVoList(hotItemVos);
        return hotItemResponseVo;
    }

    /**
     * 获取特卖商品
     * @return
     * @throws Exception
     */
    public SpecialItemResponseVo getSpecialItemList(SpecialItemRequestVo specialItemRequestVo) throws Exception {
        SpecialItemResponseVo specialItemResponseVo = new SpecialItemResponseVo();
        int totalCount = this.apiWsDao.getSpecialItemListCount();
        int pageSize = Integer.parseInt(specialItemRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        specialItemResponseVo.setCurrentPage(specialItemRequestVo.getPageNo());
        specialItemResponseVo.setTotalCount(String.valueOf(totalCount));
        specialItemResponseVo.setTotalPage(String.valueOf(totalPage));
        List<SpecialItemVo> specialItemVos = this.apiWsDao.getSpecialItemList(specialItemRequestVo);
        List<SpecialItemAdVo> specialItemAdVos = this.apiWsDao.getSpecialItemAdsList();
        specialItemResponseVo.setSpecialItemVoList(specialItemVos);
        specialItemResponseVo.setSpecialItemAdVoList(specialItemAdVos);
        return specialItemResponseVo;
    }

    /**
     * 获取促销商品
     * @return
     * @throws Exception
     */
    public PromotionItemResponseVo getPromotionItemList(PromotionItemRequestVo promotionItemRequestVo) throws Exception {
        PromotionItemResponseVo promotionItemResponseVo = new PromotionItemResponseVo();
        int totalCount = this.apiWsDao.getPromotionItemListCount();
        int pageSize = Integer.parseInt(promotionItemRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        promotionItemResponseVo.setCurrentPage(promotionItemRequestVo.getPageNo());
        promotionItemResponseVo.setTotalCount(String.valueOf(totalCount));
        promotionItemResponseVo.setTotalPage(String.valueOf(totalPage));
        List<PromotionItemVo> promotionItemVos = this.apiWsDao.getPromotionItemList(promotionItemRequestVo);
        promotionItemResponseVo.setPromotionItemVoList(promotionItemVos);
        return promotionItemResponseVo;
    }

    /**
     * 订单更新
     * @param orderUpdateRequestVo
     * @return
     * @throws Exception
     */
    public OrderUpdateResponseVo getOrderUpdate(OrderUpdateRequestVo orderUpdateRequestVo) throws Exception {
        //判断订单是否存在
        boolean isExist = this.apiWsDao.getOrderById(orderUpdateRequestVo.getOrderId()) != null;
        if (isExist) {
            OrderUpdateResponseVo orderUpdateResponseVo = new OrderUpdateResponseVo();
            this.apiWsDao.getOrderUpdate(orderUpdateRequestVo);
            //OrderVo orderVo = this.apiWsDao.getOrderById(orderUpdateRequestVo.getOrderId());
            orderUpdateResponseVo.setOrderId(orderUpdateRequestVo.getOrderId());
            orderUpdateResponseVo.setOrderStatus("5");
            return orderUpdateResponseVo;
        } else {
            throw new Exception("订单ID不存在");
        }
    }

    /**
     * 获取首页广告位
     * @return
     * @throws Exception
     */
    public HomePageListResponseVo getHomePageList() throws Exception {
        return this.apiWsDao.getHomePageList();
    }

    /**
     * 获取用户收藏商品LIst、
     * @param userCollectionRequestVo
     * @return
     * @throws Exception
     */
    public UserCollectionResponseVo getUserCollectionList(UserCollectionRequestVo userCollectionRequestVo) throws Exception {
        UserCollectionResponseVo userCollectionResponseVo = new UserCollectionResponseVo();
        int totalCount = this.apiWsDao.getUserCollectionListCount(userCollectionRequestVo);
        int pageSize = Integer.parseInt(userCollectionRequestVo.getPageSize());
        int totalPage = (int) Math.ceil(Double.valueOf(totalCount) / Double.valueOf(pageSize));
        userCollectionResponseVo.setCurrentPage(userCollectionRequestVo.getPageNo());
        userCollectionResponseVo.setTotalCount(String.valueOf(totalCount));
        userCollectionResponseVo.setTotalPage(String.valueOf(totalPage));
        userCollectionResponseVo.setCollectionVoList(this.apiWsDao.getUserCollectionList(userCollectionRequestVo));
        return userCollectionResponseVo;
    }

    /**
     * 用户新增商品收藏
     * @param collectionOperationRequestVo
     * @return
     * @throws Exception
     */
    public CollectionOperationResponseVo getUserCollectionAdd(CollectionOperationRequestVo collectionOperationRequestVo) throws Exception {
        CollectionOperationResponseVo collectionOperationResponseVo = new CollectionOperationResponseVo();
        Map map = new HashMap();
        map.put("itemId",collectionOperationRequestVo.getItemId());
        map.put("userId",collectionOperationRequestVo.getUserId());
        boolean flag = this.apiWsDao.getUserItemCollectedFlag(map) > 0;
        if (flag) {
            throw new Exception("您已经收藏过该商品啦～");
        } else {
            try {
                this.apiWsDao.getUserCollectionAdd(collectionOperationRequestVo);
            } catch (Exception e) {
                e.printStackTrace();
                collectionOperationResponseVo.setCode("1");
                collectionOperationResponseVo.setMessage(e.getMessage());
            }
            collectionOperationResponseVo.setCode("0");
            return collectionOperationResponseVo;
        }
    }

    /**
     * 用户更新商品收藏
     * @param collectionUpdateRequestVo
     * @return
     * @throws Exception
     */
    public CollectionOperationResponseVo getUserCollectionUpdate(CollectionUpdateRequestVo collectionUpdateRequestVo) throws Exception {
        CollectionOperationResponseVo collectionOperationResponseVo = new CollectionOperationResponseVo();
        try {
            this.apiWsDao.getUserCollectionUpdate(collectionUpdateRequestVo);
        } catch (Exception e) {
            e.printStackTrace();
            collectionOperationResponseVo.setCode("1");
            collectionOperationResponseVo.setMessage(e.getMessage());
        }
        collectionOperationResponseVo.setCode("0");
        return collectionOperationResponseVo;
    }

    /**
     * 支付宝接口
     * @param orderVo
     * @return
     * @throws Exception
     */
    public String getAliPay(OrderVo orderVo) throws Exception {
        String orderString = "";
        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                    AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            model.setBody("good milk");            //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setSubject("milk");         //商品名称
            model.setOutTradeNo("SN201811050900111122");      //商户订单号(自动生成)
            // model.setTimeoutExpress("30m");     //交易超时时间
            model.setTotalAmount("0.01");     //支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");      //销售产品码（固定值）
            ali_request.setBizModel(model);
//            logger.info("====================异步通知的地址为："+alipayment.getNotifyUrl());
            ali_request.setNotifyUrl(AlipayConfig.notify_url);    //异步回调地址（后台）
            ali_request.setReturnUrl(AlipayConfig.return_url);   //同步回调地址（APP）

            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)
            orderString = alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。
//            this.createAlipayMentOrder(alipaymentOrder);//创建新的商户支付宝订单
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.err.println("与支付宝交互出错，未能生成订单，请检查代码！");
        }
        return orderString;
    }

    /**
     * 支付宝异步请求逻辑处理
     * @param conversionParams
     * @return
     */
    public String notify(Map<String, String> conversionParams) throws IOException {
//        logger.info("==================支付宝异步请求逻辑处理");
        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;

        try {
            //调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);

        } catch (AlipayApiException e) {
//            logger.info("==================验签失败 ！");
            e.printStackTrace();
        }

        if (signVerified) {
            return "success";
        } else {
            return "success";
        }
        //对验签进行处理
//        if (signVerified) {
//            //验签通过
//            //获取需要保存的数据
//            String appId = conversionParams.get("app_id");//支付宝分配给开发者的应用Id
//            String notifyTime = conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
//            String gmtCreate = conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
//            String gmtPayment = conversionParams.get("gmt_payment");//交易付款时间
//            String gmtRefund = conversionParams.get("gmt_refund");//交易退款时间
//            String gmtClose = conversionParams.get("gmt_close");//交易结束时间
//            String tradeNo = conversionParams.get("trade_no");//支付宝的交易号
//            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
//            String outBizNo = conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
//            String buyerLogonId = conversionParams.get("buyer_logon_id");//买家支付宝账号
//            String sellerId = conversionParams.get("seller_id");//卖家支付宝用户号
//            String sellerEmail = conversionParams.get("seller_email");//卖家支付宝账号
//            String totalAmount = conversionParams.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
//            String receiptAmount = conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
//            String invoiceAmount = conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
//            String buyerPayAmount = conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
//            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态
//
//            //支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
//            AlipaymentOrder alipaymentOrder = this.selectByOutTradeNo(outTradeNo);
//
//            if (alipaymentOrder != null && totalAmount.equals(alipaymentOrder.getTotalAmount().toString()) && AlipayConfig.APPID.equals(appId)) {
//                //修改数据库支付宝订单表(因为要保存每次支付宝返回的信息到数据库里，以便以后查证)
//                alipaymentOrder.setNotifyTime(dateFormat(notifyTime));
//                alipaymentOrder.setGmtCreate(dateFormat(gmtCreate));
//                alipaymentOrder.setGmtPayment(dateFormat(gmtPayment));
//                alipaymentOrder.setGmtRefund(dateFormat(gmtRefund));
//                alipaymentOrder.setGmtClose(dateFormat(gmtClose));
//                alipaymentOrder.setTradeNo(tradeNo);
//                alipaymentOrder.setOutBizNo(outBizNo);
//                alipaymentOrder.setBuyerLogonId(buyerLogonId);
//                alipaymentOrder.setSellerId(sellerId);
//                alipaymentOrder.setSellerEmail(sellerEmail);
//                alipaymentOrder.setTotalAmount(Double.parseDouble(totalAmount));
//                alipaymentOrder.setReceiptAmount(Double.parseDouble(receiptAmount));
//                alipaymentOrder.setInvoiceAmount(Double.parseDouble(invoiceAmount));
//                alipaymentOrder.setBuyerPayAmount(Double.parseDouble(buyerPayAmount));
//                // 判断交易结果
//                switch (tradeStatus) {
//                    case "TRADE_FINISHED": // 交易结束并不可退款
//                        alipaymentOrder.setTradeStatus((byte) 3);
//                        break;
//                    case "TRADE_SUCCESS": // 交易支付成功
//                        alipaymentOrder.setTradeStatus((byte) 2);
//                        break;
//                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
//                        alipaymentOrder.setTradeStatus((byte) 1);
//                        break;
//                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
//                        alipaymentOrder.setTradeStatus((byte) 0);
//                        break;
//                    default:
//                        break;
//                }
//                int returnResult = this.updateByPrimaryKey(alipaymentOrder);  //更新交易表中状态
//
//                if (tradeStatus.equals("TRADE_SUCCESS")) {  //只处理支付成功的订单: 修改交易表状态,支付成功
//
//                    if (returnResult > 0) {
//                        return "success";
//                    } else {
//                        return "fail";
//                    }
//                } else {
//                    return "fail";
//                }
//
//            } else {
////                logger.info("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
//                return "fail";
//            }
//
//        } else { //验签不通过
////            logger.info("==================验签不通过 ！");
//            return "fail";
//        }

    }

    /**
     * 向支付宝发起订单查询请求
     * @param
     * @return
     * @throws IOException
     */
//    @Override
    public Byte checkAlipay(String outTradeNo) {
//        logger.info("==================向支付宝发起查询，查询商户订单号为："+outTradeNo);

        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                    AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
            alipayTradeQueryRequest.setBizContent("{" +
                    "\"out_trade_no\":\""+outTradeNo+"\"" +
                    "}");
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            if(alipayTradeQueryResponse.isSuccess()){
//
//                AlipaymentOrder alipaymentOrder=this.selectByOutTradeNo(outTradeNo);
//                //修改数据库支付宝订单表
//                alipaymentOrder.setTradeNo(alipayTradeQueryResponse.getTradeNo());
//                alipaymentOrder.setBuyerLogonId(alipayTradeQueryResponse.getBuyerLogonId());
//                alipaymentOrder.setTotalAmount(Double.parseDouble(alipayTradeQueryResponse.getTotalAmount()));
//                alipaymentOrder.setReceiptAmount(Double.parseDouble(alipayTradeQueryResponse.getReceiptAmount()));
//                alipaymentOrder.setInvoiceAmount(Double.parseDouble(alipayTradeQueryResponse.getInvoiceAmount()));
//                alipaymentOrder.setBuyerPayAmount(Double.parseDouble(alipayTradeQueryResponse.getBuyerPayAmount()));
//                switch (alipayTradeQueryResponse.getTradeStatus()) // 判断交易结果
//                {
//                    case "TRADE_FINISHED": // 交易结束并不可退款
//                        alipaymentOrder.setTradeStatus((byte) 3);
//                        break;
//                    case "TRADE_SUCCESS": // 交易支付成功
//                        alipaymentOrder.setTradeStatus((byte) 2);
//                        break;
//                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
//                        alipaymentOrder.setTradeStatus((byte) 1);
//                        break;
//                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
//                        alipaymentOrder.setTradeStatus((byte) 0);
//                        break;
//                    default:
//                        break;
//                }
//                this.updateByPrimaryKey(alipaymentOrder); //更新表记录
//                return alipaymentOrder.getTradeStatus();
                return 0;
            } else {
//                logger.info("==================调用支付宝查询接口失败！");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
