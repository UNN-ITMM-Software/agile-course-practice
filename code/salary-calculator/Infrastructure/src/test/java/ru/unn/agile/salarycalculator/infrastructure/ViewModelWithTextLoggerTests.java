package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.ViewModelTests;
import ru.unn.agile.salarycalculator.viewmodel.ViewModel;

public class ViewModelWithTextLoggerTests extends ViewModelTests {
    @Override
    public void setUpEmptyExample() {
        TextLogger realLogger = new TextLogger("salaryWorkLog.txt");
        ViewModel viewModel = new ViewModel(realLogger);

        viewModel.setSalary("10000");
        viewModel.setWorkedHours("154");
        viewModel.setCountMonth("10");
        viewModel.setCountYear("2014");

        super.setExternalViewModel(viewModel);
    }
}
