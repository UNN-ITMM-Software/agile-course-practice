package ru.unn.agile.salarycalculator.viewmodel.legacy;

import ru.unn.agile.salarycalculator.model.SalaryCalculator;

import java.time.LocalDate;
import java.util.Locale;

public class ViewModel {
    private static final int MAX_MONTH = 12;
    private static final int MAX_YEAR = 2019;
    private static final int MIN_YEAR = 2000;
    private static final int MAX_WORKED_HOURS = 500;
    private static final int MAX_COUNT_CHARACTERS_SALARY = 20;
    private String salary;
    private String workedHours;
    private String countMonth;
    private String countYear;
    private String result;
    private String status;
    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        salary = "";
        workedHours = "";
        countMonth = "";
        countYear = "";
        result = "";
        status = Status.COUNT_WAITING;
        isCalculateButtonEnabled = false;
    }

    public final class Status {
        public static final String COUNT_WAITING = "Please provide salary and count period";
        public static final String READY_CALCULATE = "Press 'Calculate' button";
        public static final String BAD_SALARY_FORMAT_SIGN = "Salary must be more 0";
        public static final String BAD_SALARY_FORMAT_NUMBERS = "Salary too much";
        public static final String BAD_COUNT_FORMAT = "Wrong format of count input";
        public static final String BAD_MONTH_FORMAT = "Month must be between 1 and 12";
        public static final String BAD_YEAR_FORMAT = "Year must be between 2000 and 2019";
        public static final String BAD_WORKED_HOURS_FORMAT =
                "Worked houses must be between 1 and 500";
        public static final String CASH = "This your cash";

        private Status() {

        }
    }

    public void checkCountFields() {
        status = Status.READY_CALCULATE;
        isCalculateButtonEnabled = isCurrentYearNumberCorrect()
                && isCurrentMonthNumberCorrect()
                && isWorkedHoursCorrect()
                && isSalaryCorrect();
    }

    private boolean isSalaryCorrect() {
        if (salary.length() > MAX_COUNT_CHARACTERS_SALARY) {
            status = Status.BAD_SALARY_FORMAT_NUMBERS;
            return false;
        }
        try {
            if (Double.parseDouble(salary) < 0) {
                status = Status.BAD_SALARY_FORMAT_SIGN;
                return false;
            }
        } catch (NumberFormatException e) {
            status = Status.BAD_COUNT_FORMAT;
            return false;
        }

        return true;
    }

    public void calculateSalary() {
        if (!isCalculateButtonEnabled) {
            return;
        }
        SalaryCalculator calculator = new SalaryCalculator();

        calculator.setSalary(Double.parseDouble(salary));

        calculator.setWorkedHoursPerMonth(Integer.parseInt(workedHours));

        calculator.setCountingMonth(LocalDate.of(Integer.parseInt(countYear),
                Integer.parseInt(countMonth), 1));
        result = getMoneyFormatInCashValue(calculator);
        status = Status.CASH;
    }

    private String getMoneyFormatInCashValue(final SalaryCalculator countPeriod) {
        return String.format(Locale.ROOT, "%.2f", countPeriod.calculateSalaryWithNDS());
    }

    public boolean isCalculateButtonEnable() {
        return isCalculateButtonEnabled;
    }

    public void setSalary(final String inSalary) {
        this.salary = inSalary;
    }


    public void setWorkedHours(final String inWorkedHours) {
        this.workedHours = inWorkedHours;
    }

    public void setCountMonth(final String inCountMonth) {
        this.countMonth = inCountMonth;
    }

    public void setCountYear(final String inCountYear) {
        this.countYear = inCountYear;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    private boolean isWorkedHoursCorrect() {
        int hours;
        try {
            hours = Integer.parseInt(workedHours);
        } catch (NumberFormatException e) {
            status = Status.BAD_WORKED_HOURS_FORMAT;
            return false;
        }

        if (hours <= MAX_WORKED_HOURS && hours > 0) {
            return true;
        } else {
            status = Status.BAD_WORKED_HOURS_FORMAT;
            return false;
        }
    }

    private boolean isCurrentMonthNumberCorrect() {
        int checkingMonth;
        try {
            checkingMonth = Integer.parseInt(countMonth);
        } catch (NumberFormatException e) {
            status = Status.BAD_MONTH_FORMAT;
            return false;
        }
        if (checkingMonth <= MAX_MONTH && checkingMonth > 0) {
            return true;
        } else {
            status = Status.BAD_MONTH_FORMAT;
            return false;
        }
    }

    private boolean isCurrentYearNumberCorrect() {
        int checkingYear;
        try {
            checkingYear = Integer.parseInt(countYear);
        } catch (NumberFormatException e) {
            status = Status.BAD_YEAR_FORMAT;
            return false;
        }
        if (checkingYear <= MAX_YEAR && checkingYear > MIN_YEAR) {
            return true;
        } else {
            status = Status.BAD_YEAR_FORMAT;
            return false;
        }
    }

    public String getSalary() {
        return salary;
    }

    public String getWorkedHours() {
        return workedHours;
    }

    public String getCountMonth() {
        return countMonth;
    }

    public String getCountYear() {
        return countYear;
    }
}
