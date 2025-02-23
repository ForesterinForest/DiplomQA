package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.Card;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.DashboardPage;
import page.PaymentByCard;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;



public class PaymentTest {


    @AfterEach
    public void clean() {
        SQLHelper.clearDB();
    }


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldApprovePaymentCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card approvedCard = DataHelper.getApprovedCardNowa();
        paymentByCard.inputData(approvedCard);
        String actualStatus = SQLHelper.getLatestPaymentStatus();
        String expectedStatus = "APPROVED";// Ожидаемый статус
        assertAll(
                () -> assertDoesNotThrow(paymentByCard::waitNotificationApproved),
                () -> {
                    assertEquals(expectedStatus, actualStatus, "APPROVED");
                });
    }


    @Test
    public void shouldDeclinedPaymentCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card declinedCard = DataHelper.getDeclinedCard();
        paymentByCard.inputData(declinedCard);
        String actualStatus = SQLHelper.getLatestPaymentStatus();
        String expectedStatus = "DECLINED"; // Ожидаемый статус
        assertAll(
                () -> assertDoesNotThrow(paymentByCard::waitNotificationFailure),
                () -> {
                    assertEquals(expectedStatus, actualStatus, "DECLINED");
                });


    }

    @Test
    public void shouldEmptyCardNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCardNumber = DataHelper.getCardWithEmptyNumber();
        paymentByCard.inputData(emptyCardNumber);
        paymentByCard.incorrectCardFormat();


    }

    @Test
    public void shouldEmptyCardMonth() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCardMonth = DataHelper.getCardWithEmptyMonth();
        paymentByCard.inputData(emptyCardMonth);
        paymentByCard.incorrectCardMonth();

    }

    @Test
    public void shouldEmptyCardYear() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCardYear = DataHelper.getCardWithEmptyYear();
        paymentByCard.inputData(emptyCardYear);
        paymentByCard.incorrectCardYear();

    }

    @Test
    public void shouldEmptyCardHolder() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCardHolder = DataHelper.getCardWithEmptyHolder();
        paymentByCard.inputData(emptyCardHolder);
        paymentByCard.incorrectCardHolder();

    }

    @Test
    public void shouldEmptyCardCvc() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCardCvc = DataHelper.getCardWithEmptyCvc();
        paymentByCard.inputData(emptyCardCvc);
        paymentByCard.incorrectCardCvc();

    }

    @Test
    public void shouldEmptyCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card emptyCard = DataHelper.getEmptyCard();
        paymentByCard.inputData(emptyCard);
        paymentByCard.emptyCard();

    }

    @Test
    public void shouldCardExpirationDate() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card shiftedYear = DataHelper.getCardWithShiftedMinusYear();
        paymentByCard.inputData(shiftedYear);
        paymentByCard.cardExpired();

    }

    @Test
    public void shouldCardExpirationDateInPlus() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card shiftedYear = DataHelper.getCardWithShiftedPlusYear();
        paymentByCard.inputData(shiftedYear);
        paymentByCard.cardExpirationDate();

    }

    @Test
    public void shouldNameCardCyrillic() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card cardHolderInCyrillic = DataHelper.getCardHolderInCyrillic();
        paymentByCard.inputData(cardHolderInCyrillic);
        paymentByCard.incorrectCardHolder();

    }

    @Test
    public void shouldNameCardNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card cardHolderInNumber = DataHelper.getCardWithHolderNumber();
        paymentByCard.inputData(cardHolderInNumber);
        paymentByCard.incorrectCardHolder();

    }

    @Test
    public void shouldCvcNumberTwo() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card cardCvcNumberTwo = DataHelper.getCardWithCvcNumberTwo();
        paymentByCard.inputData(cardCvcNumberTwo);
        paymentByCard.incorrectCardCvc();

    }

    @Test
    public void shouldCardNumber15() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card cardNumber = DataHelper.getCardWith15Number();
        paymentByCard.inputData(cardNumber);
        paymentByCard.incorrectCardFormat();


    }

    @Test
    public void shouldCardRandomNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PaymentByCard paymentByCard = dashboardPage.openBuyPage();
        Card randomNumber = DataHelper.getCardRandom();
        paymentByCard.inputData(randomNumber);
        paymentByCard.waitNotificationFailure();
    }
}
