package application.ApiWs.vo;

/**
 * 商品广告图片实体类
 */
public class ItemAdVo {
    private String itemId;
    private String picUrl;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "ItemAdVo{" +
                "itemId='" + itemId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
