package kata.api;

import kata.model.Account;
import kata.model.ServiceError;

public interface AccountService {
    void addAmount(String account, float amount) throws ServiceError;

    void removeAmount(String account, float amount) throws ServiceError;

    String getAccountHistory(String account) throws ServiceError;

    Account getAccount(String id) throws ServiceError;

    void createAccount(String id);
}
