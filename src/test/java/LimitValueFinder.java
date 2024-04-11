import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import java.math.BigInteger;
import java.util.Random;

public class LimitValueFinder {

    private final String inputXPath;

    public LimitValueFinder(String inputXPath) {
        this.inputXPath = inputXPath;
    }

    public BigInteger findMaxInputValue() {
        return findExtremeInputValue(true);
    }

    public BigInteger findMinInputValue() {
        return findExtremeInputValue(false);
    }

    private BigInteger findExtremeInputValue(boolean findMax) {
        SelenideElement inputField = $x(inputXPath);
        BigInteger value = BigInteger.ZERO;
        boolean errorFlag = false;

        for (int i = 9; i >= 0; i--) {
            value = BigInteger.valueOf(findMax ? -i : i);
            setInputValue(inputField, value);
            if (hasError()) {
                errorFlag = true;
                break;
            }
        }

        if (!errorFlag) {
            value = (findMax ? BigInteger.ONE.negate() : BigInteger.ONE);
            while (!errorFlag) {
                try {
                    setInputValue(inputField, value);
                    errorFlag = hasError();
                    if (!errorFlag) {
                        value = (findMax ? value.multiply(BigInteger.TEN) : value.multiply(BigInteger.TEN.negate()));
                    }
                } catch (Exception e) {
                    errorFlag = true;
                }
                if (errorFlag) {
                    value = value.divide(BigInteger.TEN);
                    if (findMax) {
                        value = value.negate();
                    }
                }
            }
        }

        return value;
    }

    private void setInputValue(SelenideElement inputField, BigInteger value) {
        inputField.setValue(value.toString());
    }

    private boolean hasError() {
        return $x("//ui-hint[@type='error']").exists();
    }

    public int countDigits(BigInteger number) {
        return number.toString().length();
    }
}
