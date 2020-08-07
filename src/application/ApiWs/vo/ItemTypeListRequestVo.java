package application.ApiWs.vo;

/**
 * 获取商品分类请求参数
 */
public class ItemTypeListRequestVo {
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
        return "ItemTypeListRequestVo{" +
                "pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
