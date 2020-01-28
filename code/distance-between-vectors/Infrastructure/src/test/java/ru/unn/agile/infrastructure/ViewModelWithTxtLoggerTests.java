package ru.unn.agile.infrastructure;

import ru.unn.agile.infrastructure.viewmodel.ViewModel;
import ru.unn.agile.infrastructure.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {

    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("metricslog.txt");
        ViewModel viewModel = new ViewModel(realLogger);
        super.setExternalViewModel(viewModel);
    }
}
