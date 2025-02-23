package page;

import com.codeborne.selenide.SelenideElement;
import data.Card;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentByCard {
    private final SelenideElement heading = $$("h3").find(exactText("Оплата по карте"));
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private final SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cvcField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private final SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");


    private final SelenideElement emptyCardHolder = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement errorFormat = $(byText("Неверный формат"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement expirationDate = $(byText("Неверно указан срок действия карты"));


    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public PaymentByCard() {
        heading.shouldBe(visible);
    }

    public void inputData(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }


    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));

    }

    public void waitNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(15));

    }

    public void incorrectCardFormat() {
        errorFormat.shouldBe(visible);

    }

    public void incorrectCardMonth() {
        errorFormat.shouldBe(visible);
    }

    public void incorrectCardYear() {
        errorFormat.shouldBe(visible);
    }

    public void incorrectCardHolder() {
        emptyCardHolder.shouldBe(visible);
    }

    public void incorrectCardCvc() {
        errorFormat.shouldBe(visible);
    }

    public void cardExpired() {
        cardExpired.shouldBe(visible);
    }

    public void cardExpirationDate() {
        expirationDate.shouldBe(visible);
    }

    public void emptyCard() {
        emptyCardHolder.shouldBe(visible);
        errorFormat.shouldBe(visible);


    }
}
