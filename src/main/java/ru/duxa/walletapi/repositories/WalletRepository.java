package ru.duxa.walletapi.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.duxa.walletapi.models.Wallet;

@Repository

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Wallet findByUUID(long UUID);
}
