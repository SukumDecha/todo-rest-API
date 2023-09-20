package me.louderdev.calendar.service;

import me.louderdev.calendar.model.Calendar;

import java.util.Optional;

public interface CalendarService {

    Calendar createCalendar(Calendar calendar);

    void updateCalendar(long id, Calendar calendar);

    Iterable<Calendar> getCalendars();

    Optional<Calendar> getCalendarByID(long id);

    void deleteCalendar(long id);
}
