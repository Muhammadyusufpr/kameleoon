package com.company.kameleoon.repository;

import com.company.kameleoon.entity.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, String> {
    ConfirmationTokenEntity findByConfirmationToken(String confirmation);

}
