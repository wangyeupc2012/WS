package application.ApiWs.vo;

import java.util.List;

/**
 * 货单（购物车）编辑请求参数
 */
public class ShoppingCartModifiedRequestVo {
    private String shoppingCartId;
    private String userId;
    private String itemQuantity;

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartModifiedRequestVo{" +
                "shoppingCartId='" + shoppingCartId + '\'' +
                ", userId='" + userId + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                '}';
    }
}
