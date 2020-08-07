package application.ApiWs.vo;

import java.util.List;

/**
 * 钱包流水查询响应参数
 */
public class WalletLogResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private String userId;
    private List<WalletLogVo> walletLogVoList;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<WalletLogVo> getWalletLogVoList() {
        return walletLogVoList;
    }

    public void setWalletLogVoList(List<WalletLogVo> walletLogVoList) {
        this.walletLogVoList = walletLogVoList;
    }

    @Override
    public String toString() {
        return "WalletLogResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", userId='" + userId + '\'' +
                ", walletLogVoList=" + walletLogVoList +
                '}';
    }
}
