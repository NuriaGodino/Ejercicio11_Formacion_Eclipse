package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCurso;
	private String nombre;
	private int duracion;
	private double precio;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@JsonIgnore //para que cuando haga el json me meta la lista de alumno porque sino acabo teniendo un bucle infinito
	@ManyToMany(mappedBy="cursos") //si quiero que la carga sea eager tengo que añadir en esta anotacion ,FetchType.EAGER
	private List<Alumno> alumnos; //en la carga, este tipo de objeto no se selecciona. Si quiero que se lo traiga tengo que hacer una carga Eager
	
	public Curso(int idCurso, String nombre, int duracion, double precio, Date fechaInicio) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.duracion = duracion;
		this.precio = precio;
		this.fechaInicio = fechaInicio;
	}
}
