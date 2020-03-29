package 克隆;

public class Parrot implements Cloneable{
    public String name;
    public ParrotChild parrotChild;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
