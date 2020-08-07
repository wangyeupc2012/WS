package application.ApiWs.controller;

import application.AliPay.config.AlipayAPIClientFactory;
import application.AliPay.config.AlipayConfig;
import application.ApiWs.service.ApiWsService;
import application.ApiWs.util.AppApiUtil;
import application.ApiWs.util.SmsCodeUtil;
import application.ApiWs.util.verifiedCodeUtil.SCaptcha;
import application.ApiWs.vo.*;
import application.ApiWs.vo.api.ApiRequest;
import application.ApiWs.vo.api.ApiResponse;
import cn.jiguang.common.resp.BaseResult;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import framework.util.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RestController
@Api(description = "三元及递APPWS对外服务接口")
@RequestMapping(value = "/apiws", method = RequestMethod.POST)
public class ApiWsController {

    @Resource
    private ApiWsService apiWsService;

    @ApiOperation(value = "服务接口入口", httpMethod = "POST", response = String.class, notes = "服务接口入口")
    @RequestMapping(value = "/service", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse serviceEntity(@RequestBody @ApiParam(name = "ApiRequest",value = "ApiRequest",required = true) ApiRequest apiRequest) {
        ApiRequest.MethodEnum methodEnum = apiRequest.getMethodIndex();
        ApiResponse apiResponse = new ApiResponse();
        Object responseData = null;
        if (AppApiUtil.authenticate(apiRequest)) {
            try {
                switch (methodEnum) {
                    case USER_LOGIN:
                        responseData = this.userLogin(apiRequest.getContent());
                        break;
                    case VERIFIED_CODE_SEND:
                        responseData = this.getSmsVerifiedCode(apiRequest.getContent());
                        break;
                    case USER_RESET_PASSWORD:
                        responseData = this.getUserPasswordReset(apiRequest.getContent());
                        break;
                    case ITEM_LIST:
                        responseData = this.getItemList(apiRequest.getContent());
                        break;
                    case ITEM_TYPE:
                        responseData = this.getItemTypeList(apiRequest.getContent());
                        break;
                    case SHOPPINGCART_LIST:
                        responseData = this.getShippingCartList(apiRequest.getContent());
                        break;
                    case ITEM_DETAIL:
                        responseData = this.getItemDetail(apiRequest.getContent());
                        break;
                    case SHOPPINGCART_MODIFIED:
                        responseData = this.getShoppingCartModified(apiRequest.getContent());
                        break;
                    case SHOPPINGCART_ADD:
                        responseData = this.getShoppingCartAdd(apiRequest.getContent());
                        break;
                    case SHOPPINGCART_CONFIRM:
                        responseData = this.getShoppingCartConfirm(apiRequest.getContent());
                        break;
                    case ORDER_LIST:
                        responseData = this.getOrderList(apiRequest.getContent());
                        break;
                    case ORDER_COPY:
                        responseData = this.getOrderCopy(apiRequest.getContent());
                        break;
                    case WALLET_LOGLIST:
                        responseData = this.getWalletLog(apiRequest.getContent());
                        break;
                    case PUSHMESSAGE_LIST:
                        responseData = this.getPushMessage(apiRequest.getContent());
                        break;
                    case PUSHMESSAGE_DETAIL:
                        responseData = this.getPushMessageDetail(apiRequest.getContent());
                        break;
                    case USER_INFO:
                        responseData = this.getUserInfo(apiRequest.getContent());
                        break;
                    case HOTSEARCH_LIST:
                        responseData = this.getHotSearch();
                        break;
                    case ITEM_HOT:
                        responseData = this.getHotItemList();
                        break;
                    case ITEM_SPECIAL:
                        responseData = this.getSpecialItemList(apiRequest.getContent());
                        break;
                    case ITEM_PROMOTION:
                        responseData = this.getPromotionItemList(apiRequest.getContent());
                        break;
                    case ORDER_UPDATE:
                        responseData = this.getOrderUpdate(apiRequest.getContent());
                        break;
                    case GET_VERIFIEDCODE:
                        responseData = this.getVerification();
                        break;
                    case HOMEPAGE_LIST:
                        responseData = this.getHomePageList();
                        break;
                    case COLLECTION_LIST:
                        responseData = this.getUserCollectionList(apiRequest.getContent());
                        break;
                    case COLLECTION_ADD:
                        responseData = this.getUserCollectionAdd(apiRequest.getContent());
                        break;
                    case COLLECTION_UPDATE:
                        responseData = this.getUserCollectionUpdate(apiRequest.getContent());
                        break;
                    case ALIPAY:
                        responseData = this.aliPay(apiRequest.getContent());
                        break;
                    case ERROR_METHOD:
                        responseData = null;
                        break;
                    default:
                        apiResponse.setCode(ApiResponse.CodeEnum.FAILURE);
                        apiResponse.setMessage("不存在此接口" + apiRequest.getMethod());
                        apiResponse.setData("");
                        return apiResponse;
                }
            } catch (Exception e) {
                apiResponse.setCode(ApiResponse.CodeEnum.FAILURE);
                apiResponse.setMessage(e.getMessage());
                return apiResponse;
            }
            apiResponse.setCode(ApiResponse.CodeEnum.SUCCESS);
            apiResponse.setMessage("接口调用成功！");
        } else {
            apiResponse.setCode(ApiResponse.CodeEnum.FAILURE);
            apiResponse.setMessage("接口调用认证失败！");
        }
        apiResponse.setData(responseData);
        return apiResponse;
    }

    /**
     * 用户登陆
     * @param content
     * @return
     * @throws Exception
     */
    public UserLoginResponseVo userLogin(String content) throws Exception{
        UserLoginRequestVo userLoginRequestVo = (UserLoginRequestVo)JsonUtil.getObjectFromJSON(content, UserLoginRequestVo.class);
        if (StringUtils.isBlank(userLoginRequestVo.getLoginUsername())) {
            throw new Exception("登陆名不允许为空！");
        }
        if (StringUtils.isBlank(userLoginRequestVo.getEncodedPassword())) {
            throw new Exception("密码不允许为空！");
        }
        boolean isVerified = this.apiWsService.getUserLoginVerified(userLoginRequestVo);
        if (isVerified) {
            return this.apiWsService.getUserLoginInfo(userLoginRequestVo);
        } else {
            throw new Exception("用户名或密码错误，请重新输入！");
        }
    }

    /**
     * 手机验证码发送
     * @param content
     * @return
     * @throws Exception
     */
    public VerifiedCodeResponseVo getSmsVerifiedCode(String content) throws Exception {
        VerifiedCodeResponseVo verifiedCodeResponseVo = new VerifiedCodeResponseVo();
        VerifiedCodeRequestVo verifiedCodeRequestVo = (VerifiedCodeRequestVo)JsonUtil.getObjectFromJSON(content,VerifiedCodeRequestVo.class);
        //判断手机号是否已注册
        //boolean isRegisted = this.apiWsService.getUserRegistedFlag(verifiedCodeRequestVo.getMobile());
        if (true) {
            Map resultMap = SmsCodeUtil.getMobileCheckNum(verifiedCodeRequestVo.getMobile());
            verifiedCodeResponseVo.setMobile(verifiedCodeRequestVo.getMobile());
            verifiedCodeResponseVo.setCode(JsonUtil.getMapFromJSON(resultMap.get("resultJson").toString()).get("code").toString());
            verifiedCodeResponseVo.setStatus("Y");
        } else {
            throw new Exception("手机号未注册！");
        }
        return verifiedCodeResponseVo;
    }

    /**
     * 用户密码更新&重置
     * @param content
     * @return
     * @throws Exception
     */
    public UserResetPasswordResponseVo getUserPasswordReset(String content) throws Exception {
        UserResetPasswordRequestVo userResetPasswordRequestVo = (UserResetPasswordRequestVo)JsonUtil.getObjectFromJSON(content,UserResetPasswordRequestVo.class);
        //更新
        if ("update".equals(userResetPasswordRequestVo.getUpdateType())) {
            if (!StringUtils.isBlank(userResetPasswordRequestVo.getUserName())) {
                boolean validFlag = this.apiWsService.getUserRegistedFlag(userResetPasswordRequestVo.getUserName());
                if (validFlag) {
                    UserVo userVo = this.apiWsService.getUserInfoByMobile(userResetPasswordRequestVo.getUserName());
                    if (userResetPasswordRequestVo.getOldEncodedPassword().equals(userVo.getEncodedPassword())) {
                        return this.apiWsService.getUserPasswordReset(userResetPasswordRequestVo);
                    } else {
                        throw new Exception("用户登录名或者密码错误！");
                    }
                } else {
                    throw new Exception("用户未注册或找到多个用户，请联系系统管理员！");
                }
            } else {
                throw new Exception("用户登录名不能为空！");
            }
        } else {
            userResetPasswordRequestVo.setNewEncodedPassword(userResetPasswordRequestVo.getNewEncodedPassword());
            return this.apiWsService.getUserPasswordReset(userResetPasswordRequestVo);
        }
    }

    /**
     * 获取商品列表List
     * @param content
     * @return
     * @throws Exception
     */
    public ItemListResponseVo getItemList(String content) throws Exception {
        ItemListRequestVo itemListRequestVo = (ItemListRequestVo)JsonUtil.getObjectFromJSON(content,ItemListRequestVo.class);
        if (StringUtils.isBlank(itemListRequestVo.getPageSize()) || Integer.parseInt(itemListRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            itemListRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(itemListRequestVo.getPageNo())) {
            itemListRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getItemList(itemListRequestVo);
    }

    /**
     *获取商品类型List
     * @return
     * @throws Exception
     */
    public List<ItemTypeListResponseVo> getItemTypeList(String content) throws Exception {
        //ItemTypeListRequestVo itemTypeListRequestVo = (ItemTypeListRequestVo)JsonUtil.getObjectFromJSON(content,ItemTypeListRequestVo.class);
        return this.apiWsService.getItemTypeList();
    }

    /**
     * 获取商品详情页
     * @param content
     * @return
     * @throws Exception
     */
    public ItemDetailResponseVo getItemDetail(String content) throws Exception {
        ItemDetailRequestVo itemDetailRequestVo = (ItemDetailRequestVo)JsonUtil.getObjectFromJSON(content,ItemDetailRequestVo.class);
        if (StringUtils.isBlank(itemDetailRequestVo.getItemId())) {
            throw new Exception("itemId不能为空");
        }
        if (StringUtils.isBlank(itemDetailRequestVo.getUserId())) {
            throw new Exception("userId不能为空");
        }
        return this.apiWsService.getItemDetail(itemDetailRequestVo);
    }

    /**
     * 获取购物车列表
     * @return
     * @throws Exception
     */
    public ShoppingCartListResponseVo getShippingCartList(String content) throws Exception {
        ShoppingCartListRequestVo shoppingCartListRequestVo = (ShoppingCartListRequestVo)JsonUtil.getObjectFromJSON(content, ShoppingCartListRequestVo.class);
        if (StringUtils.isBlank(shoppingCartListRequestVo.getPageSize()) || Integer.parseInt(shoppingCartListRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            shoppingCartListRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(shoppingCartListRequestVo.getPageNo())) {
            shoppingCartListRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        if (StringUtils.isBlank(shoppingCartListRequestVo.getStoreId())) {
            throw new Exception("购物车对应的门店ID不能为空！");
        }
        return this.apiWsService.getShippingCartList(shoppingCartListRequestVo);
    }

    /**
     * 更新购物车
     * @param content
     * @return
     * @throws Exception
     */
    public ShoppingCartModifiedResponseVo getShoppingCartModified(String content) throws Exception {
//        Map<String, Class<ShoppingCartItemVo>> map = new HashMap<String, Class<ShoppingCartItemVo>>();
//        map.put("shoppingCartItemVoList", ShoppingCartItemVo.class);
//        JSONObject jsonObject = JSONObject.fromObject(content);
//        ShoppingCartModifiedRequestVo shoppingCartModifiedRequestVo = (ShoppingCartModifiedRequestVo) JSONObject.toBean(jsonObject,ShoppingCartModifiedRequestVo.class,map);
        ShoppingCartModifiedRequestVo shoppingCartModifiedRequestVo = (ShoppingCartModifiedRequestVo) JsonUtil.getObjectFromJSON(content,ShoppingCartModifiedRequestVo.class);
        if (StringUtils.isBlank(shoppingCartModifiedRequestVo.getShoppingCartId())) {
            throw new Exception("购物车ID不能为空");
        }
        if (StringUtils.isBlank(shoppingCartModifiedRequestVo.getUserId())) {
            throw new Exception("userId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartModifiedRequestVo.getItemQuantity())) {
            throw new Exception("itemQuantity不能为空");
        }
        return this.apiWsService.getShoppingCartModified(shoppingCartModifiedRequestVo);
    }

    /**
     * 新增购物车
     * @param content
     * @return
     * @throws Exception
     */
    public ShoppingCartAddResponseVo getShoppingCartAdd(String content) throws Exception {
        //以下注释部分暂时不删除，原因是记录以下这种接收List集合转换为VO实体类的方法
//        Map<String, Class<ShoppingCartItemVo>> map = new HashMap<String, Class<ShoppingCartItemVo>>();
//        map.put("shoppingCartItemVoList", ShoppingCartItemVo.class);
//        JSONObject jsonObject = JSONObject.fromObject(content);
//        ShoppingCartAddRequestVo shoppingCartAddRequestVo = (ShoppingCartAddRequestVo)JSONObject.toBean(jsonObject,ShoppingCartAddRequestVo.class,map);
        ShoppingCartAddRequestVo shoppingCartAddRequestVo = (ShoppingCartAddRequestVo)JsonUtil.getObjectFromJSON(content,ShoppingCartAddRequestVo.class);
        if (StringUtils.isBlank(shoppingCartAddRequestVo.getStoreId())) {
            throw new Exception("storeId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartAddRequestVo.getUserId())) {
            throw new Exception("userId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartAddRequestVo.getItemId())) {
            throw new Exception("itemId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartAddRequestVo.getItemQuantity())) {
            throw new Exception("itemQuantity不能为空");
        }
        return this.apiWsService.getShoppingCartAdd(shoppingCartAddRequestVo);
    }

    /**
     * 确认购物车（生成未付款订单）
     *
     * 购物车确认后，购物车清空（实质是将状态更改为S，和删除不同），生成一张未付款的订单，正式进入CRM订单管理系统，本接口返回确认后的未付款订单信息
     * 订单处于未付款状态超过一定时间后（前台做控制，30分钟内付款），需要调用订单修改接口将订单状态改为后台关闭（超时关闭）
     * @param content
     * @return
     * @throws Exception
     */
    public ShoppingCartConfirmResponseVo getShoppingCartConfirm(String content) throws Exception {
        Map<String, Class<ShoppingCartIdVo>> map = new HashMap<String, Class<ShoppingCartIdVo>>();
        map.put("shoppingCartIds", ShoppingCartIdVo.class);
        JSONObject jsonObject = JSONObject.fromObject(content);
        ShoppingCartConfirmRequestVo shoppingCartConfirmRequestVo = (ShoppingCartConfirmRequestVo)JSONObject.toBean(jsonObject,ShoppingCartConfirmRequestVo.class,map);
//        ShoppingCartConfirmRequestVo shoppingCartConfirmRequestVo = (ShoppingCartConfirmRequestVo)JsonUtil.getObjectFromJSON(content,ShoppingCartConfirmRequestVo.class);
        if (shoppingCartConfirmRequestVo.getShoppingCartIds().size() == 0) {
            throw new Exception("ShoppingCartId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartConfirmRequestVo.getUserId())) {
            throw new Exception("userId不能为空");
        }
        if (StringUtils.isBlank(shoppingCartConfirmRequestVo.getShoppingDate())) {
            throw new Exception("配送日期不能为空");
        }
        return this.apiWsService.getShoppingCartConfirm(shoppingCartConfirmRequestVo);
    }

    /**
     * 获取用户信息
     * @param content
     * @return
     * @throws Exception
     */
    public UserInfoResponseVo getUserInfo(String content) throws Exception {
        UserInfoRequestVo userInfoRequestVo = (UserInfoRequestVo)JsonUtil.getObjectFromJSON(content,UserInfoRequestVo.class);
        if (StringUtils.isBlank(userInfoRequestVo.getUserId())) {
            throw new Exception("userId不能为空");
        }
        return this.apiWsService.getUserInfo(userInfoRequestVo);
    }

    /**
     * 获取订单列表
     * @param content
     * @return
     * @throws Exception
     */
    public OrderListResponseVo getOrderList(String content) throws Exception {
        OrderListRequestVo orderListRequestVo = (OrderListRequestVo)JsonUtil.getObjectFromJSON(content,OrderListRequestVo.class);
        if (StringUtils.isBlank(orderListRequestVo.getPageSize()) || Integer.parseInt(orderListRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            orderListRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(orderListRequestVo.getPageNo())) {
            orderListRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getOrderList(orderListRequestVo);
    }

    /**
     * 续单
     * @return
     * @throws Exception
     */
    public OrderCopyResponseVo getOrderCopy(String content) throws Exception {
        OrderCopyRequestVo orderCopyRequestVo = (OrderCopyRequestVo) JsonUtil.getObjectFromJSON(content,OrderCopyRequestVo.class);
        if (StringUtils.isBlank(orderCopyRequestVo.getOrderId())) {
            throw new Exception("订单ID不可为空");
        }
//        if (StringUtils.isBlank(orderCopyRequestVo.getShoppingDate())) {
//            throw new Exception("购货日期不可为空");
//        }
        if (StringUtils.isBlank(orderCopyRequestVo.getUserId())) {
            throw new Exception("用户ID不可为空");
        }
        return this.apiWsService.getOrderCopy(orderCopyRequestVo);
    }

    /**
     * 获取钱包流水
     * @param content
     * @return
     * @throws Exception
     */
    public WalletLogResponseVo getWalletLog(String content) throws Exception {
        WalletLogRequestVo walletLogRequestVo = (WalletLogRequestVo)JsonUtil.getObjectFromJSON(content,WalletLogRequestVo.class);
        if (StringUtils.isBlank(walletLogRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空！");
        }
        if (StringUtils.isBlank(walletLogRequestVo.getStartDate())) {
            throw new Exception("起始日期不能为空！");
        }
        if (StringUtils.isBlank(walletLogRequestVo.getEndDate())) {
            throw new Exception("终止日期不能为空");
        }
        if (StringUtils.isBlank(walletLogRequestVo.getPageSize()) || Integer.parseInt(walletLogRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            walletLogRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(walletLogRequestVo.getPageNo())) {
            walletLogRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getWalletLog(walletLogRequestVo);
    }

    /**
     * 获取推送消息
     * @param content
     * @return
     * @throws Exception
     */
    public PushMessageListResponseVo getPushMessage(String content) throws Exception {
        PushMessageListRequestVo pushMessageListRequestVo = (PushMessageListRequestVo)JsonUtil.getObjectFromJSON(content,PushMessageListRequestVo.class);
        if (StringUtils.isBlank(pushMessageListRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空");
        }
        if (StringUtils.isBlank(pushMessageListRequestVo.getPageSize()) || Integer.parseInt(pushMessageListRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            pushMessageListRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(pushMessageListRequestVo.getPageNo())) {
            pushMessageListRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getPushMessage(pushMessageListRequestVo);
    }

    /**
     * 获取推送消息详情
     * @param content
     * @return
     * @throws Exception
     */
    public PushMessageDetailResponseVo getPushMessageDetail(String content) throws Exception {
        PushMessageDetailRequestVo pushMessageDetailRequestVo = (PushMessageDetailRequestVo)JsonUtil.getObjectFromJSON(content,PushMessageDetailRequestVo.class);
        if (StringUtils.isBlank(pushMessageDetailRequestVo.getMessageId())) {
            throw new Exception("消息ID不能为空");
        }
        if (StringUtils.isBlank(pushMessageDetailRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空");
        }
        return this.apiWsService.getPushMessageDetail(pushMessageDetailRequestVo);
    }

    /**
     * 获取系统热搜词
     * @return
     * @throws Exception
     */
    public HotSearchListResponseVo getHotSearch() throws Exception {
        return this.apiWsService.getHotSearch();
    }

    /**
     * 获取热卖商品 本周前十
     * @return
     * @throws Exception
     */
    public HotItemResponseVo getHotItemList() throws Exception {
        return this.apiWsService.getHotItemList();
    }

    /**
     * 获取特卖商品
     * @return
     * @throws Exception
     */
    public SpecialItemResponseVo getSpecialItemList(String content) throws Exception {
        SpecialItemRequestVo specialItemRequestVo = (SpecialItemRequestVo)JsonUtil.getObjectFromJSON(content,SpecialItemRequestVo.class);
        if (StringUtils.isBlank(specialItemRequestVo.getPageSize()) || Integer.parseInt(specialItemRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            specialItemRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(specialItemRequestVo.getPageNo())) {
            specialItemRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getSpecialItemList(specialItemRequestVo);
    }

    /**
     * 获取促销商品
     * @return
     * @throws Exception
     */
    public PromotionItemResponseVo getPromotionItemList(String content) throws Exception {
        PromotionItemRequestVo promotionItemRequestVo = (PromotionItemRequestVo)JsonUtil.getObjectFromJSON(content,PromotionItemRequestVo.class);
        if (StringUtils.isBlank(promotionItemRequestVo.getPageSize()) || Integer.parseInt(promotionItemRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            promotionItemRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(promotionItemRequestVo.getPageNo())) {
            promotionItemRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getPromotionItemList(promotionItemRequestVo);
    }

    /**
     * 订单更新
     * @param content
     * @return
     * @throws Exception
     */
    public OrderUpdateResponseVo getOrderUpdate(String content) throws Exception {
        OrderUpdateRequestVo orderUpdateRequestVo = (OrderUpdateRequestVo)JsonUtil.getObjectFromJSON(content,OrderUpdateRequestVo.class);
        if (StringUtils.isBlank(orderUpdateRequestVo.getOrderId())) {
            throw new Exception("订单ID不能为空");
        }
        if (StringUtils.isBlank(orderUpdateRequestVo.getUserId())) {
            throw new Exception("userID不能为空");
        }
        return this.apiWsService.getOrderUpdate(orderUpdateRequestVo);
    }

    /**
     * 获取图形验证码接口
     *
     * @throws IOException
     */
    public VerificationResponseVo getVerification() throws IOException {
        VerificationResponseVo verificationResponseVo = new VerificationResponseVo();
//        // 设置响应的类型格式为图片格式
//        response.setContentType("image/jpeg");
//        // 禁止图像缓存。
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
        //实例生成验证码对象
        SCaptcha instance = new SCaptcha();
        //将验证码存入session
//        session.setAttribute("verification", instance.getCode());
        //向页面输出验证码图片
        BufferedImage bufferedImage = instance.write();
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",baos);
//        instance.write(response.getOutputStream());
        verificationResponseVo.setCode(instance.getCode());
        verificationResponseVo.setBase64Code(encoder.encode(baos.toByteArray()).trim().replaceAll("\n","").replaceAll("\r",""));
//        return encoder.encode(baos.toByteArray()).trim().replaceAll("\n","").replaceAll("\r","");
//        instance.write(response.getOutputStream());
        return verificationResponseVo;
    }

    /**
     * 获取首页广告位图片
     * @return
     * @throws Exception
     */
    public HomePageListResponseVo getHomePageList() throws Exception {
        return this.apiWsService.getHomePageList();
    }

    /**
     * 获取用户收藏商品
     * @param content
     * @return
     * @throws Exception
     */
    public UserCollectionResponseVo getUserCollectionList(String content) throws Exception {
        UserCollectionRequestVo userCollectionRequestVo = (UserCollectionRequestVo)JsonUtil.getObjectFromJSON(content,UserCollectionRequestVo.class);
        if (StringUtils.isBlank(userCollectionRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空");
        }
        if (StringUtils.isBlank(userCollectionRequestVo.getPageSize()) || Integer.parseInt(userCollectionRequestVo.getPageSize()) > AppApiUtil.MAX_PAGE_SIZE) {
            userCollectionRequestVo.setPageSize(String.valueOf(AppApiUtil.DEFAULT_PAGE_SIZE));
        }
        if (StringUtils.isBlank(userCollectionRequestVo.getPageNo())) {
            userCollectionRequestVo.setPageNo(String.valueOf(AppApiUtil.DEFAULT_PAGE_NO));
        }
        return this.apiWsService.getUserCollectionList(userCollectionRequestVo);
    }

    /**
     * 用户新增商品收藏
     * @return
     * @throws Exception
     */
    public CollectionOperationResponseVo getUserCollectionAdd(String content) throws Exception {
        CollectionOperationRequestVo collectionOperationRequestVo = (CollectionOperationRequestVo)JsonUtil.getObjectFromJSON(content,CollectionOperationRequestVo.class);
        if (StringUtils.isBlank(collectionOperationRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空");
        }
        if (StringUtils.isBlank(collectionOperationRequestVo.getItemId())) {
            throw new Exception("商品ID不能为空");
        }
        return this.apiWsService.getUserCollectionAdd(collectionOperationRequestVo);
    }

    /**
     * 用户更新商品收藏（更改status为N）
     * @return
     * @throws Exception
     */
    public CollectionOperationResponseVo getUserCollectionUpdate(String content) throws Exception {
        CollectionUpdateRequestVo collectionUpdateRequestVo= (CollectionUpdateRequestVo)JsonUtil.getObjectFromJSON(content,CollectionUpdateRequestVo.class);
        if (StringUtils.isBlank(collectionUpdateRequestVo.getUserId())) {
            throw new Exception("用户ID不能为空");
        }
        if (StringUtils.isBlank(collectionUpdateRequestVo.getCollectionId())) {
            throw new Exception("收藏ID不能为空");
        }
        return this.apiWsService.getUserCollectionUpdate(collectionUpdateRequestVo);
    }


    /**
     * 支付宝接口
     * @param content
     * @return
     * @throws Exception
     */
    private String aliPay(String content) throws Exception {
        OrderVo orderVo = new OrderVo();
        return this.apiWsService.getAliPay(orderVo);
    }

    /**
     * 支付宝支付成功后.异步请求该接口
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/notify_url",method=RequestMethod.POST)
    @ResponseBody
    public String notify(HttpServletRequest request,HttpServletResponse response) throws IOException {
//        logger.info("==================支付宝异步返回支付结果开始");
        //1.从支付宝回调的request域中取值
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "uft-8");
            conversionParams.put(key, valueStr);
        }
//        logger.info("==================返回参数集合："+conversionParams);
        String status = this.apiWsService.notify(conversionParams);
        return status;
    }

    /**
     * 向支付宝发起订单查询请求
     * @param content
     * @return
     * @throws Exception
     */
    public byte checkAlipay(String content) throws Exception {
        return this.apiWsService.checkAlipay(content);
    }



}
