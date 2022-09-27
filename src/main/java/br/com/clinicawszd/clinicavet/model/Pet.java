package br.com.clinicawszd.clinicavet.model;

import br.com.clinicawszd.clinicavet.util.Porte;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "pet")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    @Column(name = "tipo")
    @NotBlank(message = "Tipo é obrigatório!")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Tutor tutor;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pet")
    private List<Agendamento> agendamentos;

}
