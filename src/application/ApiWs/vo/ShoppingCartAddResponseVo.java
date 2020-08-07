package application.ApiWs.vo;

/**
 * 新增购物车响应参数
 * updated by wangye @ 2018年10月29日
 */
public class ShoppingCartAddResponseVo {
    private String storeId;
    private String shoppingCartId;
    private String itemId;
    private String itemName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartAddResponseVo{" +
                "storeId='" + storeId + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                '}';
    }
}
