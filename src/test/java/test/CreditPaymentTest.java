package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.Card;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.DashboardPage;
import page.PurchaseOnCredit;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;



public class CreditPaymentTest {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

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
    public void shouldApprovePaymentWithValidCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card approvedCard = DataHelper.getApprovedCardNowa();
        purchaseOnCredit.enterCardDetails(approvedCard);
        String actualStatus = SQLHelper.getLatestCreditStatus();
        String expectedStatus = "APPROVED";
        assertAll(
                () -> assertDoesNotThrow(purchaseOnCredit::waitNotificationApproved),
                () -> {
                    assertEquals(expectedStatus, actualStatus, "APPROVED");
                });


    }


    @Test
    public void shouldDeclinedPaymentWithNoValidCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card declinedCard = DataHelper.getDeclinedCard();
        purchaseOnCredit.enterCardDetails(declinedCard);
        String actualStatus = SQLHelper.getLatestCreditStatus();
        String expectedStatus = "DECLINED";
        assertAll(
                () -> assertDoesNotThrow(purchaseOnCredit::waitNotificationFailure),
                () -> {
                    assertEquals(expectedStatus, actualStatus, "DECLINED");
                });


    }

    @Test
    public void shouldEmptyCardNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCardNumber = DataHelper.getCardWithEmptyNumber();
        purchaseOnCredit.enterCardDetails(emptyCardNumber);
        purchaseOnCredit.incorrectCardFormat();


    }

    @Test
    public void shouldEmptyCardMonth() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCardMonth = DataHelper.getCardWithEmptyMonth();
        purchaseOnCredit.enterCardDetails(emptyCardMonth);
        purchaseOnCredit.incorrectCardMonth();

    }

    @Test
    public void shouldEmptyCardYear() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCardYear = DataHelper.getCardWithEmptyYear();
        purchaseOnCredit.enterCardDetails(emptyCardYear);
        purchaseOnCredit.incorrectCardYear();

    }

    @Test
    public void shouldEmptyCardHolder() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCardHolder = DataHelper.getCardWithEmptyHolder();
        purchaseOnCredit.enterCardDetails(emptyCardHolder);
        purchaseOnCredit.incorrectCardHolder();

    }

    @Test
    public void shouldEmptyCardCvc() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCardCvc = DataHelper.getCardWithEmptyCvc();
        purchaseOnCredit.enterCardDetails(emptyCardCvc);
        purchaseOnCredit.incorrectCardCvc();

    }

    @Test
    public void shouldEmptyCard() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card emptyCard = DataHelper.getEmptyCard();
        purchaseOnCredit.enterCardDetails(emptyCard);
        purchaseOnCredit.emptyCard();

    }

    @Test
    public void shouldCardExpirationDate() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card shiftedYear = DataHelper.getCardWithShiftedMinusYear();
        purchaseOnCredit.enterCardDetails(shiftedYear);
        purchaseOnCredit.cardExpired();

    }

    @Test
    public void shouldCardExpirationDateInPlus() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card shiftedYear = DataHelper.getCardWithShiftedPlusYear();
        purchaseOnCredit.enterCardDetails(shiftedYear);
        purchaseOnCredit.cardExpirationDate();

    }

    @Test
    public void shouldNameCardCyrillic() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card cardHolderInCyrillic = DataHelper.getCardHolderInCyrillic();
        purchaseOnCredit.enterCardDetails(cardHolderInCyrillic);
        purchaseOnCredit.incorrectCardHolder();

    }

    @Test
    public void shouldNameCardNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card cardHolderInNumber = DataHelper.getCardWithHolderNumber();
        purchaseOnCredit.enterCardDetails(cardHolderInNumber);
        purchaseOnCredit.incorrectCardHolder();

    }

    @Test
    public void shouldCvcNumberTwo() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card cardCvcNumberTwo = DataHelper.getCardWithCvcNumberTwo();
        purchaseOnCredit.enterCardDetails(cardCvcNumberTwo);
        purchaseOnCredit.incorrectCardCvc();

    }

    @Test
    public void shouldCardNumber15() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card cardNumber = DataHelper.getCardWith15Number();
        purchaseOnCredit.enterCardDetails(cardNumber);
        purchaseOnCredit.incorrectCardFormat();


    }

    @Test
    public void shouldCardRandomNumber() {
        open("http://localhost:8080/");
        DashboardPage dashboardPage = new DashboardPage();
        PurchaseOnCredit purchaseOnCredit = dashboardPage.openCreditPage();
        Card randomNumber = DataHelper.getCardRandom();
        purchaseOnCredit.enterCardDetails(randomNumber);
        purchaseOnCredit.waitNotificationFailure();
    }


}


