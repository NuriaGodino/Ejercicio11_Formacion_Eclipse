package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;
import service.ServiceFormacion;

@CrossOrigin("*")
@Controller
public class FormacionController {
	@Autowired
	ServiceFormacion service;
	
	@PostMapping("Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		if(service.validarUsuario(usuario, password) == null) {
			return "error";
		}
		return "menu";
	}
	
	@GetMapping(value = "Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursos(){
		return service.listaDeCursos();
	}
	
	@GetMapping(value = "Alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnos(){
		return service.listaDeAlumnos();
	}
	
	@GetMapping(value = "CursosAlumno", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursosDelAlumno(@RequestParam("usuario") String usuario){
		return service.cursosDelAlumno(usuario);
	}
	
	@GetMapping(value = "AlumnosCurso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnosDelCurso(@RequestParam("curso") String curso){
		return service.alumosPorCurso(curso);
	}
	
	@PostMapping("AltaAlumno")
	public String altaAlumno(@ModelAttribute AlumnoDto a) {
		service.altaAlumno(a);
		return "altaAlumno";
	}
	
	@PostMapping("AltaCurso")
	public String altaCurso(@ModelAttribute CursoDto c, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("fecha") Date fecha) {
		c.setFechaInicio(fecha);
		service.altaCurso(c);
		return "altaCurso";
	}
	
	@GetMapping(value = "CursosNoMatriculados", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> posiblesMatriculas(@RequestParam("usuario") String usuario){
		return service.cursosPosibleMatricula(usuario);
	}
	
	
	@GetMapping(value="CursosFechaBetween", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MatriculaDto> cursosFechaBetween( @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("f1") Date first, 
														 @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("f2") Date second){
		return service.consultarMatriculas(first, second);
	}
	
	@PostMapping(value = "Matricular")
	public String matricular(@RequestParam("idCurso") int idCurso, @RequestParam("usuario") String usuario) {
		service.matriculaAlumno(usuario, idCurso);
		return "";
	}
	
	
}
