class Robot {
    private GridPosition position;
    private Orientation orientation;
    Robot(GridPosition initialPosition, Orientation initialOrientation) {
        this.position = initialPosition;
        this.orientation = initialOrientation;
    }

    GridPosition getGridPosition() {
        return position;
    }

    Orientation getOrientation() {
        return orientation;
    }

    void advance() {
        int currentX = position.x;
        int currentY = position.y;
        position = switch (orientation) {
            case NORTH -> new GridPosition(currentX, currentY + 1);
            case EAST -> new GridPosition(currentX + 1, currentY);
            case SOUTH -> new GridPosition(currentX, currentY - 1);
            case WEST -> new GridPosition(currentX - 1, currentY);
            default -> position;
        };
    }

    void turnLeft() {
        turnDirection(-1);
    }

    void turnRight() {
        turnDirection(1);
    }

    void simulate(String instructions) {
        instructions.chars().forEach(c -> {
            switch ((char) c) {
                case 'A':
                    advance();
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
            }});
    }

    private void turnDirection(int amount) {
        Orientation[] values = Orientation.values();
        int size = values.length;
        int newOrientationIndex = (orientation.ordinal() + amount + size) % size;
        orientation = values[newOrientationIndex];
    }
}