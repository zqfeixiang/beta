package pattern.templateMethod;
import pattern.templateMethod.AbstractTemplate;

//炒手撕包菜的类
public class ConcreteClass_BaoCai extends AbstractTemplate{

    @Override
    public void pourVegetable() {
        System.out.println("下锅的蔬菜是包菜");
    }

    @Override
    public void pourSauce(){
        System.out.println("下锅的酱料是辣椒");
    }
}