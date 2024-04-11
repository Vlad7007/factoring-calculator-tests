import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import java.math.BigInteger;
import java.util.Random;

public class MaxValueFinder { // TODO: make universal for maximum and minimum values.

    private final String inputXPath;
    private final int bitLength;

    public MaxValueFinder(String inputXPath, int bitLength) {
        this.inputXPath = inputXPath;
        this.bitLength = bitLength;
    }

    public BigInteger findMaxInputValue() {
        SelenideElement inputField = $x(inputXPath);
        Random random = new Random();
        BigInteger value = new BigInteger(bitLength, random);
        boolean errorFlag = false;
        while (!errorFlag) {
            try {
                setInputValue(inputField, value);
                errorFlag = hasError();
            } catch (Exception e) {
                errorFlag = true;
            }
            if (!errorFlag) {
                value = value.multiply(BigInteger.TEN);
            }
        }
        return value.divide(BigInteger.TEN);
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
