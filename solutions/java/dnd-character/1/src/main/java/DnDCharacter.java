import java.util.*;
class DnDCharacter {
    private int[] abilities = new int[6];
    public DnDCharacter() {
        abilities = Arrays.stream(abilities).map(i -> ability(rollDice())).toArray();
    }
    int ability(List<Integer> scores) {
        return scores.stream().sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).sum();
    }

    List<Integer> rollDice() {
        return new Random().ints(4, 1, 7).mapToObj(Integer::valueOf).toList();
    }

    int modifier(int input) {
        return (int) Math.floor(((float)input - 10) / 2);
    }

    int getStrength() {
        return abilities[0];
    }

    int getDexterity() {
        return abilities[1];
    }

    int getConstitution() {
        return abilities[2];
    }

    int getIntelligence() {
        return abilities[3];
    }

    int getWisdom() {
        return abilities[4];
    }

    int getCharisma() {
        return abilities[5];
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }
}