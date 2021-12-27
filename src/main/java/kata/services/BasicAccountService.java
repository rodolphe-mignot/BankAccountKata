package kata.services;

import kata.api.AccountService;
import kata.model.Account;
import kata.model.Operation;
import kata.model.OperationType;
import kata.model.ServiceError;
import kata.utils.NumberUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BasicAccountService implements AccountService {

    private static final BigDecimal THRESHOLD = new BigDecimal(0);

    private static final Map<String, Account> accountMap = new HashMap<>();

    private static final String LINE_DELIMITER = "\r\n";

    @Override
    public void addAmount(String accountId, float amount) throws ServiceError {
        BigDecimal bgAmount = NumberUtils.wrapAndRound(amount);
        Account account = getAccount(accountId);
        account.setFunds(account.getFunds().add(bgAmount));
        Operation op = new Operation(ZonedDateTime.now(), OperationType.DEPOSIT, bgAmount);
        account.getOperations().add(op);

    }

    @Override
    public void removeAmount(String accountId, float amount) throws ServiceError {
        BigDecimal bgAmount = NumberUtils.wrapAndRound(amount);
        Account account = getAccount(accountId);
        BigDecimal newFunds = account.getFunds().subtract(bgAmount);
        if (newFunds.compareTo(THRESHOLD) < 0) {
            throw new ServiceError();
        } else {
            account.setFunds(newFunds);
            Operation op = new Operation(ZonedDateTime.now(), OperationType.WITHDRAWAL, bgAmount);
            account.getOperations().add(op);
        }
    }

    @Override
    public String getAccountHistory(String accountId) throws ServiceError {
        Account account = getAccount(accountId);
        String output = "Current balance : " + account.getFunds() + LINE_DELIMITER;
        return output + String.join(LINE_DELIMITER, account.getOperations().stream().map(Object::toString).collect(Collectors.joining(LINE_DELIMITER)));

    }

    @Override
    public Account getAccount(String id) throws ServiceError {
        return Optional.ofNullable(accountMap.get(id)).orElseThrow(ServiceError::new);
    }

    @Override
    public void createAccount(String id) {
        accountMap.put(id, new Account());
    }
}
