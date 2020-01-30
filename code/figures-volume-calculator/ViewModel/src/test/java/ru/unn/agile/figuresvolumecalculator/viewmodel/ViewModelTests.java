package ru.unn.agile.figuresvolumecalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.figuresvolumecalculator.model.FiguresVolumeCalculator.Operation;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;


    @After
    public void tearItDown() {
        viewModel = null;
    }

    @Before
    public void setItUp() {
        viewModel = new ViewModel();
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

    @Test
    public void operationCalculatePyramidVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("1");
        viewModel.secondParameterProperty().set("12");

        viewModel.calculate();

        assertEquals("4.0", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculatePyramidVolumeWithNegativeHeightHasCorrectResult() {
        viewModel.firstParameterProperty().set("1");
        viewModel.secondParameterProperty().set("-12");
        viewModel.operationProperty().set(Operation.CALCULATE_PYRAMID_VOLUME);

        viewModel.calculate();

        assertEquals("4.0", viewModel.resultFldProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.firstParameterProperty().set("bad_test");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(Status.READY.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void operationCalculateCylinderVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("3");
        viewModel.operationProperty().set(Operation.CALCULATE_CYLINDER_VOLUME);

        viewModel.calculate();

        assertEquals("84.82300164692441", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateCylinderVolumeWithNegativeHeightHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("-3");
        viewModel.operationProperty().set(Operation.CALCULATE_CYLINDER_VOLUME);

        viewModel.calculate();

        assertEquals("84.82300164692441", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateConeVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("3");
        viewModel.operationProperty().set(Operation.CALCULATE_CONE_VOLUME);

        viewModel.calculate();

        assertEquals("28.274333882308138", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateConeVolumeWithNegativeHeightHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("-3");
        viewModel.operationProperty().set(Operation.CALCULATE_CONE_VOLUME);

        viewModel.calculate();

        assertEquals("28.274333882308138", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateParallelipipedVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("3");
        viewModel.operationProperty().set(Operation.CALCULATE_PARALLELEPIPED_VOLUME);

        viewModel.calculate();

        assertEquals("9.0", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateParallelipipedVolumeWithNegativeHeightHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.secondParameterProperty().set("-3");
        viewModel.operationProperty().set(Operation.CALCULATE_PARALLELEPIPED_VOLUME);

        viewModel.calculate();

        assertEquals("9.0", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateSphereVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("3");
        viewModel.operationProperty().set(Operation.CALCULATE_SPHERE_VOLUME);

        viewModel.calculate();

        assertEquals("113.09733552923254", viewModel.resultFldProperty().get());
    }

    @Test
    public void operationCalculateOctahedronVolumeHasCorrectResult() {
        viewModel.firstParameterProperty().set("1");
        viewModel.operationProperty().set(Operation.CALCULATE_OCTAHEDRON_VOLUME);

        viewModel.calculate();

        assertEquals("0.4714045207910317", viewModel.resultFldProperty().get());
    }

    private void setInputData() {
        viewModel.firstParameterProperty().set("1");
        viewModel.secondParameterProperty().set("12");
    }
}
