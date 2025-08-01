import java.util.Arrays;
class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        return Arrays.stream(String.valueOf(numberToCheck).split("")).mapToInt(Integer::parseInt).map(n -> (int) Math.pow(n, String.valueOf(numberToCheck).length())).sum() == numberToCheck;
    }

}
