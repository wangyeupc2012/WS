package application.ApiWs.vo;

import java.util.List;

/**
 * 获取促销商品响应参数
 */
public class PromotionItemResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<PromotionItemVo> promotionItemVoList;

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

    public List<PromotionItemVo> getPromotionItemVoList() {
        return promotionItemVoList;
    }

    public void setPromotionItemVoList(List<PromotionItemVo> promotionItemVoList) {
        this.promotionItemVoList = promotionItemVoList;
    }

    @Override
    public String toString() {
        return "PromotionItemResponseVo{" +
                "promotionItemVoList=" + promotionItemVoList +
                '}';
    }
}
