package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Month;

public class CalendarServiceTest {

    @Test
    public void canSetMonth() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.now());
        assertEquals(LocalDate.now(), service.getMonth());
    }

    @Test
    public void canSetVacationDuration() {
        CalendarService service = new CalendarService();
        service.setVacationDuration(10);
        assertEquals(10, service.getVacationDuration());
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetNegativeVacationDuration() {
        CalendarService service = new CalendarService();
        service.setVacationDuration(-1);
    }

    @Test
    public void canCalculatePayableDaysWithoutVacation() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.of(2019, Month.APRIL, 1));
        assertEquals(22, service.getPayableDaysInMonth());
    }

    @Test
    public void canCalculatePayableDaysWithVacation() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.of(2019, Month.SEPTEMBER, 1));
        service.setVacationStartDate(LocalDate.of(2019, Month.SEPTEMBER, 8));
        service.setVacationDuration(4);
        assertEquals(18, service.getPayableDaysInMonth());
    }

    @Test
    public void canCalculatePayableDaysInCurrentMonthWithEndOfVacationInNextMonth() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.of(2019, Month.AUGUST, 1));
        service.setVacationStartDate(LocalDate.of(2019, Month.AUGUST, 25));
        service.setVacationDuration(10);
        assertEquals(17, service.getPayableDaysInMonth());
    }

    @Test
    public void canCalculatePayableDaysInMonthWhenVacationInAnotherYear() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.of(2019, Month.AUGUST, 1));
        service.setVacationStartDate(LocalDate.of(2020, Month.AUGUST, 3));
        assertEquals(22, service.getPayableDaysInMonth());
    }

    @Test
    public void canCalculatePayableDaysWhenEntireMonthIsInVacation() {
        CalendarService service = new CalendarService();
        service.setMonth(LocalDate.of(2019, Month.APRIL, 1));
        service.setVacationStartDate(LocalDate.of(2019, Month.APRIL, 1));
        service.setVacationDuration(31);
        assertEquals(0, service.getPayableDaysInMonth());
    }
}
