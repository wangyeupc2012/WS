package application.ApiWs.vo;

/**
 * 用户收藏商品相关操作通用相应参数
 */
public class CollectionOperationResponseVo {
    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CollectionOperationResponseVo{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
