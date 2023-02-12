package com.company.kameleoon.entity;

import com.company.kameleoon.enums.VotesStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "votes")
public class VotesEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private VotesStatus status;

    @Column(name = "quote_id")
    private String quoteId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private QuoteEntity quote;

    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;


}
