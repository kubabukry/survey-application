package com.example.survey.model;

import com.sun.istack.NotNull;
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

    @Id
    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private RegisteredUser idUser;

    @NotNull
    private String password;
}
