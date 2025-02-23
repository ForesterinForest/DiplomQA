package page;

import com.codeborne.selenide.SelenideElement;
import data.Card;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;



public class PurchaseOnCredit {
    private final SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private final SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cvcField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private final SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement failureOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");

    private final SelenideElement emptyCardNumber = $(byText("Неверный формат"));
    private final SelenideElement emptyCardMonth = $(byText("Неверный формат"));
    private final SelenideElement emptyCardYear = $(byText("Неверный формат"));
    private final SelenideElement emptyCardHolder = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement emptyCardCvc = $(byText("Неверный формат"));
    private final SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement expirationDate = $(byText("Неверно указан срок действия карты"));


    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public PurchaseOnCredit() {
        heading.shouldBe(visible);
    }

    public void enterCardDetails(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }


    public void waitForNotification(SelenideElement notification) {
        notification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationApproved() {
        waitForNotification(approvedOperation);

    }

    public void waitNotificationFailure() {
        waitForNotification(failureOperation);

    }

    public void incorrectCardFormat() {
        waitForNotification(emptyCardNumber);

    }

    public void incorrectCardMonth() {
        waitForNotification(emptyCardMonth);
    }

    public void incorrectCardYear() {
        waitForNotification(emptyCardYear);
    }

    public void incorrectCardHolder() {
        waitForNotification(emptyCardHolder);
    }

    public void incorrectCardCvc() {
        waitForNotification(emptyCardCvc);
    }

    public void cardExpired() {
        waitForNotification(cardExpired);
    }

    public void cardExpirationDate() {
        waitForNotification(expirationDate);
    }

    public void emptyCard() {
        waitForNotification(emptyCardNumber);
        waitForNotification(emptyCardMonth);
        waitForNotification(emptyCardYear);
        waitForNotification(emptyCardHolder);
        waitForNotification(emptyCardCvc);


    }
}

