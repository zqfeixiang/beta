package 克隆;

public class CloneUtilTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        BirdChild birdChild = new BirdChild();
        birdChild.name = "小小鸟";
        Bird bird = new Bird();
        bird.name = "小鸟";
        bird.birdChild = birdChild;

        //使用序列化克隆对象
        Bird bird1 = CloneUtils.clone(bird);
        bird1.name = "黄雀";
        bird1.birdChild.name = "小黄雀";
        System.out.println("bird name:" + bird.name);
        System.out.println("bird child name:" + bird.birdChild.name);
        System.out.println("bird2 name:" + bird1.name);
        System.out.println("bird2 birdchild name:" + bird1.birdChild.name);

        //2.所有引用类型都实现克隆
        ParrotChild parrotChild = new ParrotChild();
        parrotChild.name = "小鹦鹉";
        Parrot parrot = new Parrot();
        parrot.name = "金刚鹦鹉";
        parrot.parrotChild = parrotChild;

        Parrot parrot1 = (Parrot) parrot.clone();
        parrot1.name = "棕毛鹦鹉";
        parrot1.parrotChild.name = "小棕毛";

        System.out.println("parrot ");
    }
}
