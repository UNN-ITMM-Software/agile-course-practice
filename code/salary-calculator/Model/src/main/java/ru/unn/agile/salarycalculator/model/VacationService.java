package ru.unn.agile.salarycalculator.model;

import java.time.LocalDate;

public class VacationService {
    private static final int VACATION_DEFAULT_YEAR = 2010;
    private static final int SUNDAY_NUMBER_DAY_OF_WEEK = 7;
    private static final int SATURDAY_NUMBER_DAY_OF_WEEK = 6;
    private int lengthOfVacation = 0;
    private LocalDate checkMonth;
    private LocalDate startVacation = LocalDate.of(VACATION_DEFAULT_YEAR, 1, 1);
    private LocalDate endVacation;
    private int dayStartSubtract;
    private int dayEndSubtract;

    public int getHolidaysInVacation() {
        endVacation = this.startVacation.plusDays(lengthOfVacation);
        dayStartSubtract = this.getStartDay();
        dayEndSubtract = this.getEndDay();
        return sumVacationDaysToSubtract();
    }

    public boolean isNotDayOff(final int checkDayNumber) {
        LocalDate checkDate = LocalDate.of(this.checkMonth.getYear(),
                checkMonth.getMonth(),
                checkDayNumber);
        return isDayNotWeekend(checkDate) && isDayNotSunday(checkDate);
    }

    public VacationService setMonth(final LocalDate inCheckMonth) {
        this.checkMonth = inCheckMonth;
        return this;
    }

    public VacationService setVacationStartDate(final LocalDate inStartVacation) {
        startVacation = inStartVacation;
        return this;
    }

    public VacationService setVacationDuration(final int inLengthOfVacation) {
        lengthOfVacation = inLengthOfVacation;
        return this;
    }

    private int sumVacationDaysToSubtract() {
        if (checkMonth.getYear() != startVacation.getYear()) {
            return 0;
        }
        int dayToSubtract = 0;
        for (int i = dayStartSubtract; i < dayEndSubtract; i++) {
            if (isNotDayOff(i)) {
                dayToSubtract++;
            }
        }
        if (isExtraSituationWithEndOfSubtract()) {
            dayToSubtract++;
        }
        return dayToSubtract;
    }

    private int getStartDay() {
        if (isAllSalaryMonthInVacation() || vacationInAnotherMonthButEndInCashMonth()) {
            return 1;
        }
        if (isAllVacationInCashMonth() || vacationInCashMonthButEndInAnother()) {
            return startVacation.getDayOfMonth();
        }
        return 0;
    }

    private int getEndDay() {
        if (isAllSalaryMonthInVacation() || vacationInCashMonthButEndInAnother()) {
            return checkMonth.lengthOfMonth();
        }
        if (isAllVacationInCashMonth() || vacationInAnotherMonthButEndInCashMonth()) {
            return endVacation.getDayOfMonth();
        }
        return 0;
    }

    private boolean isExtraSituationWithEndOfSubtract() {
        return vacationInCashMonthButEndInAnother() && isNotDayOff(startVacation.lengthOfMonth())
                || isAllSalaryMonthInVacation() && isNotDayOff(startVacation.lengthOfMonth());
    }

    private boolean isAllSalaryMonthInVacation() {
        return startVacation.getMonth().getValue() < checkMonth.getMonth().getValue()
                && endVacation.getMonth().getValue() > checkMonth.getMonth().getValue();
    }

    private boolean vacationInAnotherMonthButEndInCashMonth() {
        return !isCountMonthEqualStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean vacationInCashMonthButEndInAnother() {
        return isCountMonthEqualStartVacationMonth() && !isCountMonthEqualVacationEndMonth();
    }

    private boolean isAllVacationInCashMonth() {
        return isCountMonthEqualStartVacationMonth() && isCountMonthEqualVacationEndMonth();
    }

    private boolean isCountMonthEqualVacationEndMonth() {
        return checkMonth.getMonth() == endVacation.getMonth();
    }

    private boolean isCountMonthEqualStartVacationMonth() {
        return checkMonth.getMonth() == startVacation.getMonth();
    }

    private static boolean isDayNotSunday(final LocalDate checkDay) {
        return checkDayOfWeek(checkDay) != SUNDAY_NUMBER_DAY_OF_WEEK;
    }

    private static boolean isDayNotWeekend(final LocalDate checkDay) {
        return checkDayOfWeek(checkDay) != SUNDAY_NUMBER_DAY_OF_WEEK
                && checkDayOfWeek(checkDay) != SATURDAY_NUMBER_DAY_OF_WEEK;
    }

    private static int checkDayOfWeek(final LocalDate checkDay) {
        return checkDay.getDayOfWeek().getValue();
    }
}
