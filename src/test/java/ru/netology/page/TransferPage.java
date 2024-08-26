package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement amountInput = $("[data-test-id = 'amount'] input");
    private final SelenideElement fromInput = $("[data-test-id = 'from'] input");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $("[data-test-id = 'error-notification'] .notification__content");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    //заполняет форму и переходит в экземпляр класса DashboardPage
    // это нужно - в DashboardPage есть проверка видимости - и если мы введем невалидные данные
    //тест упадет на этом месте - если сообщение об ошибке будет отражено
    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    //метод для невалидных данных негативного теста
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    //метод для проверки сообщения об ошибке
    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
