package com.company.kameleoon.repository;

import com.company.kameleoon.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {
    ProfileEntity findByEmail(String email);

    ProfileEntity findByEmailAndPassword(String email, String password);


}
