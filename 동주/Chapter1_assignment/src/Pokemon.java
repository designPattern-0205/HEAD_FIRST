public abstract class  Pokemon {
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
