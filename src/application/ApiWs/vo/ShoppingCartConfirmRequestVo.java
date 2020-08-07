package application.ApiWs.vo;

import java.util.List;

/**
 * 购物车确认请求参数
 */
public class ShoppingCartConfirmRequestVo {
    private String userId;
    private String shoppingDate;
    private List<ShoppingCartIdVo> shoppingCartIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(String shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public List<ShoppingCartIdVo> getShoppingCartIds() {
        return shoppingCartIds;
    }

    public void setShoppingCartIds(List<ShoppingCartIdVo> shoppingCartIds) {
        this.shoppingCartIds = shoppingCartIds;
    }

    @Override
    public String toString() {
        return "ShoppingCartConfirmRequestVo{" +
                "userId='" + userId + '\'' +
                ", shoppingDate='" + shoppingDate + '\'' +
                ", shoppingCartIds=" + shoppingCartIds +
                '}';
    }
}
