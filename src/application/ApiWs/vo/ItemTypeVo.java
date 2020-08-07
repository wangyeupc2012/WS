package application.ApiWs.vo;

/**
 * 商品分类 每个分类下商品实体类
 */
public class ItemTypeVo {
    private String itemId;
    private String itemName;
    private String itemPriceNow;
    private String itemPriceOri;
    private String itemPic;
    private String itemSaleQuantity;

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

    public String getItemPriceOri() {
        return itemPriceOri;
    }

    public void setItemPriceOri(String itemPriceOri) {
        this.itemPriceOri = itemPriceOri;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemSaleQuantity() {
        return itemSaleQuantity;
    }

    public void setItemSaleQuantity(String itemSaleQuantity) {
        this.itemSaleQuantity = itemSaleQuantity;
    }

    @Override
    public String toString() {
        return "ItemTypeVo{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPriceNow='" + itemPriceNow + '\'' +
                ", itemPriceOri='" + itemPriceOri + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", itemSaleQuantity='" + itemSaleQuantity + '\'' +
                '}';
    }
}
