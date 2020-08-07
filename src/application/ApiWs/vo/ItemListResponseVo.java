package application.ApiWs.vo;

import java.util.List;

/**
 * 获取商品列表响应参数
 */
public class ItemListResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<ItemVo> itemVoList;

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

    public List<ItemVo> getItemVoList() {
        return itemVoList;
    }

    public void setItemVoList(List<ItemVo> itemVoList) {
        this.itemVoList = itemVoList;
    }

    @Override
    public String toString() {
        return "ItemListResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", itemVoList=" + itemVoList +
                '}';
    }
}
