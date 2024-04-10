import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import java.math.BigInteger;
import java.util.Random;


public class FactoringCalculatorUITests {

    @Test
    public void basicTest() {
        open("https://www.swedbank.lt/business/finance/trade/factoring?language=ENG");
        $x("//button[@class='button ui-cookie-consent__accept-button']").click();
        $x("//ui-calculator[@state='hydrated']").shouldBe(Condition.visible);
    }

    @Test
    public void fillAndDisplayTest() {
        $x("//input[@id='D5']").setValue("999999").getValue().contains("999999");

        SelenideElement selectD6 = $x("//select[@id='D6']");
        selectD6.selectOption("80");
        selectD6.getValue().contains("80");

        $x("//input[@id='D7']").setValue("20").getValue().contains("20");

        SelenideElement selectD8 = $x("//select[@id='D8']");
        selectD8.selectOption("60");
        selectD8.getValue().contains("60");

        $x("//input[@id='D9']").setValue("999999").getValue().contains("999999");

    }

    @Test
    public void fillCalc() {
        $x("//input[@id='D5']").setValue("999999");
        $x("//select[@id='D6']").selectOption("85");
        $x("//input[@id='D7']").setValue("20");
        $x("//select[@id='D8']").selectOption("90");
        $x("//input[@id='D9']").setValue("999999");

        $x("//button[@id='calculate-factoring']").click();
        $x("//output[@id='result']").getValue().contains("10000022499.97");
        System.out.println($x("//output[@id='result']").getValue());
    }

    @Test
    public void maxInputValueCheck() {
        System.out.println(countDigits(findMaxInputValue()));
    }

    public BigInteger findMaxInputValue() { // TODO: implement a separate class
        int bitLength = 8;
        Random random = new Random();
        BigInteger value = new BigInteger(bitLength, random);
        boolean errorFlag = false;
        while (!errorFlag) {
            try {
                setInputValue(value);
                errorFlag = $x("//ui-hint[@type='error']").exists();
            } catch (Exception e) {
                errorFlag = true;
                break;
            }
            value = value.multiply(BigInteger.TEN);
        }

        return value.divide(BigInteger.TEN);
    }

    private void setInputValue(BigInteger value) {
        $x("//input[@id='D5']").setValue(String.valueOf(value));
    }

    public int countDigits(BigInteger number) {
        return number.toString().length();
    }

}
