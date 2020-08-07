package application.ApiWs.vo;

/**
 * 获取购物车列表请求参数
 */
public class ShoppingCartListRequestVo {
    private String storeId;
    private String shoppingCartId;
    private String pageSize;
    private String pageNo;

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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "ShoppingCartListRequestVo{" +
                "storeId='" + storeId + '\'' +
                ", shoppingCartId='" + shoppingCartId + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
