package ru.unn.agile.distancebetweenvector.viewmodel;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final double comparisonDELTA = 0.001;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefault() {
        assertEquals("", viewModel.coordXProperty().get());
        assertEquals("", viewModel.coordYProperty().get());
        assertEquals("", viewModel.coordZProperty().get());
        assertEquals("", viewModel.resultL1Property().get());
        assertEquals("", viewModel.resultL2Property().get());
        assertEquals("", viewModel.resultL3Property().get());
        assertEquals("", viewModel.resultL4Property().get());
        assertEquals("", viewModel.resultLinfProperty().get());
    }

    @Test
    public void canResultFloatResult() {
        viewModel.coordXProperty().set("1");
        viewModel.coordYProperty().set("4");
        viewModel.coordZProperty().set("5");
        viewModel.calculate();
        Float resL1 = Float.parseFloat(viewModel.resultL1Property().getValue());
        Float resL2 = Float.parseFloat(viewModel.resultL2Property().getValue());
        Float resL3 = Float.parseFloat(viewModel.resultL3Property().getValue());
        Float resL4 = Float.parseFloat(viewModel.resultL4Property().getValue());
        Float resLinf = Float.parseFloat(viewModel.resultLinfProperty().getValue());

        assertEquals(10.0f, resL1, comparisonDELTA);
        assertEquals(6.4807f, resL2, comparisonDELTA);
        assertEquals(5.748f, resL3, comparisonDELTA);
        assertEquals(5.449f, resL4, comparisonDELTA);
        assertEquals(5.0f, resLinf, comparisonDELTA);
    }

    @Test
    public void canResultAndReturnTrueWhenCorrectNumberFormat() {
        viewModel.coordXProperty().set("5");
        viewModel.coordYProperty().set("5");
        viewModel.coordZProperty().set("5");
        Boolean b = viewModel.calculate();
        assertTrue(b);
    }

    @Test
    public void canNegativeResultFloatResult() {
        viewModel.coordXProperty().set("-1");
        viewModel.coordYProperty().set("-2");
        viewModel.coordZProperty().set("-2");
        viewModel.calculate();
        Float resultL1 = Float.parseFloat(viewModel.resultL1Property().getValue());
        Float resultL2 = Float.parseFloat(viewModel.resultL2Property().getValue());
        Float resultL3 = Float.parseFloat(viewModel.resultL3Property().getValue());
        Float resultL4 = Float.parseFloat(viewModel.resultL4Property().getValue());
        Float resultLinf = Float.parseFloat(viewModel.resultLinfProperty().getValue());
        assertEquals(5.0f, resultL1, comparisonDELTA);
        assertEquals(3.0f, resultL2, comparisonDELTA);
        assertEquals(2.571f, resultL3, comparisonDELTA);
        assertEquals(2.397f, resultL4, comparisonDELTA);
        assertEquals(-1.0f, resultLinf, comparisonDELTA);
    }
}
