package application.ApiWs.vo;

/**
 * 货单（购物车）编辑响应参数
 */
public class ShoppingCartModifiedResponseVo {
    private String storeId;
    private String shoppingCartId;
    private String itemId;
    private String itemQuantity;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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

    @Override
    public String toString() {
        return "ShoppingCartModifiedResponseVo{" +
                "storeId='" + storeId + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                '}';
    }
}
