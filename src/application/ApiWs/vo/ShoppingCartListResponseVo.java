package application.ApiWs.vo;

import java.util.List;

/**
 * 货单（购物车）列表响应参数
 */
public class ShoppingCartListResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<ShoppingCartVo> shoppingCartListResponseVoList;

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

    public List<ShoppingCartVo> getShoppingCartListResponseVoList() {
        return shoppingCartListResponseVoList;
    }

    public void setShoppingCartListResponseVoList(List<ShoppingCartVo> shoppingCartListResponseVoList) {
        this.shoppingCartListResponseVoList = shoppingCartListResponseVoList;
    }

    @Override
    public String toString() {
        return "ShoppingCartListResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", shoppingCartListResponseVoList=" + shoppingCartListResponseVoList +
                '}';
    }
}
