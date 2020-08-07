package application.ApiWs.vo;

import java.util.List;

/**
 * 获取推送消息响应参数
 */
public class PushMessageListResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<PushMessageVo> pushMessageVoList;

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

    public List<PushMessageVo> getPushMessageVoList() {
        return pushMessageVoList;
    }

    public void setPushMessageVoList(List<PushMessageVo> pushMessageVoList) {
        this.pushMessageVoList = pushMessageVoList;
    }

    @Override
    public String toString() {
        return "PushMessageListResponseVo{" +
                "totalCount='" + totalCount + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", totalPage='" + totalPage + '\'' +
                ", pushMessageVoList=" + pushMessageVoList +
                '}';
    }
}
