package ru.unn.agile.redblacktree.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;
    @Before
    public void setUp() {
        viewModel = new ViewModel(new MockLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.addFieldProperty().get());
        assertEquals("", viewModel.findInsertFieldProperty().get());
        assertEquals("", viewModel.removeInsertFieldProperty().get());
    }

    @Test
    public void statusIsWaitingWhenAddElementToTreeWithEmptyFields() {
        viewModel.addElementToTree();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenFindElementToTreeWithEmptyFields() {
        viewModel.findElementInTree();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsWaitingWhenRemoveElementToTreeWithEmptyFields() {
        viewModel.removeElementFromTree();
        assertEquals(Status.WAITING.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenAllFieldsAreFill() {
        viewModel.addFieldProperty().set("1");
        viewModel.findInsertFieldProperty().set("2");
        viewModel.removeInsertFieldProperty().set("3");

        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatAddField() {
        viewModel.addFieldProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatFindInsertField() {
        viewModel.findInsertFieldProperty().set("b");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canReportBadFormatRemoveInsertField() {
        viewModel.removeInsertFieldProperty().set("c");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void buttonIsDisabledInitiallyForAddElement() {
        assertTrue(viewModel.addElementToTreeDisabledProperty().get());
    }

    @Test
    public void buttonIsDisabledInitiallyForFindElement() {
        assertTrue(viewModel.findElementInTreeDisabledProperty().get());
    }

    @Test
    public void buttonIsDisabledInitiallyForRemoveElement() {
        assertTrue(viewModel.removeElementFromTreeDisabledProperty().get());
    }

    @Test
    public void buttonIsDisabledWhenFormatIsBadForAddElement() {
        setInputData();
        viewModel.addFieldProperty().set("trash");

        assertTrue(viewModel.addElementToTreeDisabledProperty().get());
    }

    @Test
    public void buttonIsDisabledWhenFormatIsBadForFindElement() {
        setInputData();
        viewModel.findInsertFieldProperty().set("trash");

        assertTrue(viewModel.findElementInTreeDisabledProperty().get());
    }

    @Test
    public void buttonIsDisabledWhenFormatIsBadForRemoveElement() {
        setInputData();
        viewModel.removeInsertFieldProperty().set("trash");

        assertTrue(viewModel.removeElementFromTreeDisabledProperty().get());
    }

    @Test
    public void operationAddHasCorrectResult() {
        viewModel.addFieldProperty().set("1");
        viewModel.addElementToTree();

        viewModel.findInsertFieldProperty().set("1");
        viewModel.findElementInTree();

        assertTrue(Boolean.parseBoolean(viewModel.resultFindProperty().get()));
    }

    @Test
    public void operationFindHasCorrectResult() {
        viewModel.findInsertFieldProperty().set("1");
        viewModel.findElementInTree();

        assertFalse(Boolean.parseBoolean(viewModel.resultFindProperty().get()));
    }

    @Test
    public void operationRemoveHasCorrectResult() {
        viewModel.addFieldProperty().set("1");
        viewModel.addElementToTree();

        viewModel.removeInsertFieldProperty().set("1");
        viewModel.removeElementFromTree();

        assertTrue(Boolean.parseBoolean(viewModel.resultRemoveProperty().get()));
    }

    @Test
    public void canSetSuccessMessageForAddElement() {
        setInputData();
        viewModel.addElementToTree();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetSuccessMessageForFindElement() {
        setInputData();
        viewModel.findElementInTree();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetSuccessMessageForRemoveElement() {
        setInputData();
        viewModel.removeElementFromTree();
        assertEquals(Status.SUCCESS.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessageForAddElement() {
        viewModel.addFieldProperty().set("#sdiofhsdkjf");
        viewModel.addElementToTree();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessageForFindElement() {
        viewModel.findInsertFieldProperty().set("#sdiofhsdkjf");
        viewModel.findElementInTree();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void canSetBadFormatMessageForRemoveElement() {
        viewModel.removeInsertFieldProperty().set("#sdiofhsdkjf");
        viewModel.removeElementFromTree();
        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void statusIsReadyWhenSetProperDataForAllInserts() {
        setInputData();
        assertEquals(Status.READY.toString(), viewModel.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void onNullLoggerThrowException() {
        viewModel = new ViewModel(null);
    }

    @Test(expected = Test.None.class)
    public void onMockLoggerNoThrow() {
        viewModel = new ViewModel(new MockLogger());
    }

    @Test
    public void afterConstructorLogIsEmpty() {
        var log = viewModel.getLog();
        
        assertEquals(0, log.size());
    }

    private void setInputData() {
        viewModel.addFieldProperty().set("1");
        viewModel.findInsertFieldProperty().set("1");
        viewModel.removeInsertFieldProperty().set("1");
    }
}
