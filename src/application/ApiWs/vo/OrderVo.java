package application.ApiWs.vo;

import java.util.List;

/**
 * 订单头实体类
 */
public class OrderVo {
    private String orderId;
    private String shoppingDate;
    private String orderStatus;
    private String StoreId;
    private List<OrderItemVo> orderItemVoList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(String shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public List<OrderItemVo> getOrderItemVoList() {
        return orderItemVoList;
    }

    public void setOrderItemVoList(List<OrderItemVo> orderItemVoList) {
        this.orderItemVoList = orderItemVoList;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId='" + orderId + '\'' +
                ", shoppingDate='" + shoppingDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderItemVoList=" + orderItemVoList +
                '}';
    }
}
