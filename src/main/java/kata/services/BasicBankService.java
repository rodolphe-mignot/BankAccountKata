package kata.services;

import kata.api.AccountService;
import kata.api.BankService;
import kata.model.ServiceError;

public class BasicBankService implements BankService {

    private final AccountService accountService;

    public BasicBankService() {
        this.accountService = AccountServiceProvider.getAccountService();
    }

    @Override
    public void makeADeposit(String account, float amount) throws ServiceError {
        accountService.addAmount(account, amount);
    }

    @Override
    public void makeAWithdrawal(String account, float amount) throws ServiceError {
        accountService.removeAmount(account, amount);
    }

    @Override
    public String getOperationsHistory(String account) throws ServiceError {
        return accountService.getAccountHistory(account);
    }

    @Override
    public void createAccount(String id) {
        accountService.createAccount(id);
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
