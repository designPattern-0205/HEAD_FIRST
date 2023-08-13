public class PokemonSimulation {
    public static void main(String[] args)
    {
        Pokemon starting = new Squirtle();
        starting.PrintInfo();
        starting.Cry();
//        starting.Scratch();

        starting.Tackle();
        starting.feed();
    }
}
