package ru.unn.agile.redblacktree.infrastructure;

import ru.unn.agile.redblacktree.viewmodel.ViewModel;
import ru.unn.agile.redblacktree.viewmodel.ViewModelTest;

public class TxtLoggerTestsWithViewModel extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("TxtLogger.log");
        super.setViewModel(new ViewModel((realLogger)));
    }
}