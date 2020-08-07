package application.ApiWs.vo;

import java.util.List;

/**
 * 用户收藏商品响应参数
 */
public class UserCollectionResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<CollectionVo> collectionVoList;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<CollectionVo> getCollectionVoList() {
        return collectionVoList;
    }

    public void setCollectionVoList(List<CollectionVo> collectionVoList) {
        this.collectionVoList = collectionVoList;
    }

    @Override
    public String toString() {
        return "UserCollectionResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", collectionVoList=" + collectionVoList +
                '}';
    }
}
