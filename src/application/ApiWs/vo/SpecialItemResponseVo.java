package application.ApiWs.vo;

import java.util.List;

/**
 * 获取特卖商品相应参数
 */
public class SpecialItemResponseVo {
    private String totalCount;
    private String currentPage;
    private String totalPage;
    private List<SpecialItemVo> specialItemVoList;
    private List<SpecialItemAdVo> specialItemAdVoList;

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

    public List<SpecialItemVo> getSpecialItemVoList() {
        return specialItemVoList;
    }

    public void setSpecialItemVoList(List<SpecialItemVo> specialItemVoList) {
        this.specialItemVoList = specialItemVoList;
    }

    public List<SpecialItemAdVo> getSpecialItemAdVoList() {
        return specialItemAdVoList;
    }

    public void setSpecialItemAdVoList(List<SpecialItemAdVo> specialItemAdVoList) {
        this.specialItemAdVoList = specialItemAdVoList;
    }

    @Override
    public String toString() {
        return "SpecialItemResponseVo{" +
                "specialItemVoList=" + specialItemVoList +
                ", specialItemAdVoList=" + specialItemAdVoList +
                '}';
    }
}
