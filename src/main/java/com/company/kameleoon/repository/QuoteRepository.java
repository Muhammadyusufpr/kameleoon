package com.company.kameleoon.repository;

import com.company.kameleoon.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteEntity, String> {

}
