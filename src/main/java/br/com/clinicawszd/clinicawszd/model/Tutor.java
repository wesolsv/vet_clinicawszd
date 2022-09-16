package br.com.clinicawszd.clinicawszd.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tutor")
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "endereco")
    private String endereco;

}
