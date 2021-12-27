package kata.model;


import kata.utils.NumberUtils;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {

    private final ZonedDateTime operationTime;
    private final OperationType operationType;
    private final BigDecimal operationAmount;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");

    public Operation(ZonedDateTime time, OperationType op, BigDecimal amount) {
        this.operationTime = time;
        this.operationType = op;
        this.operationAmount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "operationTime=" + operationTime.format(formatter) +
                ", operationType=" + operationType +
                ", operationAmount=" + NumberUtils.roundAndUnwrap(operationAmount) +
                '}';
    }
}
