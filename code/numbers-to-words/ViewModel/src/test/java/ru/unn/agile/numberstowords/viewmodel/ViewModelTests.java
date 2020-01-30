package ru.unn.agile.numberstowords.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        FakeLogger logger = new FakeLogger();
        viewModel = new ViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.numberInputProperty().get());
        assertEquals("", viewModel.textOutputProperty().get());
        assertEquals("", viewModel.statusProperty().get());
    }

    @Test
    public void setErrorMessageWithEmptyInput() {
        viewModel.convert();

        assertEquals(Status.EMPTY_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void setErrorMessageWithIncorrectInput() {
        viewModel.numberInputProperty().set("qwe");

        viewModel.convert();

        assertEquals(Status.WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertingHasCorrectResult() {
        viewModel.numberInputProperty().set("15");

        viewModel.convert();

        assertEquals("fifteen", viewModel.textOutputProperty().get());
    }

    @Test
    public void setErrorMessageWithNotInteger() {
        viewModel.numberInputProperty().set("15.5");

        viewModel.convert();

        assertEquals(Status.WRONG_INPUT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void clearErrorMessageWithCorrectInput() {
        viewModel.numberInputProperty().set("wrong");
        viewModel.convert();

        viewModel.numberInputProperty().set("1");
        viewModel.convert();

        assertEquals("", viewModel.statusProperty().get());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type when logger is null");
        }
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertEquals(0, log.size());
    }

    @Test
    public void convertingIsLogged() {
        viewModel.convert();
        List<String> log = viewModel.getLog();

        assertNotEquals(0, log.size());
    }

    @Test
    public void logContainsPressInformation() {
        viewModel.convert();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*"
                + LogMessages.CONVERT_WAS_PRESSED.toString() + ".*"));
    }

    @Test
    public void logContainsInputInformation() {
        viewModel.numberInputProperty().set("42");
        viewModel.convert();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + "Input: \"42\"." + ".*"));
    }

    @Test
    public void canPutSeveralMessages() {
        viewModel.convert();
        viewModel.convert();
        viewModel.convert();
        List<String> log = viewModel.getLog();

        assertEquals(6, log.size());
    }

    @Test
    public void logContainsConvertingEmptyInputInformation() {
        viewModel.convert();
        String message = viewModel.getLog().get(viewModel.getLog().size() - 1);

        assertTrue(message.matches(".*"
                + LogMessages.EMPTY_INPUT.toString() + ".*"));
    }

    @Test
    public void logContainsConvertingWrongInputInformation() {
        viewModel.numberInputProperty().set("wrong");
        viewModel.convert();
        String message = viewModel.getLog().get(viewModel.getLog().size() - 1);

        assertTrue(message.matches(".*"
                + LogMessages.WRONG_INPUT.toString() + ".*"));
    }

    @Test
    public void logContainsConvertingCorrectInputInformation() {
        viewModel.numberInputProperty().set("42");
        viewModel.convert();
        String message = viewModel.getLog().get(viewModel.getLog().size() - 1);

        assertTrue(message.matches(".*"
                + LogMessages.CONVERT_WAS_COMPLETED.toString() + ".*"));
    }
}
