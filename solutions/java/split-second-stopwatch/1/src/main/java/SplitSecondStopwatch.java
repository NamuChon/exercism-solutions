import java.util.*;
public class SplitSecondStopwatch {
    
    private enum State {
        READY,
        STOPPED,
        RUNNING;

        public String value;

        private State() {
            this.value = this.toString().toLowerCase();
        }
    }

    private final List<String> previousLaps = new ArrayList<>();
    private int totalTime = 0;
    private int currentLap = 0;
    private State currentState = State.READY;
    
    public void start() {
        if (currentState == State.RUNNING) throw new IllegalStateException("cannot start an already running stopwatch");
        currentState = State.RUNNING;
    }

    public void stop() {
        if (currentState != State.RUNNING) throw new IllegalStateException("cannot stop a stopwatch that is not running");
        currentState = State.STOPPED;
    }

    public void reset() {
        if (currentState != State.STOPPED) throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        currentState = State.READY;
        previousLaps.clear();
        totalTime = 0;
        currentLap = 0;
    }

    public void lap() {
        if (currentState != State.RUNNING) throw new IllegalStateException("cannot lap a stopwatch that is not running");
        previousLaps.add(currentLap());
        currentLap = 0;
    }

    public String state() {
        return currentState.value;
    }

    public String currentLap() {
        return timeToString(currentLap);
    }

    public String total() {
        return timeToString(totalTime);
    }

    public List<String> previousLaps() {
        return previousLaps;
    }

    public void advanceTime(String timeString) {
        if (currentState == State.RUNNING) {
            int hours = Integer.parseInt(timeString.substring(0, 2)),
                minutes = Integer.parseInt(timeString.substring(3, 5)),
                seconds = Integer.parseInt(timeString.substring(6, 8));
            int amount = hours * 3600 + minutes * 60 + seconds;
            currentLap += amount;
            totalTime += amount;
        }
    }

    private static String timeToString(int time) {
        int hours = time / 3600;
        time %= 3600;
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}