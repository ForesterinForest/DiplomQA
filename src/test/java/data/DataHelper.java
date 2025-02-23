package data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker();
    private static final Faker faker1 = new Faker(new Locale("ru_RU"));
    private static final Faker faker2 = new Faker();



    public static String numericName(){
        return faker2.number().digits(10);
    }

    public static Card getApprovedCardNowa() {
        return new Card("4444444444444441", "02", "25", faker.name().fullName(), faker.number().digits(3));
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "02", "25", faker.name().fullName(), faker.number().digits(3));
    }

    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }
    public static String getShiftedMonth() {
        return LocalDate.now().plusMonths((int) (Math.random() * 1)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static Card generateRandomCard() {
        return new Card( faker.number().digits(16), getShiftedMonth(), getShiftedYear(0), faker.name().fullName(), faker.number().digits(3));
    }

    public static Card getCardRandom() {
        return generateRandomCard();
    }

    public static Card getCardWithEmptyMonth() {
        return new Card("4444444444444441", " ", getShiftedYear(0), faker.name().fullName(), faker.number().digits(3));
    }//
    public static Card getCardWithShiftedMinusYear() {
        return new Card("4444444444444441",  getShiftedMonth(), getShiftedYear(-6), faker.name().fullName(), faker.number().digits(3));
    }
    public static Card getCardWithShiftedPlusYear() {
        return new Card("4444444444444441",  getShiftedMonth(), getShiftedYear(+6), faker.name().fullName(), faker.number().digits(3));
    }

    public static Card getCardWithEmptyYear() {
        return new Card("4444444444444441", getShiftedMonth(), " ", faker.name().fullName(), faker.number().digits(3));
    }

    public static Card getCardWithEmptyCvc() {
        return new Card("4444444444444441", getShiftedMonth(), getShiftedYear(0), faker.name().fullName()," " );
    }

    public static Card getCardWithCvcNumberTwo() {
        return new Card("4444444444444441", getShiftedMonth(), getShiftedYear(0), faker.name().fullName(), faker.number().digits(2));
    }
    public static Card getCardWithEmptyHolder() {
        return new Card("4444444444444441", getShiftedMonth(), getShiftedYear(0), " ", faker.number().digits(3));
    }

    public static Card getCardWithEmptyNumber() {
        return new Card(" ", getShiftedMonth(), getShiftedYear(0), faker.name().fullName(), faker.number().digits(3));
    }
    public static Card getCardWith15Number() {
        return new Card("444444444444444 ", getShiftedMonth(), getShiftedYear(0), faker.name().fullName(), faker.number().digits(3));
    }
    public static Card getCardHolderInCyrillic(){
        return new Card("4444444444444441", getShiftedMonth(), getShiftedYear(0), faker1.name().fullName(), faker.number().digits(3));
    }
    public static Card getCardWithHolderNumber() {
        return new Card("4444444444444441", getShiftedMonth(), getShiftedYear(0), numericName() ,faker.number().digits(3));
    }

}
