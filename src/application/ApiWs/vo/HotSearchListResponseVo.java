package application.ApiWs.vo;

import java.util.List;

/**
 * 热搜 响应参数
 */
public class HotSearchListResponseVo {
    private List<HotSearchVo> hotSearchVoList;

    public List<HotSearchVo> getHotSearchVoList() {
        return hotSearchVoList;
    }

    public void setHotSearchVoList(List<HotSearchVo> hotSearchVoList) {
        this.hotSearchVoList = hotSearchVoList;
    }

    @Override
    public String toString() {
        return "HotSearchListResponseVo{" +
                "hotSearchVoList=" + hotSearchVoList +
                '}';
    }
}
