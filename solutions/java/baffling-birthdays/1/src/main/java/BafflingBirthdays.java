import java.time.*;
import java.util.*;

class BafflingBirthdays {

    private static final int[] DAY_COUNTS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    boolean sharedBirthday(List<LocalDate> birthdates) {
        for (int i = 0; i < birthdates.size() - 1; i++) {
            LocalDate date1 = birthdates.get(i);
            int month1 = date1.getMonthValue(), day1 = date1.getDayOfMonth();
            for (int j = i + 1; j < birthdates.size(); j++) {
                LocalDate date2 = birthdates.get(j);
                int month2 = date2.getMonthValue(), day2 = date2.getDayOfMonth();
                if (month1 == month2 && day1 == day2) return true;
            }
        }
        return false;
    }

    List<LocalDate> randomBirthdates(int groupSize) {
        List<LocalDate> result = new ArrayList<>(groupSize);
        for (int i = 0; i < groupSize; i++) {
            int month = (int) (Math.random() * 12) + 1;
            int day = (int) (Math.random() * DAY_COUNTS[month]) + 1;
            result.add(LocalDate.of(1, month, day));
        }
        return result;
    }

    double estimatedProbabilityOfSharedBirthday(int groupSize) {
        final int TRIALS = 1000;
        int shareCount = 0;
        for (int i = 0; i < TRIALS; i++) {
            if (sharedBirthday(randomBirthdates(groupSize))) shareCount++;
        }
        return (double) shareCount / TRIALS * 100;
    }
}