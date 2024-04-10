package org.example.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionData {
    private String coin;
    private String transactionId;
    private long timestamp;
    private BigDecimal amount;
    private BigDecimal price;
}
