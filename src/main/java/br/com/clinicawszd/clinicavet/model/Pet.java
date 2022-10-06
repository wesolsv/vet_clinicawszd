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

    public static class Builder{
        private String nome;
        private Integer idade;
        private Porte porte;
        private String tipo;
        private Tutor tutor;

        public Pet.Builder nome(String nome){
            this.nome = nome;
            return this;
        }
        public Pet.Builder idade(Integer idade){
            this.idade = idade;
            return this;
        }
        public Pet.Builder porte(Porte porte){
            this.porte = porte;
            return this;
        }
        public Pet.Builder tipo(String tipo){
            this.tipo = tipo;
            return this;
        }
        public Pet.Builder tutor(Tutor tutor) {
            this.tutor = tutor;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }

    private Pet(Pet.Builder builder){
        nome = builder.nome;
        idade = builder.idade;
        porte = builder.porte;
        tipo = builder.tipo;
        tutor = builder.tutor;
    }
}
