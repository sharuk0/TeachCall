package dbp.techcall.timeSlot.infrastructure;

import dbp.techcall.timeSlot.dto.BasicDayAvailability;
import dbp.techcall.timeSlot.domain.TimeSlot;
import dbp.techcall.timeSlot.dto.DateTimeProjection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByProfessorIdAndWeekNumber(Long professorId, int i);

    List<TimeSlot> findByProfessorIdAndWeekNumberAndDay(Long id, Integer week, Integer day, Sort sort);

    List<BasicDayAvailability> findByProfessorIdAndWeekNumber(Long professorId, Integer week);

    List<BasicDayAvailability> findByProfessorIdAndWeekNumberAndDay(Long id, Integer week, Integer day);

    DateTimeProjection findDateTimeProjectionById(Long id);
}
