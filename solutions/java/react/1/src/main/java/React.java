import java.util.*;
import java.util.function.*;
public class React {

    public static class Cell<T> {
        protected List<ComputeCell<T>> computeCells = new ArrayList<>();
        protected T value;
        
        public T getValue() {
            return value;
        }

        protected void addComputeCell(ComputeCell<T> computeCell) {
            computeCells.add(computeCell);
        }

        protected void updateComputeCells() {
            computeCells.stream().forEach(ComputeCell::updateValue);
            computeCells.stream().forEach(ComputeCell::updateComputeCells);
        }

        protected void callback() {
            computeCells.stream().forEach(ComputeCell::call);
            computeCells.stream().forEach(ComputeCell::callback);
        }
    }

    public static class InputCell<T> extends Cell<T> {
        
        public void setValue(T newValue) {
            value = newValue;
            updateComputeCells();
            callback();
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        private List<Consumer<T>> callbacks = new ArrayList<>();
        private Function<List<T>, T> function;
        private List<Cell<T>> cells;
        private boolean changed;
        
        public ComputeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
            this.function = function;
            this.cells = cells;
            updateValue();
        }
        
        public void addCallback(Consumer<T> callback) {
            callbacks.add(callback);
        }

        public void removeCallback(Consumer<T> callback) {
            callbacks.remove(callback);
        }

        protected void updateValue() {
            T newValue = function.apply(cells.stream().map(Cell::getValue).toList());
            if (!Objects.equals(value, newValue)) {
                value = newValue;
                changed = true;
            } else {
                changed = false;
            }
        }

        protected void call() {
            if (changed) callbacks.forEach(consumer -> consumer.accept(value));
            changed = false;
        }
    }

    public static <T> InputCell<T> inputCell(T initialValue) {
        InputCell<T> inputCell = new InputCell<>();
        inputCell.setValue(initialValue);
        return inputCell;
    }

    public static <T> ComputeCell<T> computeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
        ComputeCell<T> computeCell = new ComputeCell<>(function, cells);
        cells.forEach(cell -> cell.addComputeCell(computeCell));
        return computeCell;
    }
}
