package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import model.Curso;
import service.ServiceFormacion;

@CrossOrigin("*")
@Controller
public class FormacionController {
	@Autowired
	ServiceFormacion service;
	
	@PostMapping("Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
		if(service.validarUsuario(usuario, password) == null) {
			return "Login";
		}
		return "menu";
	}
	
	@GetMapping(value = "Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursos(){
		return service.listaDeCursos();
	}
	
	@GetMapping(value = "Alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnos(){
		return service.listaDeAlumnos();
	}
	
	@GetMapping(value = "CursosAlumno", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursosDelAlumno(@RequestParam("usuario") String usuario){
		return service.cursosDelAlumno(usuario);
	}
	
	@GetMapping(value = "AlumnosCurso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Alumno> alumnosDelCurso(@RequestParam("curso") String curso){
		return service.alumosPorCurso(curso);
	}
}
