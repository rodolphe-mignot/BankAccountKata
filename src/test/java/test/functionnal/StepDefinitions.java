package test.functionnal;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kata.model.ServiceError;
import kata.services.BasicBankService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private final BasicBankService bankService = new BasicBankService();
    private String id;
    private String bankStatement;

    @Given("a bank client that has an existing account")
    public void a_bank_client() {
        id = UUID.randomUUID().toString();
        bankService.createAccount(id);
        this.bankStatement = null;
    }

    @When("he makes a deposit of {double} to his account")
    public void heMakesADepositOfToHisAccount(double amount) throws ServiceError {
        bankService.makeADeposit(id, (float) amount);
    }

    @Then("his account is credited with {double}")
    public void hisAccountIsCreditedWith(double amount) throws ServiceError {
        assertEquals(amount, bankService.getAccountService().getAccount(id).getFunds().doubleValue());
    }

    @And("he has a positive balance of {double} on his account")
    public void heHasAPositiveBalanceOfOnHisAccount(double amount) throws ServiceError {
        bankService.makeADeposit(id, (float) amount);
    }

    @When("he makes a withdrawal of {double} from his account")
    public void heMakesAWithdrawalOfFromHisAccount(double amount) throws ServiceError {
        bankService.makeAWithdrawal(id, (float) amount);
    }


    @Then("his account has a remained balance of {double}")
    public void hisAccountHasARemainedBalanceOf(double amount) throws ServiceError {
        assertEquals(bankService.getAccountService().getAccount(id).getFunds().doubleValue(), amount);
    }

    @When("he checks the history of his account")
    public void heChecksTheHistoryOfHisAccount() throws ServiceError {
        assertDoesNotThrow(() -> {
            this.bankStatement = bankService.getOperationsHistory(id);
        });
    }

    @Then("the bank statement is given")
    public void theBankStatementIsGiven() {
        assertTrue(this.bankStatement.length() > 0);
    }
}
