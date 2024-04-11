import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;


public class FactoringCalculatorUITests {

    @Before
    public void setUp() {
        open("https://www.swedbank.lt/business/finance/trade/factoring?language=ENG");
        $x("//button[@class='button ui-cookie-consent__accept-button']").click();
        $x("//ui-calculator[@state='hydrated']").shouldBe(Condition.visible);
    }

    @Test
    public void fillAndDisplayTest() {
        SelenideElement valueD5 = $x("//input[@id='D5']").setValue("999999");
        assertEquals("999999", valueD5.getValue());

        SelenideElement selectD6 = $x("//select[@id='D6']");
        selectD6.selectOption("80");
        assertEquals("80", selectD6.getValue());

        SelenideElement valueD7 = $x("//input[@id='D7']").setValue("20");
        assertEquals("20", valueD7.getValue());

        SelenideElement selectD8 = $x("//select[@id='D8']");
        selectD8.selectOption("60");
        assertEquals("60", selectD8.getValue());

        SelenideElement valueD9 = $x("//input[@id='D9']").setValue("999999");
        assertEquals("999999", valueD9.getValue());
    }

    @Test
    public void fillCalc() {
        $x("//input[@id='D5']").setValue("999999");
        $x("//select[@id='D6']").selectOption("85");
        $x("//input[@id='D7']").setValue("20");
        $x("//select[@id='D8']").selectOption("90");
        $x("//input[@id='D9']").setValue("999999");

        $x("//button[@id='calculate-factoring']").click();
        SelenideElement result = $x("//output[@id='result']");
        assertEquals("10000022499.97", result.getValue());
    }

    @Test
    public void correctResultDisplayTest() {

        $x("//input[@id='D5']").setValue("99");
        $x("//select[@id='D6']").selectOption("85");
        $x("//input[@id='D7']").setValue("20");
        $x("//select[@id='D8']").selectOption("90");
        $x("//input[@id='D9']").setValue("9999");

        $x("//button[@id='calculate-factoring']").click();

        SelenideElement labelEur = $$(".plain-list.-results label.units").findBy(text("EUR"));
        SelenideElement listItem = labelEur.closest("li");

        assertFalse(isElementOutsideContainer(labelEur, listItem));
    }

    private static boolean isElementOutsideContainer(SelenideElement element, SelenideElement container) {
        return Boolean.TRUE.equals(executeJavaScript(
                "var elementRect = arguments[0].getBoundingClientRect();" +
                        "var containerRect = arguments[1].getBoundingClientRect();" +
                        "var offset = 10;" +
                        "return elementRect.top < containerRect.top || elementRect.bottom > containerRect.bottom || " +
                        "elementRect.left < containerRect.left || (elementRect.right - offset) > containerRect.right;",
                element, container
        ));
    }

    @Test
    public void testInputValidationErrors() {
        InputValidator validatorD5 = new InputValidator("//input[@id='D5']");

        validatorD5.setInputValue("1");
        assertFalse(validatorD5.isErrorPresent());

        validatorD5.setInputValue("-1");
        assertTrue(validatorD5.isErrorPresent());

        validatorD5.setInputValue("0");
        assertTrue(validatorD5.isErrorPresent());


        InputValidator validatorD7 = new InputValidator("//input[@id='D7']");

        validatorD7.setInputValue("1");
        assertFalse(validatorD7.isErrorPresent());

        validatorD7.setInputValue("-1");
        assertTrue(validatorD7.isErrorPresent());

        validatorD7.setInputValue("0");
        assertFalse(validatorD7.isErrorPresent());


        InputValidator validatorD9 = new InputValidator("//input[@id='D9']");

        validatorD9.setInputValue("1");
        assertFalse(validatorD9.isErrorPresent());

        validatorD9.setInputValue("-1");
        assertFalse(validatorD9.isErrorPresent());

        validatorD9.setInputValue("0");
        assertFalse(validatorD9.isErrorPresent());
    }

    @Test
    public void testInputValidationErrorTypes() {
        InputValidator validatorD5 = new InputValidator("//input[@id='D5']");

        validatorD5.setInputValue("");
        assertEquals("valueMissing", validatorD5.getErrorType());

        validatorD5.setInputValue("1");
        assertNull(validatorD5.getErrorType());
        validatorD5.setInputValue("-1");
        assertEquals("rangeUnderflow", validatorD5.getErrorType());
        validatorD5.setInputValue("0");
        assertEquals("rangeUnderflow", validatorD5.getErrorType());


        InputValidator validatorD7 = new InputValidator("//input[@id='D7']");

        validatorD7.setInputValue("");
        assertEquals("valueMissing", validatorD7.getErrorType());

        validatorD7.setInputValue("1");
        assertNull(validatorD7.getErrorType());
        validatorD7.setInputValue("-1");
        assertEquals("rangeUnderflow", validatorD7.getErrorType());
        validatorD7.setInputValue("0");
        assertNull(validatorD7.getErrorType());


        InputValidator validatorD9 = new InputValidator("//input[@id='D9']");

        validatorD9.setInputValue("");
        assertEquals("valueMissing", validatorD9.getErrorType());

        validatorD9.setInputValue("1");
        assertNull(validatorD9.getErrorType());
        validatorD9.setInputValue("-1");
        assertNull(validatorD9.getErrorType());
        validatorD9.setInputValue("0");
        assertNull(validatorD9.getErrorType());
    }

    @Test
    public void testSpecialInputValidationErrorTypes() {

        InputValidator validatorD5 = new InputValidator("//input[@id='D5']");

        validatorD5.setInputValue("1.005");
        assertEquals("stepMismatch", validatorD5.getErrorType());
        validatorD5.setInputValue("10000.01");
        assertNull(validatorD5.getErrorType());

        validatorD5.setInputValue(" ");
        assertEquals("valueMissing", validatorD5.getErrorType());
        validatorD5.setInputValue("abcd");
        assertEquals("valueMissing", validatorD5.getErrorType());



        InputValidator validatorD7 = new InputValidator("//input[@id='D7']");

        validatorD7.setInputValue("1.005");
        assertEquals("stepMismatch", validatorD7.getErrorType());
        validatorD7.setInputValue("15.01");
        assertNull(validatorD7.getErrorType());

        validatorD7.setInputValue("20.01");
        assertEquals("rangeOverflow", validatorD7.getErrorType());

        validatorD7.setInputValue(" ");
        assertEquals("valueMissing", validatorD7.getErrorType());
        validatorD5.setInputValue("abcd");
        assertEquals("valueMissing", validatorD5.getErrorType());



        InputValidator validatorD9 = new InputValidator("//input[@id='D9']");

        validatorD9.setInputValue("1.005");
        assertEquals("stepMismatch", validatorD9.getErrorType());
        validatorD9.setInputValue("10000.01");
        assertNull(validatorD9.getErrorType());

        validatorD9.setInputValue("-1.005");
        assertEquals("stepMismatch", validatorD9.getErrorType());
        validatorD9.setInputValue("-10000.01");
        assertNull(validatorD9.getErrorType());

        validatorD9.setInputValue(" ");
        assertEquals("valueMissing", validatorD9.getErrorType());
        validatorD5.setInputValue("abcd");
        assertEquals("valueMissing", validatorD5.getErrorType());

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
