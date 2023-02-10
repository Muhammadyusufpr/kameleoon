package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "votes")
public class VotesEntity extends BaseEntity {
    @Column(name = "quote_id")
    private String quoteId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private QuoteEntity quote;


}
