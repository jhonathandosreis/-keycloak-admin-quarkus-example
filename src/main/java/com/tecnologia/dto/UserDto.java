package com.tecnologia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Sobre nome é obrigatório")
    private String sobreNome;
    @NotNull(message = "Idade é obrigatório")
    private Integer idade;
}
