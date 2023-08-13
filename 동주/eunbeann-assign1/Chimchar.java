public class Chimchar extends Pokemon{ //불꽃숭이

    public Chimchar()
    {
        this.name = "불꽃숭이";
        this.level = 1;
        punchSkills = new FirePunch();
    }
    @Override
    public void Cry() {
        System.out.println("숭이숭이!!");
    }
}
