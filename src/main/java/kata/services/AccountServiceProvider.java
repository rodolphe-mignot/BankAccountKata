package kata.services;

import kata.api.AccountService;

import java.util.Optional;

public class AccountServiceProvider {

    private static AccountService accountService;

    private AccountServiceProvider() {
    }

    public static AccountService getAccountService() {
        return Optional.ofNullable(accountService).orElseGet(BasicAccountService::new);
    }

    public static void setAccountService(AccountService accountService) {
        AccountServiceProvider.accountService = accountService;
    }
}
