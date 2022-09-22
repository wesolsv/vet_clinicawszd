package br.com.clinicawszd.clinicavet.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @Size(min=10, max = 15, message = "O telefone do tutor deve ter min 10 e max 11 caracteres")
    private String telefone;

    @Column(name = "cpf")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Column(name = "email")
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(name = "endereco")
    private String endereco;

}
