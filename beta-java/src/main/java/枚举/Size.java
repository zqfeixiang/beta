package 枚举;

public enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private Size(String size){
        this.size = size;
    }
    public String getSize(){
        return size;
    }
    private String size;
}
