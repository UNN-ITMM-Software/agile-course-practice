package ru.unn.agile.salarycalculator.model;

import java.time.LocalDate;

public class SalaryCalculator {
    private static final double NDS = 0.87;
    private static final int WORK_HOURS_PER_DAY = 8;
    private static final int MAX_SALARY = 100000000;
    private static final int MAX_HOURS_PER_MONTH = 400;
    private double salary;
    private int workedHoursPerMonth;
    private int vacationDuration;
    private LocalDate countingMonth;
    private LocalDate vacationStartDate;

    public SalaryCalculator() {
        this.salary = 0;
        this.workedHoursPerMonth = 0;
        this.vacationDuration = 0;
        this.countingMonth = LocalDate.now();
        this.vacationStartDate = LocalDate.now();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        if (salary < 0) {
            throw new NumberFormatException("Negative salary");
        }
        if (salary > MAX_SALARY) {
            throw new NumberFormatException("Salary is too big, sorry");
        }
        this.salary = salary;
    }

    private LocalDate getCountingMonth() {
        return countingMonth;
    }

    public void setCountingMonth(final LocalDate countingMonth) {
        this.countingMonth = countingMonth;
    }

    public int getWorkedHoursPerMonth() {
        return workedHoursPerMonth;
    }

    public void setWorkedHoursPerMonth(final int workedHoursPerMonth) {
        if (workedHoursPerMonth < 0) {
            throw new NumberFormatException("Worked hours can't be less than zero");
        }
        if (workedHoursPerMonth > MAX_HOURS_PER_MONTH) {
            throw new NumberFormatException("Hours per month went out of maximum");
        }
        this.workedHoursPerMonth = workedHoursPerMonth;
    }

    public int getVacationDuration() {
        return vacationDuration;
    }

    public void setVacationDuration(final int vacationDuration) {
        if (vacationDuration < 0) {
            throw new NumberFormatException("Vacation can't be less than zero");
        }
        this.vacationDuration = vacationDuration;
    }

    private LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(final LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public double calculateSalaryWithNDS() {
        return this.calculateSalaryWithoutNDS() * NDS;
    }

    private double calculateSalaryWithoutNDS() {
        if (this.isEmployeeWorkedMoreThanNormalHoursPerDay()) {
            return this.calculateSalaryWithOvertime();
        }
        if (this.isEmployeeWorkedLessThanNormalHoursPerMonth()) {
            return this.calculateSalaryForLessHours();
        }
        return this.calculateDefaultSalary();
    }

    private double calculateSalaryForLessHours() {
        return this.getWorkedHoursPerMonth() * this.calculateSalaryForOneWorkHour();
    }

    private double calculateDefaultSalary() {
        return this.getDefaultSumOfWorkedHours() * this.calculateSalaryForOneWorkHour();
    }

    private double calculateSalaryWithOvertime() {
        return calculateDefaultSalary() + calculateOvertime();
    }

    private double calculateOvertime() {
        return (this.calculateSalaryForOneWorkHour() * 2)
                * (this.getWorkedHoursPerMonth() - this.getDefaultSumOfWorkedHours());
    }

    private double calculateSalaryForOneWorkHour() {
        int workingDaysPerCountedMonth = new CalendarService()
                .setMonth(this.getCountingMonth()).countWorkingDaysInMonth();
        return this.getSalary() / (workingDaysPerCountedMonth * WORK_HOURS_PER_DAY);
    }

    private int getDefaultSumOfWorkedHours() {
        CalendarService calendarService = new CalendarService()
                .setMonth(this.getCountingMonth())
                .setVacationStartDate(this.getVacationStartDate())
                .setVacationDuration(this.getVacationDuration());
        int payableDaysInMonth = calendarService.getPayableDaysInMonth();
        return payableDaysInMonth * WORK_HOURS_PER_DAY;
    }

    private boolean isEmployeeWorkedLessThanNormalHoursPerMonth() {
        return this.getWorkedHoursPerMonth() < this.getDefaultSumOfWorkedHours();
    }

    private boolean isEmployeeWorkedMoreThanNormalHoursPerDay() {
        return this.getWorkedHoursPerMonth() > this.getDefaultSumOfWorkedHours();
    }
}
