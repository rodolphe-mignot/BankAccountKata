package kata.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String identity;
    private BigDecimal funds;
    private List<Operation> operations;

    public Account() {
        this.funds = new BigDecimal(0);
        this.operations = new ArrayList<>();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public BigDecimal getFunds() {
        return funds.setScale(2, RoundingMode.HALF_DOWN);
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

}
