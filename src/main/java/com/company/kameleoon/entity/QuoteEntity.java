package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "quote")
public class QuoteEntity extends BaseEntity {
    //content, date of creation / update, link to user who posted it, link to votes;
    @Column
    private String content;

    @Column(name = "user_id")
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column
    @CreationTimestamp
    protected LocalDateTime updatedDate;


}
