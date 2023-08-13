public abstract class Barbie {

    AppearanceType appearanceType;
    DialogType dialogType;

    public Barbie() {}

    public void sex()
    {
        System.out.println("바비는 모두 여자입니다!");
    }

    public void Name()
    {
        System.out.println("My name is BarBie!");
    }

    public void GetAppearance()
    {
        appearanceType.appearance();
    }

    public void GetDialog()
    {
        dialogType.dialog();
    }

}
