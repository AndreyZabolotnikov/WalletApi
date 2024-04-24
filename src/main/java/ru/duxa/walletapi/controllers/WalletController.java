package ru.duxa.walletapi.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.duxa.walletapi.dto.WalletDTO;
import ru.duxa.walletapi.services.WalletService;
import ru.duxa.walletapi.util.WalletErrorResponse;
import ru.duxa.walletapi.util.WalletNotChangeException;
import ru.duxa.walletapi.util.WalletNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WalletController {


    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public Double getWalletBalance(@PathVariable("WALLET_UUID") long walletUUID) {
        Double balance = walletService.getWalletBalance(walletUUID);
        return balance;
    }

    @PostMapping("/wallet")
    public ResponseEntity<HttpStatus> changeWallet(@RequestBody @Valid WalletDTO walletDTO, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new WalletNotChangeException(errorMessage.toString());
        } else if (walletService.findWallet(walletDTO.getWalletId()) == null) {
            throw new WalletNotFoundException();
        } else {
            walletService.updateWallet(walletDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @ExceptionHandler
    private ResponseEntity<WalletErrorResponse> handleWalletException(WalletNotFoundException e) {
        WalletErrorResponse response = new WalletErrorResponse(
                "Wallet not found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<WalletErrorResponse> handleWalletException(WalletNotChangeException e) {
        WalletErrorResponse response = new WalletErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
