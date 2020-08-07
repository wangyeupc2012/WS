package application.ApiWs.vo;

/**
 * 特卖商品页面广告轮播图
 */
public class SpecialItemAdVo {
    private String adId;
    private String thumbnail;
    private String adPic;
    private String itemId;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "SpecialItemAdVo{" +
                "adId='" + adId + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", adPic='" + adPic + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
