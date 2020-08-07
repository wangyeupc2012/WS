package application.ApiWs.vo;

/**
 * 收藏商品实体类
 */
public class CollectionVo {
    private String itemName;
    private String itemId;
    private String itemPic;
    private String itemPrice;
    private String collectionId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public String toString() {
        return "CollectionVo{" +
                "itemName='" + itemName + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", collectionId='" + collectionId + '\'' +
                '}';
    }
}
