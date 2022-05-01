package service;

import java.util.List;

import model.Alumno;
import model.Curso;

public interface ServiceFormacion {
	/**
	 * - Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
		- Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
		- Lista de cursos
		- Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
		- Matricular alumno. A partir del usuario e idCurso, el alumno se matriula en dicho curso
	 */
	
	Alumno validarUsuario(String usuario, String password);
	List<Curso>	cursosDelAlumno(String usuario);
	List<Curso> listaDeCursos();
	public List<Alumno> listaDeAlumnos();
	List<Alumno> alumosPorCurso(String nombreCurso);
	void matriculaAlumno(String usuario, int idCurso);
}
