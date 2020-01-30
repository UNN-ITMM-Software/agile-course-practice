package ru.unn.agile.vectorsdistancescalculator.infrastructure;

import ru.unn.agile.vectorsdistancescalculator.viewmodel.ViewModel;
import ru.unn.agile.vectorsdistancescalculator.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
                new TxtLogger("./ViewModel_With_TxtLoggerTests.log");
        super.setOutsideViewModel(new ViewModel(realLogger));
    }
}
