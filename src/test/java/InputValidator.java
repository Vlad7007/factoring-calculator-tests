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
        return getErrorHintElement().exists();
    }

    public String getErrorMessage() {
        if (isErrorPresent()) {
            return getErrorHintElement().text();
        }
        return "No error message present";
    }

    public String getErrorType() {
        SelenideElement errorHint = getErrorHintElement();
        if (errorHint.exists()) {
            return errorHint.getAttribute("error-type");
        } else {
            return null;
        }
    }

    private SelenideElement getErrorHintElement() {
        return $x(inputXPath +
                "/ancestor::div[contains(@class, 'ui-field__control')]/ui-hint[@type='error']"
        );
    }
}