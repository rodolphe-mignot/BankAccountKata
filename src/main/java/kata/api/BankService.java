package kata.api;


import kata.model.ServiceError;

public interface BankService {
    void makeADeposit(String account, float amount) throws ServiceError;

    void makeAWithdrawal(String account, float amount) throws ServiceError;

    String getOperationsHistory(String account) throws ServiceError;

    void createAccount(String id);
}
