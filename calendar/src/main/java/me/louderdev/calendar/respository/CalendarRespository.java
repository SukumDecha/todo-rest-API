package me.louderdev.calendar.respository;

import me.louderdev.calendar.model.Calendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRespository extends CrudRepository<Calendar, Long> {


}
