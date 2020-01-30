package ru.unn.agile.figuresvolumecalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {
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
    public void canSetDefaultValues() {
        assertEquals("", viewModel.firstParameterProperty().get());
        assertEquals("", viewModel.secondParameterProperty().get());
        assertEquals(Operation.CALCULATE_PYRAMID_VOLUME, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultFldProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

/*    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }*/

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        viewModel.firstParameterProperty().set("1");
        viewModel.secondParameterProperty().set("2");

        assertEquals(Status.READY.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.firstParameterProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.firstParameterProperty().set("1");

        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.calculationDisabledFlProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.firstParameterProperty().set("trash");

        assertTrue(viewModel.calculationDisabledFlProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.firstParameterProperty().set("1");

        assertTrue(viewModel.calculationDisabledFlProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.calculationDisabledFlProperty().get());
    }

    @Test
    public void canSetCalculatePyramidVolumeOperation() {
        viewModel.operationProperty().set(Operation.CALCULATE_PYRAMID_VOLUME);
        assertEquals(Operation.CALCULATE_PYRAMID_VOLUME, viewModel.operationProperty().get());
    }

    @Test
    public void calculatePyramidVolumeIsDefaultOperation() {
        assertEquals(Operation.CALCULATE_PYRAMID_VOLUME, viewModel.operationProperty().get());
    }

    private void setInputData() {
        viewModel.firstParameterProperty().set("1");
        viewModel.secondParameterProperty().set("12");
    }
}
