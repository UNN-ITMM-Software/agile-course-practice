package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VacationServiceTest {
    @Test
    public void canCheckNotDayOff() {
        VacationService service = new VacationService();
        service.setMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        assertTrue(service.isNotDayOff(3));
    }

    @Test
    public void canCheckDayOff() {
        VacationService service = new VacationService();
        service.setMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        assertFalse(service.isNotDayOff(22));
    }

    @Test
    public void canCheckDayForSubtractionWithoutVacation() {
        VacationService service = new VacationService();
        service.setMonth(LocalDate.of(2018, Month.AUGUST, 1));
        assertEquals(0, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckDayForSubtractionWithSameVacationMonth() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.AUGUST, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.AUGUST, 4));
        service.setVacationDuration(14);

        assertEquals(10, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckDayForSubtractionWithDifferentVacationMonth() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.AUGUST, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.JULY, 28));
        service.setVacationDuration(14);

        assertEquals(8, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckSubtractionDayWithStartInCountMonthButEndInAnother() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.AUGUST, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.AUGUST, 25));
        service.setVacationDuration(14);

        assertEquals(5, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckSubtractionDayWithVacationInMiddleOfMonth() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.SEPTEMBER, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.SEPTEMBER, 10));
        service.setVacationDuration(14);

        assertEquals(10, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckSubtractionDayWithVacationEndInMiddleOfAnotherMonth() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.OCTOBER, 28));
        service.setVacationDuration(14);

        assertEquals(7, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckSubtractionDayWithVacationStartInMiddleOfCountMonth() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.MARCH, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.MARCH, 21));
        service.setVacationDuration(14);

        assertEquals(8, service.getHolidaysInVacation());
    }

    @Test
    public void canCheckSubtractionDayWhenAllMonthInVacation() {
        VacationService service = new VacationService();

        service.setMonth(LocalDate.of(2018, Month.JULY, 1));
        service.setVacationStartDate(LocalDate.of(2018, Month.JUNE, 19));
        service.setVacationDuration(50);

        assertEquals(22, service.getHolidaysInVacation());
    }
}
