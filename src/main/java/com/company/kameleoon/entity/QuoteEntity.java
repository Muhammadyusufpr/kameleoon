package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "quotes")
public class QuoteEntity extends BaseEntity {
    //content, date of creation / update, link to user who posted it, link to votes;
    @Column
    private String content;

    @Column(name = "profile_id")
    private String profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column
    @CreationTimestamp
    protected LocalDateTime updatedDate;


}
