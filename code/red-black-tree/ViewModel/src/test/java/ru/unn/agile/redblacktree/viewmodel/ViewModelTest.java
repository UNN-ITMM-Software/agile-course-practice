package ru.unn.agile.redblacktree.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel(new MockLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    private void setInputData() {
        viewModel.addFieldProperty().set("1");
        viewModel.findInsertFieldProperty().set("1");
        viewModel.removeInsertFieldProperty().set("1");
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

    @Test
    public void onElementAddingAddElementLogWillBeCreated() {
        viewModel.addFieldProperty().set("0");
        viewModel.addElementToTree();
        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.ELEMENT_ADDED + ".*";
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementSearchingSearchElementLogWillBeCreated() {
        viewModel.addFieldProperty().set("0");
        viewModel.addElementToTree();

        viewModel.findInsertFieldProperty().set("0");
        viewModel.findElementInTree();
        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.SEARCH_ELEMENT + ".*";
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementSearchingElementExistsFoundLog() {
        viewModel.addFieldProperty().set("0");
        viewModel.addElementToTree();

        viewModel.findInsertFieldProperty().set("0");
        viewModel.findElementInTree();
        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.SEARCH_ELEMENT + ".*" + "found" + ".*";
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementSearchingElementNotExistsNotFoundLog() {
        viewModel.findInsertFieldProperty().set("0");
        viewModel.findElementInTree();
        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.SEARCH_ELEMENT + ".*" + "not found" + ".*";
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementRemovingRemoveLogWillBeCreated() {
        viewModel.addFieldProperty().set("0");
        viewModel.addElementToTree();

        viewModel.removeInsertFieldProperty().set("0");
        viewModel.removeElementFromTree();

        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.REMOVE_ELEMENT + ".*";
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementRemovingElementExistsRemoveLogContainsRemoved() {
        viewModel.addFieldProperty().set("0");
        viewModel.addElementToTree();

        viewModel.removeInsertFieldProperty().set("0");
        viewModel.removeElementFromTree();

        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.REMOVE_ELEMENT + ".*" + "removed" + ".*" ;
        assertTrue(logOutput.matches(matchPatter));
    }

    @Test
    public void onElementRemovingElementNotExistsRemoveLogContainsNotFound() {
        viewModel.removeInsertFieldProperty().set("0");
        viewModel.removeElementFromTree();

        String logOutput = viewModel.getLog().get(viewModel.getLog().size() - 1);

        String matchPatter = ".*" + LogPrefix.REMOVE_ELEMENT + ".*" + "not found" + ".*" ;
        assertTrue(logOutput.matches(matchPatter));
    }
}
