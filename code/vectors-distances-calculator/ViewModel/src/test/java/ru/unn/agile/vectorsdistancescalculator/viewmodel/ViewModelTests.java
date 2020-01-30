package ru.unn.agile.vectorsdistancescalculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.vectorsdistancescalculator.model.VectorsDistancesCalculator.Operation;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {

    private static final double EPS = 0.01;

    private ViewModel viewModel;

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    public void setOutsideViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.x1Property().get());
        assertEquals("", viewModel.y1Property().get());
        assertEquals("", viewModel.z1Property().get());
        assertEquals("", viewModel.x2Property().get());
        assertEquals("", viewModel.y2Property().get());
        assertEquals("", viewModel.z2Property().get());
        assertEquals(Operation.CALCULATE_L1_DISTANCE, viewModel.operationProperty().get());
        assertEquals("", viewModel.resultFieldProperty().get());
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.x1Property().set("@");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.x1Property().set("2");
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledByInit() {
        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setPositiveInputVectors();
        viewModel.x1Property().set("KABO");

        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledIfNotAllFieldsFilled() {
        viewModel.x1Property().setValue("1");
        assertTrue(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledIfDataFilledCorrect() {
        setMixedInputVectors();
        assertFalse(viewModel.calculationDisabledFlagProperty().get());
    }

    @Test
    public void canSetCalculateLInfDistanceOperation() {
        viewModel.operationProperty().set(Operation.CALCULATE_LInf_DISTANCE);
        assertEquals(Operation.CALCULATE_LInf_DISTANCE, viewModel.operationProperty().get());
    }

    @Test
    public void calculateL1DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.calculate();
        double expectedL1 = 27.0;
        double actualL1 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL1, actualL1, EPS);
    }

    @Test
    public void calculateL2DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L2_DISTANCE);
        viewModel.calculate();
        double expectedL2 = 15.84;
        double actualL2 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL2, actualL2, EPS);
    }

    @Test
    public void calculateL3DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L3_DISTANCE);
        viewModel.calculate();
        double expectedL3 = 13.39;
        double actualL3 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL3, actualL3, EPS);
    }

    @Test
    public void calculateL4DistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_L4_DISTANCE);
        viewModel.calculate();
        double expectedL4 = 12.39;
        double actualL4 = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedL4, actualL4, EPS);
    }

    @Test
    public void calculateLInfDistanceOperationsHasCorrectResult() {
        setMixedInputVectors();
        viewModel.operationProperty().set(Operation.CALCULATE_LInf_DISTANCE);
        viewModel.calculate();
        double expectedLInf = 11.0;
        double actualLInf = Double.parseDouble(viewModel.resultFieldProperty().get());
        assertEquals(expectedLInf, actualLInf, EPS);
    }

    @Test
    public void canSetReadyMessage() {
        setMixedInputVectors();
        String expected = Status.READY.toString();
        String actual = viewModel.statusFieldProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetSuccessMessage() {
        setMixedInputVectors();
        viewModel.calculate();
        String expected = Status.SUCCESS.toString();
        String actual = viewModel.statusFieldProperty().get();
        assertEquals(expected, actual);
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.x1Property().set("@");
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusFieldProperty().get());
    }

    private void setPositiveInputVectors() {
        viewModel.x1Property().set("2");
        viewModel.y1Property().set("3");
        viewModel.z1Property().set("4");
        viewModel.x2Property().set("5");
        viewModel.y2Property().set("6");
        viewModel.z2Property().set("7");
    }

    private void setMixedInputVectors() {
        viewModel.x1Property().set("-2");
        viewModel.y1Property().set("3");
        viewModel.z1Property().set("-4");
        viewModel.x2Property().set("5");
        viewModel.y2Property().set("-6");
        viewModel.z2Property().set("7");
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void canCreateViewModel() {
        ViewModel viewModel = new ViewModel();

        assertNotNull(viewModel);
    }

    @Test
    public void canSetDefaultLog() {
        assertEquals(0, viewModel.getLog().size());
    }

    @Test
    public void logIsEmptyInTheStarting() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsCorrectMessageAfterCalculation() {
        setMixedInputVectors();
        viewModel.calculate();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CALCULATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsCorrectInputArgumentsAfterCalculation() {
        setMixedInputVectors();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.x1Property().get()
                + ".*" + viewModel.y1Property().get()
                + ".*" + viewModel.z1Property().get()
                + ".*" + viewModel.x2Property().get()
                + ".*" + viewModel.y2Property().get()
                + ".*" + viewModel.z2Property().get() + ".*"));
    }

    @Test
    public void argumentsInfoIsCorrectlyFormatted() {
        setMixedInputVectors();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Arguments"
                + ": X1 = "  + viewModel.x1Property().get()
                + "; Y1 = "  + viewModel.y1Property().get()
                + "; Z1 = "  + viewModel.z1Property().get()
                + "; X2 = "  + viewModel.x2Property().get()
                + "; Y2 = "  + viewModel.y2Property().get()
                + "; Z2 = "  + viewModel.z2Property().get() + ".*"));
    }

    @Test
    public void operationTypeIsAdvertededInTheLog() {
        setMixedInputVectors();

        viewModel.calculate();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*Calculate L1 Distance.*"));
    }

    @Test
    public void canPutSomeLogMessages() {
        setMixedInputVectors();

        viewModel.calculate();
        viewModel.calculate();
        viewModel.calculate();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void canSeeWhenOperationChangeInLog() {
        setMixedInputVectors();

        viewModel.onOperationChanged(Operation.CALCULATE_L1_DISTANCE,
                Operation.CALCULATE_L2_DISTANCE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.OPERATION_WAS_CHANGED
                + "Calculate L2 Distance.*"));
    }

    @Test
    public void operationIsNotLoggedIfNotChanged() {
        viewModel.onOperationChanged(Operation.CALCULATE_L1_DISTANCE,
                Operation.CALCULATE_L2_DISTANCE);

        viewModel.onOperationChanged(Operation.CALCULATE_L2_DISTANCE,
                Operation.CALCULATE_L2_DISTANCE);

        assertEquals(1, viewModel.getLog().size());
    }

    @Test
    public void argumentsAreProperlyLogged() {
        setMixedInputVectors();

        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + LogMessages.EDITING_FINISHED
                + "Input arguments are: \\["
                + viewModel.x1Property().get() + "; "
                + viewModel.y1Property().get() + "; "
                + viewModel.z1Property().get() + "; "
                + viewModel.x2Property().get() + "; "
                + viewModel.y2Property().get() + "; "
                + viewModel.z2Property().get() + "\\]"));
    }

    @Test
    public void calculateIsNotCalledWhenButtonIsDisabled() {
        viewModel.calculate();

        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void doNotLogSameParametersTwiceWithPartialInput() {
        viewModel.x1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);
        viewModel.x1Property().set("12");
        viewModel.onFocusChanged(Boolean.TRUE, Boolean.FALSE);

        assertEquals(1, viewModel.getLog().size());
    }

}
