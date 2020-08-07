package application.ApiWs.dao;

import application.ApiWs.vo.*;
import framework.mybatis.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ApiWsDao extends BaseDao {

    private static final String NAMESPACE = "application.ApiWs.mapper.ApiWsMapper.";

    public int getUserVerifiedCnt(UserLoginRequestVo userLoginRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getUserVerifiedCnt", userLoginRequestVo);
    }

    public UserLoginResponseVo getUserLoginInfo(UserLoginRequestVo userLoginRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getUserLoginInfo", userLoginRequestVo);
    }

    public List<StoreVo> getUserLoginStoreInfoList(String userId) throws Exception {
        return this.selectList(NAMESPACE + "getUserLoginStoreInfoList",userId);
    }

    public List<StoreVo> getUserLoginStoreInfoListForManager(String userId) throws Exception {
        return this.selectList(NAMESPACE + "getUserLoginStoreInfoListForManager",userId);
    }

    public int getUserRegistedFlag(String mobile) throws Exception {
        return this.selectOne(NAMESPACE + "getUserRegistedFlag",mobile);
    }

    public void getUserPasswordReset(UserResetPasswordRequestVo userResetPasswordRequestVo) throws Exception {
        this.update(NAMESPACE + "getUserPasswordReset",userResetPasswordRequestVo);
    }

    public UserVo getUserInfoByMobile(String mobile) throws Exception {
        return this.selectOne(NAMESPACE + "getUserInfoByMobile",mobile);
    }

    public int getItemListCount(ItemListRequestVo itemListRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getItemListCount",itemListRequestVo);
    }
    public List<ItemVo> getItemList(ItemListRequestVo itemListRequestVo) throws Exception {
        return this.selectList(NAMESPACE + "getItemList",itemListRequestVo);
    }

    public List<ItemVo> getItemListHotSell(ItemListRequestVo itemListRequestVo) throws Exception {
        return this.selectList(NAMESPACE+"getItemListHotSell",itemListRequestVo);
    }

    public List<ItemTypeListResponseVo> getItemTypeList() throws Exception {
        return this.selectList(NAMESPACE + "getItemTypeList",null);
    }

    public ItemVo getItemDetail(ItemDetailRequestVo itemDetailRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getItemDetail",itemDetailRequestVo);
    }

    public List<ShoppingCartVo> getShippingCartList(ShoppingCartListRequestVo shoppingCartListRequestVo) throws Exception {
        return this.selectList(NAMESPACE + "getShippingCartList", shoppingCartListRequestVo);
    }

    public List<ShoppingCartItemVo> getShippingCartItemList(String shoppingCartId) throws Exception {
        return this.selectList(NAMESPACE + "getShippingCartItemList",shoppingCartId);
    }

    public List<ItemVo> getShippingCartItemLIst(String shippingCartId) throws Exception {
        return selectList(NAMESPACE + "getShippingCartItemLIst",shippingCartId);
    }

    public int getShippingCartListCount(ShoppingCartListRequestVo shoppingCartListRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getShippingCartListCount",shoppingCartListRequestVo);
    }

    public int getShippingCartItemExist(String shoppingCartItemId) throws Exception {
        return this.selectOne(NAMESPACE + "getShippingCartItemExist",shoppingCartItemId);
    }

    public void getShoppingCartItemModified(ShoppingCartItemVo shoppingCartItemVo) throws Exception {
        this.update(NAMESPACE + "getShoppingCartItemModified",shoppingCartItemVo);
    }

    public void getShoppingCartItemInsert(ShoppingCartItemVo shoppingCartItemVo) throws Exception {
        this.update(NAMESPACE + "getShoppingCartItemInsert",shoppingCartItemVo);
    }

    public ShoppingCartVo getShoppingCartById(String shoppingCartId) throws Exception {
        return this.selectOne(NAMESPACE + "getShoppingCartById",shoppingCartId);
    }

    public void getShoppingCartInsert(ShoppingCartAddRequestVo shoppingCartAddRequestVo) throws Exception {
        this.insert(NAMESPACE + "getShoppingCartInsert",shoppingCartAddRequestVo);
    }

    public UserInfoResponseVo getUserInfo(UserInfoRequestVo userInfoRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getUserInfo",userInfoRequestVo);
    }

    public Map getShoppingCartConfirm(Map requestMap) throws Exception {
        return this.selectOne(NAMESPACE + "getShoppingCartConfirm",requestMap);
    }

    public OrderVo getOrderById(String orderId) throws Exception {
        return this.selectOne(NAMESPACE + "getOrderById",orderId);
    }

    public List<OrderItemVo> getOrderItemListByOrders(String orders) throws Exception {
        return this.selectList(NAMESPACE + "getOrderItemListByOrders",orders);
    }

    public int getOrderListCount(OrderListRequestVo orderListRequestVo) throws Exception {
        return this.selectOne(NAMESPACE + "getOrderListCount",orderListRequestVo);
    }

    public List<OrderVo> getOrderList(OrderListRequestVo orderListRequestVo) throws Exception {
        return this.selectList(NAMESPACE + "getOrderList",orderListRequestVo);
    }

    public List<WalletLogVo> getWalletLogList(WalletLogRequestVo walletLogRequestVo) throws Exception {
        return this.selectList(NAMESPACE + "getWalletLogList",walletLogRequestVo);
    }

    public List<PushMessageVo> getPushMessageList(PushMessageListRequestVo pushMessageListRequestVo) throws Exception {
        return this.selectList(NAMESPACE + "getPushMessageList",pushMessageListRequestVo);
    }

    public PushMessageVo getPushMessageDetail(String messageId) throws Exception {
        return this.selectOne(NAMESPACE + "getPushMessageDetail",messageId);
    }

    public void getPushMessageReaded(PushMessageDetailRequestVo pushMessageDetailRequestVo) throws Exception {
        this.insert(NAMESPACE + "getPushMessageReaded",pushMessageDetailRequestVo);
    }

    public List<HotSearchVo> getHotSearchList() throws Exception {
        return this.selectList(NAMESPACE + "getHotSearchList",null);
    }

    public int getHotSearchCnt(String content) throws Exception {
        return this.selectOne(NAMESPACE + "getHotSearchCnt",content);
    }

    public void getHotSearchInsert(String content) throws Exception {
        this.insert(NAMESPACE + "getHotSearchInsert",content);
    }

    public void getHotSearchUpdate(String content) throws Exception {
        this.insert(NAMESPACE + "getHotSearchUpdate",content);
    }

    public List<HotItemVo> getHotItemList() throws Exception {
        return this.selectList(NAMESPACE+"getHotItemList",null);
    }

    public List<SpecialItemVo> getSpecialItemList(SpecialItemRequestVo specialItemRequestVo) throws Exception {
        return this.selectList(NAMESPACE+"getSpecialItemList",specialItemRequestVo);
    }

    public List<SpecialItemAdVo> getSpecialItemAdsList() throws Exception {
        return this.selectList(NAMESPACE + "getSpecialItemAdsList",null);
    }

    public List<PromotionItemVo> getPromotionItemList(PromotionItemRequestVo promotionItemRequestVo) throws Exception {
        return this.selectList(NAMESPACE+"getPromotionItemList",promotionItemRequestVo);
    }

    public void getOrderUpdate(OrderUpdateRequestVo orderUpdateRequestVo) throws Exception {
        this.update(NAMESPACE + "getOrderUpdate",orderUpdateRequestVo);
    }

    public List<ItemTypeVo> getItemListByType(String typeCode) throws Exception {
        return this.selectList(NAMESPACE+"getItemListByType",typeCode);
    }

    public int getItemSaleQuantityById(String itemId) throws Exception {
        return this.selectOne(NAMESPACE+"getItemSaleQuantityById",itemId);
    }

    public List<ItemPromotionVo> getItemPromotionListByItemId(String itemId) throws Exception {
        return this.selectList(NAMESPACE+"getItemPromotionListByItemId",itemId);
    }

    public int getPromotionItemListCount() throws Exception {
        return this.selectOne(NAMESPACE+"getPromotionItemListCount",null);
    }

    public int getSpecialItemListCount() throws Exception {
        return this.selectOne(NAMESPACE+"getSpecialItemListCount",null);
    }

    public int getPushMessageCount() throws Exception {
        return this.selectOne(NAMESPACE+"getPushMessageCount",null);
    }

    public int getWalletLogCount(WalletLogRequestVo walletLogRequestVo) throws Exception {
        return this.selectOne(NAMESPACE+"getWalletLogCount",walletLogRequestVo);
    }

    public HomePageListResponseVo getHomePageList() throws Exception {
        return this.selectOne(NAMESPACE+"getHomePageList",null);
    }

    public List<ItemAdVo> getMessageAdListByMessageId(String messageId) throws Exception {
        return this.selectList(NAMESPACE+"getMessageAdListByMessageId",messageId);
    }

    public int getUserCollectionListCount(UserCollectionRequestVo userCollectionRequestVo) throws Exception {
        return this.selectOne(NAMESPACE+"getUserCollectionListCount",userCollectionRequestVo);
    }

    public List<CollectionVo> getUserCollectionList(UserCollectionRequestVo userCollectionRequestVo) throws Exception {
        return this.selectList(NAMESPACE+"getUserCollectionList",userCollectionRequestVo);
    }

    public void getUserCollectionAdd(CollectionOperationRequestVo collectionOperationRequestVo) throws Exception {
        this.insert(NAMESPACE+"getUserCollectionAdd",collectionOperationRequestVo);
    }

    public void getUserCollectionUpdate(CollectionUpdateRequestVo collectionUpdateRequestVo) throws Exception {
        this.update(NAMESPACE+"getUserCollectionUpdate",collectionUpdateRequestVo);
    }

    public void getShoppingCartItemDelete(ShoppingCartItemVo shoppingCartItemVo) throws Exception {
        this.delete(NAMESPACE+"getShoppingCartItemDelete",shoppingCartItemVo);
    }

    public int getShippingCartExist(ShoppingCartAddRequestVo shoppingCartAddRequestVo) throws Exception {
        return this.selectOne(NAMESPACE+"getShippingCartExist",shoppingCartAddRequestVo);
    }

    public String getShoppingCartId(ShoppingCartAddRequestVo shoppingCartAddRequestVo) throws Exception {
        return this.selectOne(NAMESPACE+"getShoppingCartId",shoppingCartAddRequestVo);
    }

    public void getShoppingCartDelete(String shoppingCartId) throws Exception {
        this.delete(NAMESPACE+"getShoppingCartDelete",shoppingCartId);
    }

    public void getShoppingCartModified(ShoppingCartModifiedRequestVo shoppingCartModifiedRequestVo) throws Exception {
        this.update(NAMESPACE+"getShoppingCartModified",shoppingCartModifiedRequestVo);
    }

    public Map getOrderCopyByPkg(Map requestMap) throws Exception {
        return this.selectOne(NAMESPACE+"getOrderCopyByPkg",requestMap);
    }

    public int getUserItemCollectedFlag(Map map) throws Exception {
        return this.selectOne(NAMESPACE+"getUserItemCollectedFlag",map);
    }

    public String getBatchId() throws Exception {
        return this.selectOne(NAMESPACE+"getBatchId",null);
    }

    public void insertIntoShoppingCartTemp(Map map) throws Exception {
        this.insert(NAMESPACE+"insertIntoShoppingCartTemp",map);
    }

    public void getShoppingCartUpdate(ShoppingCartAddRequestVo shoppingCartAddRequestVo) throws Exception {
        this.update(NAMESPACE + "getShoppingCartUpdate",shoppingCartAddRequestVo);
    }
}
