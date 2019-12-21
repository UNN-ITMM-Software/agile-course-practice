package ru.unn.agile.salarycalculator.viewmodel.legacy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.salarycalculator.viewmodel.legacy.ViewModel.Status;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUpEmptyExample() {
        viewModel = new ViewModel();
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("154");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");
    }

    @After
    public void deleteExample() {
        viewModel = null;
    }

    @Test
    public void checkStatusInBegin() {
        viewModel = new ViewModel();
        assertEquals(Status.COUNT_WAITING, viewModel.getStatus());
    }

    @Test
    public void checkSetters() {
        viewModel.setSalary("10000");
        viewModel.setWorkedHours("145");
        viewModel.setCountMonth("5");
        viewModel.setCountYear("2000");

        assertEquals("10000", viewModel.getSalary());
        assertEquals("145", viewModel.getWorkedHours());
        assertEquals("5", viewModel.getCountMonth());
        assertEquals("2000", viewModel.getCountYear());
    }

    @Test
    public void checkStatusWhenReadyCalculate() {
        viewModel.checkCountFields();

        assertEquals(Status.READY_CALCULATE, viewModel.getStatus());
    }

    @Test
    public void checkStatusCash() {
        viewModel.checkCountFields();
        viewModel.calculateSalary();

        assertEquals(Status.CASH, viewModel.getStatus());
    }

    @Test
    public void isLengthCharactersSalaryNotCorrect() {
        viewModel.setSalary("1000000000000000000000000");
        viewModel.checkCountFields();

        assertEquals(Status.BAD_SALARY_FORMAT_NUMBERS, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenOneOfCountFieldEmpty() {
        viewModel.setCountYear("");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithChar() {
        viewModel.setWorkedHours("a");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_WORKED_HOURS_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectMonth() {
        viewModel.setCountMonth("50");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkStatusWhenCountInputWithIncorrectYear() {
        viewModel.setCountYear("19191");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_YEAR_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNormalParameters() {
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("7281.52", viewModel.getResult());
    }

    @Test
    public void checkResultWithOvertime() {
        viewModel.setWorkedHours("200");
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("10213.04", viewModel.getResult());
    }

    @Test
    public void checkResultWithLessTime() {
        viewModel.setWorkedHours("10");
        viewModel.checkCountFields();

        viewModel.calculateSalary();

        assertEquals("472.83", viewModel.getResult());
    }


    @Test
    public void checkResultWithNegativeWorkedHours() {
        viewModel.setWorkedHours("-144");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_WORKED_HOURS_FORMAT, viewModel.getStatus());
    }

    @Test
    public void checkResultWithNegativeSalary() {
        viewModel.setSalary("-10000");

        viewModel.checkCountFields();

        assertEquals(Status.BAD_SALARY_FORMAT_SIGN, viewModel.getStatus());
    }

    @Test
    public void checkStatusAndButtonWhenIncorrectDate() {
        viewModel.setCountMonth("35");
        viewModel.checkCountFields();

        assertEquals(Status.BAD_MONTH_FORMAT, viewModel.getStatus());
    }
}
