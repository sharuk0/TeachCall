package dbp.techcall.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public interface StudentBookingsRes {
    Integer getId();
    LocalDate getDate();
    String getFirstName();
    String getLastName();
    LocalTime getStartTime();
    String getTitle();
    String getLink();
}
