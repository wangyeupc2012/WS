package application.ApiWs.vo;

/**
 * 促销商品实体类
 */
public class PromotionItemVo {
    private String itemId;
    private String itemName;
    private String itemPriceNow;
    private String itemPrice;
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

    public String getItemPriceNow() {
        return itemPriceNow;
    }

    public void setItemPriceNow(String itemPriceNow) {
        this.itemPriceNow = itemPriceNow;
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

    @Override
    public String toString() {
        return "PromotionItemVo{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPriceNow='" + itemPriceNow + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemPic='" + itemPic + '\'' +
                '}';
    }
}
