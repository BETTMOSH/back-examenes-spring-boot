package es.roberto.gestionexamenesbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * @author Roberto Ledezma
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String nombre;
    private String apellido;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(nullable = false, unique = true)
    private String email;
    private String telefono;
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Examen> examenes = new HashSet<>();



    // Metodo para obtener los roles del usuario y convertirlos en GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.usuarioRoles.stream()
                .map(usuarioRol -> new SimpleGrantedAuthority(usuarioRol.getRol().getRolNombre()))
                .collect(Collectors.toList());
    }

}


