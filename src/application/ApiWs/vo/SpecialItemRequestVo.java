package application.ApiWs.vo;

/**
 * 获取特卖商品请求参数
 */
public class SpecialItemRequestVo {
    private String pageSize;
    private String pageNo;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PromotionItemRequestVo{" +
                "pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
