import java.util.*;
import java.util.function.*;
public class PiecingItTogether {

    private static final List<Function<JigsawInfo, JigsawInfo>> TRIALS = List.of(
        PiecingItTogether::rowsAndColumns,
        PiecingItTogether::aspectRatio,
        PiecingItTogether::pieces
    );
    
    public static JigsawInfo getCompleteInformation(JigsawInfo input) {
        JigsawInfo result = null;
        for (Function<JigsawInfo, JigsawInfo> trial : TRIALS) {
            result = trial.apply(input);
            if (result != null) break;
        }
        if (result == null) throw new IllegalArgumentException("Insufficient data");
        checkContradiction(input, result);
        return result;
    }

    private static JigsawInfo aspectRatio(JigsawInfo input) {
        OptionalDouble optionalAspectRatio = input.getAspectRatio();
        Optional<String> optionalFormat = input.getFormat();
        double aspectRatio;
        if (optionalAspectRatio.isPresent()) {
            aspectRatio = optionalAspectRatio.getAsDouble();
        } else if (optionalFormat.isPresent() && optionalFormat.get().equals("square")) {
            aspectRatio = 1.0;
        } else {
            return null;
        }

        int rows = -1;
        if (input.getRows().isPresent()) {
            rows = input.getRows().getAsInt();
        } else if (input.getColumns().isPresent()) {
            rows = (int) Math.round(input.getColumns().getAsInt() / aspectRatio);
        } else if (input.getPieces().isPresent()) {
            rows = (int) Math.round(Math.sqrt(input.getPieces().getAsInt() / aspectRatio));
        } else if (input.getBorder().isPresent()) {
            rows = (int) Math.round((input.getBorder().getAsInt() / 2 + 2) / (aspectRatio + 1));
        } else if (input.getInside().isPresent()) {
            rows = solveQuadraticPlus(aspectRatio, -2 * aspectRatio - 2, -input.getInside().getAsInt() + 4);
        }
        
        if (rows == -1) return null;
        int columns = (int) Math.round(aspectRatio * rows);
        return rowsAndColumns(rows, columns);
    }

    private static JigsawInfo pieces(JigsawInfo input) {
        OptionalInt optionalPieces = input.getPieces(), optionalBorder = input.getBorder(), optionalInside = input.getInside();
        int pieces;
        if (optionalPieces.isPresent()) {
            pieces = optionalPieces.getAsInt();
        } else if (optionalBorder.isPresent() && optionalInside.isPresent()) {
            pieces = optionalBorder.getAsInt() + optionalInside.getAsInt();
        } else {
            return null;
        }

        int border = -1;
        if (optionalBorder.isPresent()) {
            border = optionalBorder.getAsInt();
        } else if (optionalInside.isPresent()) {
            border = pieces - optionalInside.getAsInt();
        }

        int rows = -1;
        if (input.getRows().isPresent()) {
            rows = input.getRows().getAsInt();
        } else if (input.getColumns().isPresent()) {
            rows = pieces - input.getColumns().getAsInt();
        } else if (border != -1 && input.getFormat().isPresent()) {
            int a = 2, b = -border - 4, c = 2 * pieces,
                rowOrCol1 = solveQuadraticPlus(a, b, c), rowOrCol2 = pieces / rowOrCol1;
            rows = input.getFormat().get().equals("portrait") ? Integer.max(rowOrCol1, rowOrCol2) : Integer.min(rowOrCol1, rowOrCol2);
        }

        if (rows == -1) return null;
        int columns = pieces / rows;
        return rowsAndColumns(rows, columns);
    }

    private static JigsawInfo rowsAndColumns(JigsawInfo input) {
        OptionalInt optionalRows = input.getRows(), optionalColumns = input.getColumns();
        if (optionalRows.isEmpty() || optionalColumns.isEmpty()) return null;
        return rowsAndColumns(optionalRows.getAsInt(), optionalColumns.getAsInt());
    }

    private static JigsawInfo rowsAndColumns(int rows, int columns) {
        int pieces = rows * columns,
            border = 2 * rows + 2 * columns - 4,
            inside = pieces - border;
        double aspectRatio = (double) columns / rows;

        return new JigsawInfo.Builder()
            .pieces(pieces)
            .border(border)
            .inside(inside)
            .rows(rows)
            .columns(columns)
            .aspectRatio(aspectRatio)
            .format(getFormat(aspectRatio))
            .build();
    }

    private static String getFormat(double aspectRatio) {
        return aspectRatio == 1.0 ? "square" : aspectRatio > 1.0 ? "landscape" : "portrait";
    }

    private static int solveQuadraticPlus(double a, double b, double c) {
        return (int) Math.round((-b + Math.sqrt(b*b - 4*a*c)) / (2*a));
    }

    private static void checkContradiction(JigsawInfo input, JigsawInfo result) {
        List<Function<JigsawInfo, Object>> toCheck = List.of(
            JigsawInfo::getPieces,
            JigsawInfo::getBorder,
            JigsawInfo::getInside,
            JigsawInfo::getRows,
            JigsawInfo::getColumns,
            JigsawInfo::getAspectRatio,
            JigsawInfo::getFormat
        );

        for (Function<JigsawInfo, Object> checkElement : toCheck) {
            Object inputInfo = checkElement.apply(input);
            if (isEmpty(inputInfo)) continue;
            Object resultInfo = checkElement.apply(result);
            if (!inputInfo.equals(resultInfo)) throw new IllegalArgumentException("Contradictory data");
        }
    }

    private static boolean isEmpty(Object optional) {
        if (optional instanceof OptionalInt) {
            return ((OptionalInt) optional).isEmpty();
        } else if (optional instanceof OptionalDouble) {
            return ((OptionalDouble) optional).isEmpty();
        } else {
            return ((Optional<String>) optional).isEmpty();
        }
    }
}
