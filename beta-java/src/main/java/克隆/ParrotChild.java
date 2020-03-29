package 克隆;

public class ParrotChild{
    public String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
