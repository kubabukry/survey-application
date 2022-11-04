package com.example.survey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long nip;
    private String address;
    private Boolean isVerified;

    @OneToOne(optional = false)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private RegisteredUser idUser;
}
