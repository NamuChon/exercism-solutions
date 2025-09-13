import java.util.*;
import java.util.stream.*;
public class Transpose {
    public String transpose(String toTranspose) {
        String[] toTransposeRows = toTranspose.split("\n");
        int rows = toTransposeRows.length;
        int maxColumns = Arrays.stream(toTransposeRows)
            .mapToInt(String::length)
            .max()
            .orElse(0);
        char[][] result = new char[maxColumns][rows];
        
        for (int i = 0; i < rows; i++) {
            String row = toTransposeRows[i];
            for (int j = 0; j < maxColumns; j++) {
                result[j][i] = row.length() > j ? row.charAt(j) : '\0';
            }
        }
        
        return Arrays.stream(result)
            .map(a -> new String(a).replaceFirst("\0+$", "").replace("\0", " "))
            .collect(Collectors.joining("\n"));
    }
}
