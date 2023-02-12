package com.company.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    //name, email, password and date of creation;
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean isEnable;

}
