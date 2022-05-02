package service;

import java.util.Date;
import java.util.List;

import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;

public interface ServiceFormacion {
	/**
	 * - Validar usuarios. Dada usuario y contraseña, obtener los datos del alumno
		- Cursos de un alumno. A partir del usuario, obtener la lista de cursos en donde está matriculado en alumno
		- Lista de cursos
		- Alumnos por curso. A partir de un nombre de curso, se devuelve los alumnos matriculados en dicho curso
		- Matricular alumno. A partir del usuario e idCurso, el alumno se matriula en dicho curso
	 */
	
	AlumnoDto validarUsuario(String usuario, String password);
	List<CursoDto>	cursosDelAlumno(String usuario);
	List<CursoDto> listaDeCursos();
	public List<AlumnoDto> listaDeAlumnos();
	List<AlumnoDto> alumosPorCurso(String nombreCurso);
	boolean matriculaAlumno(String usuario, int idCurso);
	
	//public List<Curso> findCursoMatriculados(String usuario);
	public boolean altaCurso(CursoDto curso);
	public boolean altaAlumno(AlumnoDto alumno);
	public List<MatriculaDto> consultarMatriculas(Date f1, Date f2);
	public List<CursoDto> cursosPosibleMatricula(String usuario);
}
