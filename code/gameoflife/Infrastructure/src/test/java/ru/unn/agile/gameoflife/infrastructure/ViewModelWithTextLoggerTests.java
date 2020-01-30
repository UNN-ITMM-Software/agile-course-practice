package ru.unn.agile.gameoflife.infrastructure;

import ru.unn.agile.gameoflife.viewmodel.ViewModel;
import ru.unn.agile.gameoflife.viewmodel.ViewModelTest;

public class ViewModelWithTextLoggerTests extends ViewModelTest {
    @Override
    public void setUp() {
        TextLogger logger =
                new TextLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(logger));
    }
}
