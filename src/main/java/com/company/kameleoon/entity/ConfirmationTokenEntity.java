package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "confirmation")
public class ConfirmationTokenEntity extends BaseEntity {
    @Column
    private String confirmationToken;


    @Column(name = "profile_id")
    private String profileId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;


    public ConfirmationTokenEntity(ProfileEntity profile) {
        this.profile = profile;
        this.createdDate = LocalDateTime.now();
        this.confirmationToken = UUID.randomUUID().toString();
    }


}
