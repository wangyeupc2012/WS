package application.ApiWs.vo;

/**
 * 首页广告位响应参数实体类
 */
public class HomePageListResponseVo {
    private String homePage;
    private String itemId;

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "HomePageListResponseVo{" +
                "homePage='" + homePage + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
