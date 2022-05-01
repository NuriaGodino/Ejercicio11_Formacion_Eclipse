package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="alumnos")
public class Alumno {
	@Id
	private String usuario;
	private String password;
	private String nombre;
	private String email;
	private Integer edad;
	@JsonIgnore //para que no me meta la lista de cursos en el json que se crea
	@ManyToMany() //si yo quiero los alumnos no quiero que me de los cursos porque me va a dar un bucle infinito asi que debo evitar que se manden por json los obj relacionados de tipo coleccion
	@JoinTable(name="matriculas",
		joinColumns = @JoinColumn(name="usuario",referencedColumnName = "usuario"), 
        inverseJoinColumns = @JoinColumn(name="idCurso", referencedColumnName ="idCurso"))
	private List<Curso> cursos;
	
	public Alumno(String usuario, String password, String nombre, String email, Integer edad) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}
}
