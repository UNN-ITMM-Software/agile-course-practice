package ru.unn.agile.fractioncalculator.viewmodel;

import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.fractioncalculator.model.Fraction;

import static org.junit.Assert.*;

public class ViewModelTest {

    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canGetFirstFraction() {
        assertNotNull(viewModel.firstFractionProperty());
    }

    @Test
    public void canGetSecondFraction() {
        assertNotNull(viewModel.secondFractionProperty());
    }

    @Test
    public void canSetFirstFraction() {
        String expected = "11/22";
        viewModel.setFirstFraction(expected);
        String actual = viewModel.firstFractionProperty().get();

        assertEquals(expected, actual);
    }

    @Test
    public void canSetSecondFraction() {
        String expected = "33/44";
        viewModel.setSecondFraction(expected);
        String actual = viewModel.secondFractionProperty().get();

        assertEquals(expected, actual);
    }
}