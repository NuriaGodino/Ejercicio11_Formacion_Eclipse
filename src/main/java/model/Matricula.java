package model;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="matriculas")
public class Matricula {
	@EmbeddedId //para decirle que esto es clave primaria 
	private MatriculaPk id;
	private double nota;
	@ManyToOne()
	@JoinColumn(name = "idCurso", referencedColumnName = "idCurso", insertable = false, updatable = false)
	Curso curso;
	@ManyToOne()
	@JoinColumn(name = "usuario", referencedColumnName = "usuario", insertable = false, updatable = false)
	Alumno alumno;
	
	public Matricula(double nota, Curso curso, Alumno alumno) {
		super();
		this.nota = nota;
		this.curso = curso;
		this.alumno = alumno;
	}
	
	//la fk usuario y la fk idCurso esta repetida en la entidad y nos dara un error para evitar la duplicidad lo que debemos hacer  en la anotcaion joincolum a?adir las dos ultimas prop a valor false
	
}
