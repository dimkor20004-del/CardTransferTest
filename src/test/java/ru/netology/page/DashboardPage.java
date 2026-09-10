package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public int getCardBalance(String cardId) {
        SelenideElement card = $("[data-test-id='" + cardId + "']");
        String cardText = card.getText();
        String balanceText = cardText.replaceAll(".*баланс:\\s*", "");
        String cleanBalance = balanceText.replaceAll("[^0-9]", "");
        return Integer.parseInt(cleanBalance);
    }

    public TransferPage selectCardForTransfer(String cardId) {
        SelenideElement card = $("[data-test-id='" + cardId + "']");
        card.$("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }
}