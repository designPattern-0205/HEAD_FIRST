public abstract class  Pokemon {

    public String name;
    public int level;

    public void PrintInfo()
    {
        System.out.println("==============");
        System.out.println("이름 :" + name);
        System.out.println("레벨 :" + level);
        System.out.println("==============");
    }
    public void Scratch()
    {
        System.out.println("할퀴기 공격!");
    }

    public void Tackle()
    {
        System.out.println("몸통박치기 공격!");
    }

    public abstract void Cry();

    public void feed()
    {
        System.out.println("우걱우걱");
    }
}
