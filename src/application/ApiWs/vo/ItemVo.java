package application.ApiWs.vo;

import java.util.List;

/**
 * 商品bean类
 */
public class ItemVo {
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemSpec;
    private String itemPic;
    private String itemShelfLife;
    private String itemQuantity;
    private String storageCondition;
    private String isBackBottle;
    private String isCollected;
    private String itemDetailPic;
    private List<String> carousels;
    private List<ItemPromotionVo> itemPromotionVoList;

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

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemShelfLife() {
        return itemShelfLife;
    }

    public void setItemShelfLife(String itemShelfLife) {
        this.itemShelfLife = itemShelfLife;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getIsBackBottle() {
        return isBackBottle;
    }

    public void setIsBackBottle(String isBackBottle) {
        this.isBackBottle = isBackBottle;
    }

    public List<ItemPromotionVo> getItemPromotionVoList() {
        return itemPromotionVoList;
    }

    public void setItemPromotionVoList(List<ItemPromotionVo> itemPromotionVoList) {
        this.itemPromotionVoList = itemPromotionVoList;
    }

    public String getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(String isCollected) {
        this.isCollected = isCollected;
    }

    public String getItemDetailPic() {
        return itemDetailPic;
    }

    public void setItemDetailPic(String itemDetailPic) {
        this.itemDetailPic = itemDetailPic;
    }

    public List<String> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<String> carousels) {
        this.carousels = carousels;
    }

    @Override
    public String toString() {
        return "ItemVo{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemSpec='" + itemSpec + '\'' +
                ", itemPic='" + itemPic + '\'' +
                ", itemShelfLife='" + itemShelfLife + '\'' +
                ", itemQuantity='" + itemQuantity + '\'' +
                ", storageCondition='" + storageCondition + '\'' +
                ", isBackBottle='" + isBackBottle + '\'' +
                ", isCollected='" + isCollected + '\'' +
                ", itemDetailPic='" + itemDetailPic + '\'' +
                ", carousels=" + carousels +
                ", itemPromotionVoList=" + itemPromotionVoList +
                '}';
    }
}
