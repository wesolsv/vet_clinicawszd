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
    @Size(min=3, message = "O nome do tutor deve ter no minimo 3 caracteres")
    private String nome;

    @Column(name = "telefone")
    @Size(min=10, max = 15, message = "O telefone do tutor deve ter min 10 e max 15 caracteres")
    private String telefone;

    @Column(name = "cpf")
    @NotBlank(message = "CPF é obrigatório")
    @Size(min=11,max = 11, message = "O CPF deve ter no min 11 e max 15 caracteres")
    private String cpf;

    @Column(name = "email")
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(name = "endereco")
    private String endereco;

//    public Tutor(Long id) {
//        this.id = id;
//    }

    public static class Builder{
        private String nome;
        private String telefone;
        private String cpf;
        private String email;
        private String endereco;

        public Builder nome(String nome){
            this.nome = nome;
            return this;
        }
        public Builder telefone(String telefone){
            this.telefone = telefone;
            return this;
        }
        public Builder cpf(String cpf){
            this.cpf = cpf;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder endereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public Tutor build() {
            return new Tutor(this);
        }
    }

    private Tutor(Builder builder){
        nome = builder.nome;
        telefone = builder.telefone;
        cpf = builder.cpf;
        email = builder.email;
        endereco = builder.endereco;
    }

}
