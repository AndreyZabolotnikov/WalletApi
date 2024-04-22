package ru.duxa.walletapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.duxa.walletapi.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByUUID(long UUID);
}
