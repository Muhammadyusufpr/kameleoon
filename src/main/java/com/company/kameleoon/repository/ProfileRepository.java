package com.company.kameleoon.repository;

import com.company.kameleoon.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    ProfileEntity findByEmail(String email);

    ProfileEntity findByEmailAndPassword(String email, String password);


}
