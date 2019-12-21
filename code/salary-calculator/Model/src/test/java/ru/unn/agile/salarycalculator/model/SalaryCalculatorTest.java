package ru.unn.agile.salarycalculator.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class SalaryCalculatorTest {
    private final double delta = 0.01;

    private static void setDataForTest(final SalaryCalculator calculator) {
        calculator.setSalary(18000);
        calculator.setCountingMonth(LocalDate.of(2018, Month.OCTOBER, 1));
    }

    @Test
    public void canSetSalary() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setSalary(1000);
        assertEquals(1000, salaryCalculator.getSalary(), delta);
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetNegativeSalary() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setSalary(-1);
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetSalaryMoreThanMaxSalary() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setSalary(1000000000);
    }

    @Test
    public void canSetWorkedHoursPerMonth() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setWorkedHoursPerMonth(100);
        assertEquals(100, salaryCalculator.getWorkedHoursPerMonth());
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetNegativeWorkedHoursPerMonth() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setWorkedHoursPerMonth(-1);
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetHoursPerMonthMoreThanMaxValue() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setWorkedHoursPerMonth(401);
    }

    @Test
    public void canSetVacationDuration() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setVacationDuration(10);
        assertEquals(10, salaryCalculator.getVacationDuration());
    }

    @Test (expected = NumberFormatException.class)
    public void canNotSetNegativeVacationDuration() {
        SalaryCalculator salaryCalculator = new SalaryCalculator();
        salaryCalculator.setVacationDuration(-1);
    }

    @Test
    public void canCalculateSalaryInNormalMonth() {
        SalaryCalculator calculator = new SalaryCalculator();
        calculator.setSalary(18000);
        calculator.setCountingMonth(LocalDate.of(2019, Month.OCTOBER, 1));
        calculator.setWorkedHoursPerMonth(184);

        assertEquals(15660.0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInNormalMonthWithOvertime() {
        SalaryCalculator calculator = new SalaryCalculator();
        calculator.setSalary(18000);
        calculator.setCountingMonth(LocalDate.of(2019, Month.OCTOBER, 1));
        calculator.setWorkedHoursPerMonth(200);

        assertEquals(18383.48, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInNormalMonthWithLessHours() {
        SalaryCalculator calculator = new SalaryCalculator();
        calculator.setSalary(18000);
        calculator.setCountingMonth(LocalDate.of(2019, Month.OCTOBER, 1));
        calculator.setWorkedHoursPerMonth(160);

        assertEquals(13617.4, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInMonthWith14DaysOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();
        calculator.setSalary(18000);
        calculator.setWorkedHoursPerMonth(104);
        calculator.setVacationStartDate(LocalDate.of(2018, Month.NOVEMBER, 6));
        calculator.setVacationDuration(14);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));

        assertEquals(9965.45, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInMonthWithStartOfVacationAndFinishInAnother() {
        SalaryCalculator calculator = new SalaryCalculator();

        setDataForTest(calculator);
        calculator.setWorkedHoursPerMonth(144);
        calculator.setVacationStartDate(LocalDate.of(2018, Month.OCTOBER, 27));
        calculator.setVacationDuration(14);
        calculator.setCountingMonth(LocalDate.of(2018, Month.OCTOBER, 1));

        assertEquals(12255.65, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void canCalculateSalaryInMonthWithEndOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setDataForTest(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        calculator.setWorkedHoursPerMonth(120);
        calculator.setVacationStartDate(LocalDate.of(2018, Month.OCTOBER, 27));
        calculator.setVacationDuration(14);

        assertEquals(10677.27, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWhichAllInVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setDataForTest(calculator);
        calculator.setWorkedHoursPerMonth(0);
        calculator.setVacationStartDate(LocalDate.of(2019, Month.SEPTEMBER, 27));
        calculator.setVacationDuration(50);
        calculator.setCountingMonth(LocalDate.of(2018, Month.OCTOBER, 1));

        assertEquals(0.0, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWhichInYearNotEqualYearOfVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setDataForTest(calculator);
        calculator.setCountingMonth(LocalDate.of(2019, Month.NOVEMBER, 1));
        calculator.setWorkedHoursPerMonth(160);
        calculator.setVacationStartDate(LocalDate.of(2020, Month.OCTOBER, 27));
        calculator.setVacationDuration(14);

        assertEquals(14914.28, calculator.calculateSalaryWithNDS(), delta);
    }

    @Test
    public void countSalaryInMonthWithOvertimeAndWithoutVacation() {
        SalaryCalculator calculator = new SalaryCalculator();

        setDataForTest(calculator);
        calculator.setCountingMonth(LocalDate.of(2018, Month.NOVEMBER, 1));
        calculator.setWorkedHoursPerMonth(160);

        assertEquals(14236.36, calculator.calculateSalaryWithNDS(), delta);
    }

}
