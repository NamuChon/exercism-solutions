class Clock {
    private int hours, minutes;
    Clock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        add(0);
    }

    void add(int minutes) {
        this.minutes += minutes;
        int hours = this.minutes / 60;
        this.minutes %= 60;
        int shift = 0;
        if (this.minutes < 0) {
            shift = -1;
            this.minutes += 60;
        } else if (this.minutes >= 60) {
            shift = 1;
            this.minutes -= 60;
        }
        this.hours = (((this.hours + hours + shift) % 24) + 24) % 24;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Clock)) return false;
        Clock otherClock = (Clock) obj;
        return this.hours == otherClock.hours && this.minutes == otherClock.minutes;
    }
}