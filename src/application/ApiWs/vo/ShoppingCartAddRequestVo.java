package application.ApiWs.vo;


/**
 * 货单新增请求参数
 */
public class ShoppingCartAddRequestVo {
    private String storeId;
    private String userId;
    private String itemId;
    private String itemQuantity;
    private String shoppingCartId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return "ShoppingCartAddRequestVo{" +
                "storeId='" + storeId + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                '}';
    }
}
