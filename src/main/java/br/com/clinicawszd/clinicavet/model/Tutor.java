package br.com.clinicawszd.clinicavet.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "tutor")
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tutor")
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @Column(name = "telefone")
    @NotBlank(message = "Telefone é obrigatório!")
    private String telefone;

    @Column(name = "cpf")
    @NotBlank(message = "CPF é obrigatório!")
    private String cpf;

    @Column(name = "email")
    @NotBlank(message = "Email é obrigatório!")
    private String email;

    @Column(name = "endereco")
    private String endereco;

}
