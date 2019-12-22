package  ru.unn.agile.salarycalculator.viewmodel.legacy;

import java.util.List;

public interface ILogger {
    void log(String s);

    List<String> getLog();
}
