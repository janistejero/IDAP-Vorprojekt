package gewinnverteiler;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Janis Tejero
 */
public class NumericTextField extends TextField {

    public NumericTextField() {
        super();

        addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!isValid(getText())) {
                event.consume();
            }
        });

        textProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> {
            if (!isValid(newValue) || newValue.isEmpty()) {
                setText(oldValue);
            }
        });
    }

    private boolean isValid(final String value) {
        if (value.length() == 0 || value.equals("-")) {
            return true;
        }

        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public double getDouble() {
        try {
            return Double.parseDouble(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
