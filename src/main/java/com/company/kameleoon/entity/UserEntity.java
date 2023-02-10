package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    //name, email, password and date of creation;
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "quote_id")
    private String quoteId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private QuoteEntity quote;
}
