package com.example.survey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credentials implements Serializable {

    @Id             //klucze nie są generowane tylko pobierane z idUser
    @Column(name = "idUser")
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @MapsId         //primary key kopiowany od usera
    @JsonBackReference
    private RegisteredUser idUser;

    private String password;
}
