# Bank Service kata
## Installation

Use following maven dependency

        <dependency>
            <groupId>rodolphe.mignot.kata.bank.service</groupId>
            <artifactId>BankAccountKata</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>


## Usage



```java
package bankservice.usage;
import kata.api.BankService;
import kata.model.ServiceError;
import kata.services.BasicBankService;

import java.util.UUID;
import java.util.logging.Logger;

public class BankServiceUsage {

    private static final Logger LOGGER = Logger.getLogger(BankServiceUsage.class.getName());

    public static void main(String[] args) throws ServiceError {
        BankService service = new BasicBankService();
        String uuid = UUID.randomUUID().toString();
        service.createAccount(uuid);
        service.makeADeposit(uuid, 125);
        service.makeAWithdrawal(uuid, 50.98f);
        LOGGER.info(service.getOperationsHistory(uuid));
    }
}

```