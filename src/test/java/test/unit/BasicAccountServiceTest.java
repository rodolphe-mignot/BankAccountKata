package test.unit;

import kata.api.AccountService;
import kata.model.ServiceError;
import kata.services.AccountServiceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BasicAccountServiceTest {

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = AccountServiceProvider.getAccountService();
    }

    @Test
    void addAmount() throws ServiceError {
        String id = getNewRandomId();
        accountService.createAccount(id);
        accountService.addAmount(id, 100);
        assertEquals(100, accountService.getAccount(id).getFunds().intValue());
    }

    @Test
    void removeAmount() throws ServiceError {
        String id = getNewRandomId();
        accountService.createAccount(id);
        accountService.addAmount(id, 2000);
        accountService.removeAmount(id, 100.95f);
        assertEquals(1899.05f, accountService.getAccount(id).getFunds().floatValue());
    }

    @Test
    void getAccountHistory() throws ServiceError {
        String id = getNewRandomId();
        accountService.createAccount(id);
        accountService.addAmount(id, 2000);
        accountService.removeAmount(id, 100.95f);

        String accountHistory = accountService.getAccountHistory(id);
        String expectedString = "Current balance : 1899.05\r\n" +
                "{operationTime=xx/xx/xxxx - xx:xx:xx +0100, operationType=DEPOSIT, operationAmount=2000.0}\n\n" +
                "{operationTime=xx/xx/xxxx - xx:xx:xx +0100, operationType=WITHDRAWAL, operationAmount=100.95}";
        assertEquals(expectedString.length(), accountHistory.length());
        assertNotEquals(accountHistory.indexOf("Current balance : 1899.05"), -1);
        assertNotEquals(accountHistory.indexOf(", operationType=DEPOSIT, operationAmount=2000.0}"), -1);
        assertNotEquals(accountHistory.indexOf(", operationType=WITHDRAWAL, operationAmount=100.95"), -1);
        assertNotEquals(accountHistory.indexOf("operationTime="), -1);
    }

    private String getNewRandomId() {
        return UUID.randomUUID().toString();
    }

    @Test
    void throwExceptionIfNoAccountPresent() {
        String id = getNewRandomId();
        assertThrows(ServiceError.class, () -> accountService.addAmount(id, 2000));
    }

    @Test
    void moreThanOneAccountIsPresent() throws ServiceError {
        String id1 = getNewRandomId();
        accountService.createAccount(id1);
        String id2 = getNewRandomId();
        accountService.createAccount(id2);

        accountService.addAmount(id1, 150);
        accountService.removeAmount(id1, 50);
        assertEquals(100f, accountService.getAccount(id1).getFunds().floatValue());

        accountService.addAmount(id2, 50);
        accountService.removeAmount(id2, 12.5f);
        assertEquals(37.5f, accountService.getAccount(id2).getFunds().floatValue());
    }

    @Test
    void throwExceptionIfNegativeBalance() throws ServiceError {
        String id2 = getNewRandomId();
        accountService.createAccount(id2);
        accountService.addAmount(id2, 12.5f);
        assertThrows(ServiceError.class, () -> accountService.removeAmount(id2, 50));
    }

    @Test
    void createAccount() throws ServiceError {
        String id2 = getNewRandomId();
        accountService.createAccount(id2);
        assertNotNull(accountService.getAccount(id2));
    }
}