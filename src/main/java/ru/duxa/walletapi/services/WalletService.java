package ru.duxa.walletapi.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.duxa.walletapi.dto.WalletDTO;
import ru.duxa.walletapi.models.Wallet;
import ru.duxa.walletapi.repositories.WalletRepository;
import ru.duxa.walletapi.util.WalletNotChangeException;
import ru.duxa.walletapi.util.WalletNotFoundException;

import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet findWallet(long walletUUID) {
        Optional<Wallet> foundWallet = walletRepository.findById(walletUUID);
        return foundWallet.orElseThrow(WalletNotFoundException::new);
    }

    public double getWalletBalance(long walletUUID) {
        Wallet wallet = findWallet(walletUUID);
        double balance = wallet.getBalance();
        return balance;
    }

    @Transactional
    public void updateWallet(WalletDTO walletDTO) {
        long walletId = walletDTO.getWalletId();
        Wallet wallet = walletRepository.findByUUID(walletId);
        Double newAmount = wallet.getBalance();
        if (walletDTO.getOperationType().equals("DEPOSIT")) {
            newAmount += walletDTO.getAmount();
        } else if (walletDTO.getOperationType().equals("WITHDRAWAL") && newAmount >= walletDTO.getAmount()) {
            newAmount -= walletDTO.getAmount();
        } else {
            throw new WalletNotChangeException("Insufficient funds in the account");
        }
        wallet.setBalance(newAmount);
    }
}
