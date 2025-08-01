import java.util.Arrays;
class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        int[] digits = Arrays.stream(String.valueOf(numberToCheck).split("")).mapToInt(Integer::parseInt).toArray();
        int result = Arrays.stream(digits).map(n -> (int) Math.pow(n, digits.length)).sum();
        return result == numberToCheck;
    }

}
