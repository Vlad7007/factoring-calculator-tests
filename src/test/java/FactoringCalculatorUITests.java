import com.codeborne.selenide.Condition;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class FactoringCalculatorUITests {

    @Test
    public void basicTest() {
        open("https://www.swedbank.lt/business/finance/trade/factoring?language=ENG");
        $x("//button[@class='button ui-cookie-consent__accept-button']").click();
        $x("//section[@class='frame']").shouldBe(Condition.visible);
    }

}
