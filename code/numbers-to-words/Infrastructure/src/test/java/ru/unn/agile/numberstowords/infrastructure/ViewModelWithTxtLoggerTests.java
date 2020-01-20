package ru.unn.agile.numberstowords.infrastructure;

import ru.unn.agile.numberstowords.viewmodel.ViewModel;
import ru.unn.agile.numberstowords.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger logger =
                new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModelForm(new ViewModel(logger));
    }
}
