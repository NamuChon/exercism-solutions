class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}
class Warrior extends Fighter
{
    @Override
    public String toString()
    {
        return "Fighter is a Warrior";
    }
    @Override
    boolean isVulnerable()
    {
        return false;
    }
    @Override
    int getDamagePoints(Fighter fighter)
    {
        return fighter.isVulnerable() ? 10 : 6;
    }
}
class Wizard extends Fighter
{
    private boolean isVulnerable = true;
    void prepareSpell()
    {
        isVulnerable = false;
    }
    @Override
    public String toString()
    {
        return "Fighter is a Wizard";
    }
    @Override
    boolean isVulnerable()
    {
        return isVulnerable;
    }
    @Override
    int getDamagePoints(Fighter fighter)
    {
        return isVulnerable ? 3 : 12;
    }
}