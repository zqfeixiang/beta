package constants;

public enum ResultCodeEnum {

    SUCCESS(1, "success"),
    NO_DATA_ERROR(-1, "查询数据为空");

    private int code;
    private String message;
    private ResultCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
