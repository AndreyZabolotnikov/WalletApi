package ru.duxa.walletapi.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {
    @NotNull(message = "WalletId cannot be null")
    @Min(value = 1, message = "WalletId cannot be less than 1")
    private long walletId;
    @NotEmpty(message = "OperationType cannot be empty")
    private String operationType;
    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount cannot be less than 1")
    private double amount;

    @Override
    public String toString() {
        return "WalletDTO{" +
                "walletId=" + walletId +
                ", operationType='" + operationType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
