public class Lasagna {
    public int expectedMinutesInOven()
    {
        return 40;
    }
    public int remainingMinutesInOven(int t)
    {
        return expectedMinutesInOven() - t;
    }

    public int preparationTimeInMinutes(int l)
    {
        return 2 * l;
    }

    public int totalTimeInMinutes(int l, int t)
    {
        return preparationTimeInMinutes(l) + t;
    }
}
