package me.louderdev.calendar.controller;

import jakarta.validation.Valid;
import me.louderdev.calendar.model.Calendar;
import me.louderdev.calendar.service.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class CalendarController {

    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping
    public String getIndex() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Calendar> calendarList = (List<Calendar>) calendarService.getCalendars();
        model.addAttribute("calendars",
                calendarList.isEmpty() ? Collections.EMPTY_LIST : calendarList);
        return "index";
    }

    @GetMapping("/addcalendar")
    public String showAddCalendarForm(Calendar calendar) {
        return "add-calendar";
    }

    @PostMapping("/addcalendar")
    public String addCalendar(@Valid Calendar calendar, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-calendar";
        }
        calendarService.createCalendar(calendar);
        model.addAttribute("calendars", calendarService.getCalendars());
        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("calendar", calendarService.getCalendarByID(id));
        return "update-calendar";
    }

    @PutMapping("/update/{id}")
    public String updateCalendar(@PathVariable("id") Long id, Calendar calendar, BindingResult result, Model model) {
        if(result.hasErrors()) {
            calendar.setId(id);
            return "update-calendar";
        }

        calendarService.updateCalendar(id, calendar);
        model.addAttribute("calendars", calendarService.getCalendars());
        return "redirect:/index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCalendar(@PathVariable("id") Long id, Model model) {
        calendarService.deleteCalendar(id);
        model.addAttribute("calendars", calendarService.getCalendars());
        return "redirect:/index";
    }

}
