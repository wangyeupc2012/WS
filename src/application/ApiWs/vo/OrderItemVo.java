package application.ApiWs.vo;

/**
 * 订单行实体类
 */
public class OrderItemVo {
    private String orderItemId;
    private String itemId;
    private String itemSn;
    private String itemQuantity;
    private String itemPrice;
    private String itemName;
    private String itemPic;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemSn() {
        return itemSn;
    }

    public void setItemSn(String itemSn) {
        this.itemSn = itemSn;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    @Override
    public String toString() {
        return "OrderItemVo{" +
                "orderItemId='" + orderItemId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemSn='" + itemSn + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPic='" + itemPic + '\'' +
                '}';
    }
}
