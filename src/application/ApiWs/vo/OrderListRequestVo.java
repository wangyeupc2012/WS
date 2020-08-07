package application.ApiWs.vo;

/**
 * 获取订单列表请求参数
 */
public class OrderListRequestVo {
    private String storeId;
    private String orderStatus;
    private String shoppingDate;
    private String orderId;
    private String pageSize;
    private String pageNo;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(String shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
        return "OrderListRequestVo{" +
                "storeId='" + storeId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", shoppingDate='" + shoppingDate + '\'' +
                ", orderId='" + orderId + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
