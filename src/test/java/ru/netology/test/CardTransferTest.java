package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;
import ru.netology.utils.DataHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTransferTest {

    private DashboardPage dashboardPage;

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
        UserData user = DataHelper.getValidUser();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(user.getLogin(), user.getPassword());
        dashboardPage = verificationPage.validVerify(user.getVerificationCode());
    }

    @Test
    public void testTransferFromFirstToSecondCard() {
        String firstCardId = DataHelper.getFirstCardId();
        String secondCardId = DataHelper.getSecondCardId();

        int firstBalanceBefore = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceBefore = dashboardPage.getCardBalance(secondCardId);

        int transferAmount = 1000;

        TransferPage transferPage = dashboardPage.selectCardForTransfer(secondCardId);
        dashboardPage = transferPage.makeTransfer(
                String.valueOf(transferAmount),
                DataHelper.getFirstCardNumber()
        );

        int firstBalanceAfter = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceAfter = dashboardPage.getCardBalance(secondCardId);

        assertEquals(firstBalanceBefore - transferAmount, firstBalanceAfter);
        assertEquals(secondBalanceBefore + transferAmount, secondBalanceAfter);
    }

    @Test
    public void testTransferFromSecondToFirstCard() {
        String firstCardId = DataHelper.getFirstCardId();
        String secondCardId = DataHelper.getSecondCardId();

        int firstBalanceBefore = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceBefore = dashboardPage.getCardBalance(secondCardId);

        int transferAmount = 500;

        TransferPage transferPage = dashboardPage.selectCardForTransfer(firstCardId);
        dashboardPage = transferPage.makeTransfer(
                String.valueOf(transferAmount),
                DataHelper.getSecondCardNumber()
        );

        int firstBalanceAfter = dashboardPage.getCardBalance(firstCardId);
        int secondBalanceAfter = dashboardPage.getCardBalance(secondCardId);

        assertEquals(firstBalanceBefore + transferAmount, firstBalanceAfter);
        assertEquals(secondBalanceBefore - transferAmount, secondBalanceAfter);
    }
}