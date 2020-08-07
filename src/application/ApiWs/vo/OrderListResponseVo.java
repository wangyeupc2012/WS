package application.ApiWs.vo;

import java.util.List;

/**
 * 获取订单列表响应参数
 */
public class OrderListResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<OrderVo> orderVoList;

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

    public List<OrderVo> getOrderVoList() {
        return orderVoList;
    }

    public void setOrderVoList(List<OrderVo> orderVoList) {
        this.orderVoList = orderVoList;
    }

    @Override
    public String toString() {
        return "OrderListResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", orderVoList=" + orderVoList +
                '}';
    }
}
