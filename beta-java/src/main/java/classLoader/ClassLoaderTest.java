package classLoader;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {

        //获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取系统类加载器的父类加载器-->扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取扩展类加载器的父类-->根加载器
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        //当前类是哪个类加载器加载的
        Class<?> aClass = Class.forName("classLoader.ClassLoaderTest");
        System.out.println(aClass.getClassLoader());

        //JDK内部类是哪个类加载器
        Class<?> aClass1 = Class.forName("java.lang.String");
        System.out.println(aClass1.getClassLoader());

        //系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));

    }
}
