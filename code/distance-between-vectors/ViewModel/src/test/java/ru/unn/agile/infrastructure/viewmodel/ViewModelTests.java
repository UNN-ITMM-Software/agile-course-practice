package ru.unn.agile.infrastructure.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    private final double comparisonDELTA = 0.001;

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
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
        assertEquals("", viewModel.messageProperty().get());
        assertEquals("", viewModel.resultL1Property().get());
        assertEquals("", viewModel.resultL2Property().get());
        assertEquals("", viewModel.resultL3Property().get());
        assertEquals("", viewModel.resultL4Property().get());
        assertEquals("", viewModel.resultLinfProperty().get());
    }

    @Test
    public void canStopAndReturnFalseWhenNotNumber() {
        viewModel.coordXProperty().set("a");
        viewModel.coordYProperty().set("5");
        viewModel.coordZProperty().set("5");
        Boolean b = viewModel.calculate();
        assertEquals(false, b);
    }

    @Test
    public void canReturnErrorInCalcMetricsWhenNotNumber() {
        viewModel.coordXProperty().set("a");
        viewModel.coordYProperty().set("5");
        viewModel.coordZProperty().set("5");
        StringProperty error = new SimpleStringProperty();
        Boolean b = viewModel.calculate();
        assertEquals("ERROR", viewModel.resultL1Property().getValue());
        assertEquals("ERROR", viewModel.resultL2Property().getValue());
        assertEquals("ERROR", viewModel.resultL3Property().getValue());
        assertEquals("ERROR", viewModel.resultL4Property().getValue());
        assertEquals("ERROR", viewModel.resultLinfProperty().getValue());
    }

    @Test
    public void canResultFloatResult() {
        ILogger logger;
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
    public void falseWhenHaveNotValue() {
        Boolean b = viewModel.calculate();
        assertEquals(false, b);
    }

    @Test
    public void errorWhenHaveNotValue() {
        Boolean b = viewModel.calculate();
        assertEquals(viewModel.resultL1Property().getValue(), "ERROR");
        assertEquals(viewModel.resultL2Property().getValue(), "ERROR");
        assertEquals(viewModel.resultL3Property().getValue(), "ERROR");
        assertEquals(viewModel.resultL4Property().getValue(), "ERROR");
        assertEquals(viewModel.resultLinfProperty().getValue(), "ERROR");
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

    @Test
    public void messageIsNotEmptyWhenUncorrect() {
        viewModel.coordXProperty().set("f");
        viewModel.coordYProperty().set("2");
        viewModel.coordZProperty().set("z");
        viewModel.calculate();
        Boolean notNull = false;
        Integer lengthOfMessage = viewModel.messageProperty().length().getValue();
        if (lengthOfMessage != 0) {
            notNull = true;
        }
        assertTrue(notNull);
    }

    @Test
    public void messageIsEmptyWhenCorrect() {
        viewModel.coordXProperty().set("2");
        viewModel.coordYProperty().set("2");
        viewModel.coordZProperty().set("2");
        viewModel.calculate();
        Boolean notNull = false;
        Integer lengthOfMessage = viewModel.messageProperty().length().getValue();
        if (lengthOfMessage != 0) {
            notNull = true;
        }
        assertFalse(notNull);
    }
}
