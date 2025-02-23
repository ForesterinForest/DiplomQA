package page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement heading = $$("h2").find(text("Путешествие дня"));
    private final SelenideElement buyButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public PaymentByCard openBuyPage() {
        buyButton.click();
        return new PaymentByCard();
    }

    public PurchaseOnCredit openCreditPage() {
        creditButton.click();
        return new PurchaseOnCredit();
    }
}

