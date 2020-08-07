package application.ApiWs.vo.api;

/**
 * API接口相应参数
 * @author wangye
 * 2018-08-08
 */
public class ApiResponse {
    public enum CodeEnum {
        SUCCESS,FAILURE
    }

    private CodeEnum code;
    private String message;
    private Object data;

    public int getCode() {
        return code.ordinal();
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
