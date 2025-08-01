import java.time.*;
import java.time.temporal.*;

public class SwiftScheduling {
    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        return switch (description) {
            case "NOW" -> meetingStart.plusHours(2);
            case "ASAP" -> meetingStart.getHour() < 13 ? setHour(meetingStart, 17) : setHour(meetingStart.plusDays(1), 13);
            case "EOW" -> switch (meetingStart.getDayOfWeek()) {
                case MONDAY, TUESDAY, WEDNESDAY -> setHour(setDayOfWeek(meetingStart, DayOfWeek.FRIDAY), 17);
                case THURSDAY, FRIDAY -> setHour(setDayOfWeek(meetingStart, DayOfWeek.SUNDAY), 20);
                default -> null;
            };
            case String s when s.matches("\\d{1,2}M") -> {
                int month = Integer.parseInt(s.substring(0, s.length() - 1));
                yield meetingStart.getMonthValue() < month ? setHour(firstWorkDay(meetingStart.withMonth(month)), 8) : setHour(firstWorkDay(meetingStart.plusYears(1).withMonth(month)), 8);
            }
            case String s when s.matches("Q[1-4]") -> {
                int quarter = Character.getNumericValue(s.charAt(1));
                yield (meetingStart.getMonthValue() - 1) / 3 + 1 <= quarter ? setHour(lastWorkDay(meetingStart, quarter), 8) : setHour(lastWorkDay(meetingStart.plusYears(1), quarter), 8);
            }
            default -> null;
        };
    }

    private static LocalDateTime setHour(LocalDateTime original, int hour) {
        return original.withHour(hour).withMinute(0).withSecond(0);
    }

    private static LocalDateTime setDayOfWeek(LocalDateTime original, DayOfWeek day) {
        return original.with(TemporalAdjusters.nextOrSame(day));
    }

    private static LocalDateTime firstWorkDay(LocalDateTime original) {
        original = original.with(TemporalAdjusters.firstDayOfMonth());
        return switch (original.getDayOfWeek()) {
            case SATURDAY -> original.plusDays(2);
            case SUNDAY -> original.plusDays(1);
            default -> original;
        };
    }

    private static LocalDateTime lastWorkDay(LocalDateTime original, int quarter) {
        original = original.withMonth(quarter * 3).with(TemporalAdjusters.lastDayOfMonth());
        return switch (original.getDayOfWeek()) {
            case SATURDAY -> original.minusDays(1);
            case SUNDAY -> original.minusDays(2);
            default -> original;
        };
    }
}
