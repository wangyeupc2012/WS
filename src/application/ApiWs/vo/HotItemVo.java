package application.ApiWs.vo;

/**
 * 热卖商品实体类
 */
public class HotItemVo {
    private String itemId;
    private String itemName;
    private String itemSaleQuantity;
    private String itemPrice;
    private String itemOriPrice;
    private String itemPic;

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

    public String getItemSaleQuantity() {
        return itemSaleQuantity;
    }

    public void setItemSaleQuantity(String itemSaleQuantity) {
        this.itemSaleQuantity = itemSaleQuantity;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemOriPrice() {
        return itemOriPrice;
    }

    public void setItemOriPrice(String itemOriPrice) {
        this.itemOriPrice = itemOriPrice;
    }

    @Override
    public String toString() {
        return "HotItemVo{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemSaleQuantity='" + itemSaleQuantity + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemOriPrice='" + itemOriPrice + '\'' +
                ", itemPic='" + itemPic + '\'' +
                '}';
    }
}
