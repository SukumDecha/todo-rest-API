package me.louderdev.calendar.service;

import me.louderdev.calendar.model.Calendar;
import me.louderdev.calendar.respository.CalendarRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ICalendarService implements CalendarService {

    private CalendarRespository calendarRespository;

    @Autowired
    public ICalendarService(CalendarRespository calendarRespository) {
        super();
        this.calendarRespository = calendarRespository;
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarRespository.save(calendar);
    }

    @Override
    public void updateCalendar(long id, Calendar calendar) {
        Optional<Calendar> optionalCalendar = calendarRespository
                .findById(id);
        if(optionalCalendar.isPresent()) {
            Calendar result = optionalCalendar.get();
            result.setContent(calendar.getContent());
            result.setGoWith(calendar.getGoWith());
            result.setStatus(calendar.isStatus());
            result.setDateTime(calendar.getDateTime());
            result.setId(calendar.getId());
            calendarRespository.save(result);
        }
    }

    @Override
    public Iterable<Calendar> getCalendars() {
        return calendarRespository.findAll();
    }

    @Override
    public Optional<Calendar> getCalendarByID(long id) {
        return calendarRespository.findById(id);
    }

    @Override
    public void deleteCalendar(long id) {
        calendarRespository.deleteById(id);
    }
}
