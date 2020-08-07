package application.ApiWs.vo;

/**
 * 购物车ID实体类
 */
public class ShoppingCartIdVo {
    private String shoppingCartId;

    public String getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    @Override
    public String toString() {
        return "ShoppingCartIdVo{" +
                "shoppingCartId='" + shoppingCartId + '\'' +
                '}';
    }
}
