package ru.unn.agile.salarycalculator.model;

import java.time.LocalDate;

public class CalendarService {
    private int vacationDuration;
    private static final int VACATION_DEFAULT_YEAR = 1972;
    private LocalDate vacationStartDate;
    private LocalDate month;

    public CalendarService() {
        this.vacationDuration = 0;
        this.vacationStartDate = LocalDate.of(VACATION_DEFAULT_YEAR, 1, 1);
        this.month = LocalDate.now();
    }

    public int getVacationDuration() {
        return vacationDuration;
    }

    public CalendarService setVacationDuration(final int vacationDuration) {
        if (vacationDuration < 0) {
            throw new NumberFormatException("Wrong duration");
        }
        this.vacationDuration = vacationDuration;
        return this;
    }

    private LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public CalendarService setVacationStartDate(final LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
        return this;
    }

    public LocalDate getMonth() {
        return month;
    }

    public CalendarService setMonth(final LocalDate month) {
        this.month = month;
        return this;
    }

    public int countWorkingDaysInMonth() {
        VacationService checkingPeriod = new VacationService();

        checkingPeriod.setMonth(this.getMonth());

        int workingDaysInMonth = 0;
        for (int i = 1; i <= this.getMonth().lengthOfMonth(); i++) {
            if (checkingPeriod.isNotDayOff(i)) {
                workingDaysInMonth++;
            }
        }
        return workingDaysInMonth;
    }

    public int getPayableDaysInMonth() {
        VacationService checkingPeriod = new VacationService();

        checkingPeriod
                .setMonth(this.getMonth())
                .setVacationStartDate(this.getVacationStartDate())
                .setVacationDuration(this.getVacationDuration());
        int payableDaysInMonth = this.countWorkingDaysInMonth();
        if (!this.isCountYearNotVacationYear()) {
            return payableDaysInMonth - checkingPeriod.getHolidaysInVacation();
        }
        return payableDaysInMonth;
    }

    private boolean isCountYearNotVacationYear() {
        return this.getMonth().getYear() != this.getVacationStartDate().getYear();
    }
}
