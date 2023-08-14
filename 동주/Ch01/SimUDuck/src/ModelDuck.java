public class ModelDuck extends Duck{
    public ModelDuck()
    {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display()
    {
        System.out.println("[꺼림직한 생김새의 모형오리다. 다가가지 않는게 좋을 것 같다.]");
    }
}
