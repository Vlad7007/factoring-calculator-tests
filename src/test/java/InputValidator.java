import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class InputValidator {

    private final String inputXPath;

    public InputValidator(String inputXPath) {
        this.inputXPath = inputXPath;
    }

    public void setInputValue(String value) {
        SelenideElement inputField = $x(inputXPath);
        inputField.setValue(value);
    }

    public boolean isErrorPresent() {
        SelenideElement errorHint = $x(inputXPath +
                "/ancestor::div[contains(@class, 'ui-field__control')]/ui-hint[@type='error']"
        );
        return errorHint.exists();
    }

    public String getErrorMessage() {
        if (isErrorPresent()) {
            SelenideElement errorHint = $x(inputXPath +
                    "/ancestor::div[contains(@class, 'ui-field__control')]/ui-hint[@type='error']"
            );
            return errorHint.text();
        }
        return "No error message present";
    }
}