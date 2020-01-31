package ru.unn.agile.gameoflife.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}
