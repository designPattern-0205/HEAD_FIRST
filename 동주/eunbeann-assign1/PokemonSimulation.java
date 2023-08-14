public class PokemonSimulation {
    public static void main(String[] args)
    {
        Pokemon starting = new Piplup();
        starting.PrintInfo();
        starting.Cry();
//        starting.Scratch();
        starting.Tackle();
        starting.PunchAction();
        starting.feed();
    }
}
