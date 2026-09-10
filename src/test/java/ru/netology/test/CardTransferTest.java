package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTransferTest {

    private DashboardPage dashboardPage;

    private String firstCardId = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private String secondCardId = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin("vasya", "qwerty123");
        dashboardPage = verificationPage.validVerify("12345");
    }

    @Test
    public void testTransferFromFirstToSecondCard() {
        int firstBalanceBefore = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceBefore = dashboardPage.getCardBalance(secondCardId);

        int transferAmount = 1000;

        TransferPage transferPage = dashboardPage.selectCardForTransfer(secondCardId);
        dashboardPage = transferPage.makeTransfer(String.valueOf(transferAmount), "5559 0000 0000 0001");

        int firstBalanceAfter = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceAfter = dashboardPage.getCardBalance(secondCardId);

        assertEquals(firstBalanceBefore - transferAmount, firstBalanceAfter);
        assertEquals(secondBalanceBefore + transferAmount, secondBalanceAfter);
    }

    @Test
    public void testTransferFromSecondToFirstCard() {
        int firstBalanceBefore = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceBefore = dashboardPage.getCardBalance(secondCardId);

        int transferAmount = 500;

        TransferPage transferPage = dashboardPage.selectCardForTransfer(firstCardId);
        dashboardPage = transferPage.makeTransfer(String.valueOf(transferAmount), "5559 0000 0000 0002");

        int firstBalanceAfter = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceAfter = dashboardPage.getCardBalance(secondCardId);

        assertEquals(firstBalanceBefore + transferAmount, firstBalanceAfter);
        assertEquals(secondBalanceBefore - transferAmount, secondBalanceAfter);
    }
}