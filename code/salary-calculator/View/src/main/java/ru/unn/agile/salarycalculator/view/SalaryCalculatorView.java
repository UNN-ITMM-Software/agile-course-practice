package ru.unn.agile.salarycalculator.view;

import ru.unn.agile.salarycalculator.viewmodel.ILogger;
import ru.unn.agile.salarycalculator.viewmodel.ViewModel;
import ru.unn.agile.salarycalculator.infrastructure.TextLogger;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class SalaryCalculatorView {
    private JPanel mainPanel;
    private JTextField txtSalary;
    private JTextField txtWorkedHours;
    private JTextField txtCountYear;
    private JTextField txtResult;
    private JButton calculateButton;
    private JLabel lbStatus;
    private JTextField txtCountMonth;
    private JTextArea textAreaLogs;
    private ViewModel viewModel;

    private SalaryCalculatorView() {
    }

    private SalaryCalculatorView(final ViewModel viewModelArg) {
        this.viewModel = viewModelArg;
        backBind();
        salaryCalculatorActionListener();
        salaryCalculatorKeyAdapter();

        textAreaLogs.setText("Logs will be here");
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("SalaryCalculatorView");
        ILogger logger = new TextLogger("salaryWorkLog.txt");
        ViewModel viewModel = new ViewModel(logger);
        frame.setContentPane(new SalaryCalculatorView(viewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setSalary(txtSalary.getText());
        viewModel.setWorkedHours(txtWorkedHours.getText());
        viewModel.setCountMonth(txtCountMonth.getText());
        viewModel.setCountYear(txtCountYear.getText());
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnable());
        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
        textAreaLogs.setText(viewModel.getLogs());
    }

    private void salaryCalculatorActionListener() {
        calculateButton.addActionListener(actionEvent -> {
            bind();
            viewModel.calculateSalary();
            backBind();
        });
    }

    private void salaryCalculatorKeyAdapter() {
        KeyAdapter whenInCountType = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.checkCountFields();
                backBind();
            }
        };

        txtSalary.addKeyListener(whenInCountType);
        txtWorkedHours.addKeyListener(whenInCountType);
        txtCountMonth.addKeyListener(whenInCountType);
        txtCountYear.addKeyListener(whenInCountType);
    }
}
