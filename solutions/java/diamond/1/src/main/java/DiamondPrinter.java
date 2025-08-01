import java.util.*;
class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> result = new ArrayList<>();
        int length = 2 * (a - 'A') + 1;
        char currentChar = 'A';
        int reflector = 1;
        for (int i = 0; i < length; i++) {
            result.add(getLine(currentChar, length - 2));
            if (i == length / 2) {
                reflector = -1;
            }
            currentChar += reflector;
        }
        return result;
    }
    private String getLine(char a, int spaceNum) {
        if (a == 'A') {
            String sideSpace = getSpace((int) Math.floor((float) spaceNum / 2) + 1);
            return sideSpace + a + sideSpace;
        }
        int midSpaceNum = 2 * (a - 'A') - 1;
        String sideSpace = getSpace((spaceNum - midSpaceNum) / 2);
        return sideSpace + a + getSpace(midSpaceNum) + a + sideSpace;
    }
    private String getSpace(int num) {
        return " ".repeat(num);
    }
}
