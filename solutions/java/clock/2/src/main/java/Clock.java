class Clock {
    private static final int DAY = 24 * 60;
    private int minutes = 0;
    Clock(int hours, int minutes) {
        add(60 * hours + minutes);
    }

    void add(int minutes) {
        this.minutes = (((this.minutes + minutes) % DAY) + DAY) % DAY;
    }

    @Override
    public String toString() {
        int hours = this.minutes / 60;
        int minutes = this.minutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}