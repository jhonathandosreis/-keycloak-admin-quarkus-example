package com.tecnologia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "sobre_nome")
    private String sobreNome;
    @Column(name = "idade")
    private Integer idade;
}
