package ru.unn.agile.gameoflife.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
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
    public void defaultHeightIsEmpty() {
        assertEquals("", viewModel.heightFieldProperty().get());
    }

    @Test
    public void defaultWidthIsEmpty() {
        assertEquals("", viewModel.widthFieldProperty().get());
    }

    @Test
    public void defaultStatusIsWaiting() {
        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsWaitingWhenFieldsAreEmpty() {
        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreNotEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");

        assertEquals(Status.READY.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsWaitingWhenFieldsWasDeleted() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.heightFieldProperty().set("");
        viewModel.widthFieldProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void canCreateGrid() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        char[][] grid = {{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', '.'}};

        assertArrayEquals(grid, viewModel.gridArrayProperty());
    }

    @Test
    public void statusIsWaitingWhenCreateGridWithEmptyFields() {
        viewModel.createGrid();

        assertEquals(Status.WAITING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsInitialiseWhenGridIsCreated() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();

        assertEquals(Status.INITIALISE.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void canChangeCellStatusToLive() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);

        assertEquals('*', viewModel.gridArrayProperty()[1][2]);
    }

    @Test
    public void canChangeCellStatusToDead() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);
        viewModel.changeCellStatus(1, 2);

        assertEquals('.', viewModel.gridArrayProperty()[1][2]);
    }

    @Test
    public void statusIsGamingWhenGridIsNotEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);

        assertEquals(Status.GAMING.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsInitialiseWhenGridBecameEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);
        viewModel.changeCellStatus(1, 2);

        assertEquals(Status.INITIALISE.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void canMadeTurn() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);
        viewModel.getNextStep();
        char[][] nextGrid = {{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', '.'}};

        assertArrayEquals(nextGrid, viewModel.gridArrayProperty());
    }

    @Test
    public void statusIsInitialiseWhenGameIsOver() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);
        viewModel.getNextStep();

        assertEquals(Status.INITIALISE.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.heightFieldProperty().set("ab");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsInvalidInputWhenInputIsNegative() {
        viewModel.heightFieldProperty().set("-1");

        assertEquals(Status.IVALID_INPUT.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void statusIsInvalidInputWhenInputIsZero() {
        viewModel.heightFieldProperty().set("0");

        assertEquals(Status.IVALID_INPUT.toString(), viewModel.statusTextProperty().get());
    }

    @Test
    public void defaultCanNotStartCreateGrid() {
        assertTrue(viewModel.couldNotCreateProperty().get());
    }

    @Test
    public void canStartCreateGridWhenFieldsIsNotEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");

        assertFalse(viewModel.couldNotCreateProperty().get());
    }

    @Test
    public void canNotStartCreateGridWhenFieldsBecameEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.heightFieldProperty().set("");
        viewModel.widthFieldProperty().set("");

        assertTrue(viewModel.couldNotCreateProperty().get());
    }

    @Test
    public void defaultCanNotStartGame() {
        assertTrue(viewModel.couldNotGetNextStepProperty().get());
    }

    @Test
    public void canNotStartGameWithoutInitialiseLiveCells() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();

        assertTrue(viewModel.couldNotGetNextStepProperty().get());
    }

    @Test
    public void canStartGameWhenGridIsNotEmpty() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);

        assertFalse(viewModel.couldNotGetNextStepProperty().get());
    }

    @Test
    public void canNotGetNextStepWhenGameOver() {
        viewModel.heightFieldProperty().set("3");
        viewModel.widthFieldProperty().set("3");
        viewModel.createGrid();
        viewModel.changeCellStatus(1, 2);
        viewModel.getNextStep();

        assertTrue(viewModel.couldNotGetNextStepProperty().get());
    }

    @Test
    public void canCreateViewModelWithLogger() {
        FakeLogger logger = new FakeLogger();
        ViewModel viewModelLogged = new ViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void logIsEmptyInTheBeginning() {
        List<String> log = viewModel.getLog();

        assertTrue(log.isEmpty());
    }

    @Test
    public void viewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new ViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger value can not be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void logContainsProperMessageAfterGridCreation() {
        setInputData();

        viewModel.createGrid();
        String message = viewModel.getLog().get(0);

        assertTrue(message.matches(".*" + LogMessages.CREATE_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsInputArgumentsAfterGridCreation() {
        setInputData();

        viewModel.createGrid();

        String message = viewModel.getLog().get(0);
        assertTrue(message.matches(".*" + viewModel.heightFieldProperty().get()
                + ".*" + viewModel.widthFieldProperty().get() + ".*"));
    }

    @Test
    public void canPutSeveralLogMessages() {
        setInputData();

        viewModel.createGrid();
        viewModel.createGrid();
        viewModel.createGrid();

        assertEquals(3, viewModel.getLog().size());
    }

    @Test
    public void logContainsProperMessageAfterMakingMove() {
        setInputData();
        viewModel.createGrid();

        viewModel.getNextStep();

        String message = viewModel.getLog().get(1);

        assertTrue(message.matches(".*" + LogMessages.NEXT_WAS_PRESSED + ".*"));
    }

    @Test
    public void logContainsProperMessageAfterMakingNewCell() {
        setInputData();
        viewModel.createGrid();

        viewModel.changeCellStatus(2, 2);

        String message = viewModel.getLog().get(1);

        assertTrue(message.matches(".*" + LogMessages.CELL_WAS_CHANGED + ".*"));
    }

    @Test
    public void logContainsCellPositionAfterMakingNewCell() {
        final int cellPositionX = 3;
        final int cellPositionY = 2;

        setInputData();
        viewModel.createGrid();

        viewModel.changeCellStatus(cellPositionY, cellPositionX);

        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + (char) (cellPositionY + '0')
                        + ".*" + (char) (cellPositionX + '0') + ".*"));
    }

    @Test
    public void logContainsCellConditionForAliveCell() {
        setInputData();
        viewModel.createGrid();

        viewModel.changeCellStatus(3, 2);

        String message = viewModel.getLog().get(1);
        assertTrue(message.matches(".*" + "alive" + ".*"));
    }

    @Test
    public void logContainsCellConditionForDeadCell() {
        setInputData();
        viewModel.createGrid();

        viewModel.changeCellStatus(3, 2);
        viewModel.changeCellStatus(3, 2);

        String message = viewModel.getLog().get(2);
        assertTrue(message.matches(".*" + "dead" + ".*"));
    }

    private void setInputData() {
        viewModel.heightFieldProperty().set("5");
        viewModel.widthFieldProperty().set("5");
    }

}
