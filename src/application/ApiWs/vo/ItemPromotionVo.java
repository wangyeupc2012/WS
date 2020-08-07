package application.ApiWs.vo;

/**
 * 促销折扣实体类
 */
public class ItemPromotionVo {
    private String promotionId;
    private String promotionName;
    private String itemId;
    private String condition;
    private String discount;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "itemPromotionVo{" +
                "promotionId='" + promotionId + '\'' +
                ", promotionName='" + promotionName + '\'' +
                ", itemId='" + itemId + '\'' +
                ", condition='" + condition + '\'' +
                ", discount='" + discount + '\'' +
                '}';
    }
}
