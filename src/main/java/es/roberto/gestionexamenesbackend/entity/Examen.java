package es.roberto.gestionexamenesbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;

    private String titulo;
    private String descripcion;
    private String puntosMaximos;
    private String numeroDePreguntas;
    private boolean activo = false;
    private double nota;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "examen")
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;


}
