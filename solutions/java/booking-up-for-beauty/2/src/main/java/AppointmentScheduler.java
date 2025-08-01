import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
class AppointmentScheduler {
    final private LocalDate ANNIVERSARY = LocalDate.of(2012, 9, 15);
    public LocalDateTime schedule(String appointmentDateDescription) {
        return LocalDateTime.parse(appointmentDateDescription, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        return appointmentDate.isBefore(LocalDateTime.now());
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        return appointmentDate.getHour() >= 12 && appointmentDate.getHour() < 18;
    }

    public String getDescription(LocalDateTime appointmentDate) {
        return DateTimeFormatter.ofPattern("'You have an appointment on' EEEE, MMMM d, yyyy, 'at' h:mm a.").format(appointmentDate);
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.of(LocalDate.now().getYear(), ANNIVERSARY.getMonthValue(), ANNIVERSARY.getDayOfMonth());
    }
}