package application.ApiWs.vo;

/**
 * 热搜实体类
 */
public class HotSearchVo {
    private String searchContent;
    private String searchCount;

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(String searchCount) {
        this.searchCount = searchCount;
    }

    @Override
    public String toString() {
        return "HotSearchVo{" +
                "searchContent='" + searchContent + '\'' +
                ", searchCount='" + searchCount + '\'' +
                '}';
    }
}
