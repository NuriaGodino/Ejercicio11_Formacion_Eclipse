package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.AlumnoDao;
import dao.CursosDao;
import dao.MatriculaDao;
import dto.AlumnoDto;
import dto.CursoDto;
import dto.MatriculaDto;
import model.Alumno;
import model.Curso;
import model.Matricula;
import model.MatriculaPk;

@Service
public class ServiceFormacionImpl implements ServiceFormacion {

	@Autowired
	ConversorEntityDto conversor;
	
	//@Autowired Se lo quitamos y se lo ponemos por un constructor
	AlumnoDao alumnosDao;
	
	//@Autowired Se lo quitamos y se lo ponemos por un constructor
	CursosDao cursosDao;
	MatriculaDao matriculaDao;
	
	public ServiceFormacionImpl(@Autowired AlumnoDao alumnosDao, @Autowired CursosDao cursosDao, @Autowired MatriculaDao matriculaDao) {
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
		this.matriculaDao = matriculaDao;
	}
	
	@Override
	public AlumnoDto validarUsuario(String usuario, String password) {
		return conversor.alumnoToDto(alumnosDao.findByUsuarioAndPassword(usuario, password));
	}

	@Override
	public List<CursoDto> cursosDelAlumno(String usuario) {
		//tenemos que recorrer la lista y transformar uno por uno
		return cursosDao.findByAlumno(usuario).stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> listaDeCursos() {
		return cursosDao.findAll().stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}
	
	public List<AlumnoDto> listaDeAlumnos(){
		return alumnosDao.findAll().stream().map(c -> conversor.alumnoToDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<AlumnoDto> alumosPorCurso(String nombreCurso) {
		return alumnosDao.findByCurso(nombreCurso).stream().map(c -> conversor.alumnoToDto(c)).collect(Collectors.toList());
	}
	
	
	@Override
	public boolean matriculaAlumno(String usuario, int idCurso) {
		Optional<Curso> curso = cursosDao.findById(idCurso);
		Optional<Alumno> alumno = alumnosDao.findById(usuario);
		if(curso.isPresent() && alumno.isPresent()) {
			matriculaDao.save(new Matricula(new MatriculaPk(idCurso, usuario), 0, curso.get(), alumno.get()));
			return true;
		}
		return false;
	}

	@Override
	public boolean altaCurso(CursoDto curso) {
		if(cursosDao.findByNombre(curso.getNombre()).isEmpty()){
			cursosDao.save(conversor.dtoToCurso(curso));
			return true;
		}
		return false;
	}

	@Override
	public boolean altaAlumno(AlumnoDto alumno) {
		if(alumnosDao.findById(alumno.getUsuario()).isEmpty()) {
			alumnosDao.save(conversor.dtoToAlumno(alumno));
			return true;
		}
		return false;
	}

	@Override
	public List<MatriculaDto> consultarMatriculas(Date f1, Date f2) {
		return matriculaDao.findByMatriculasFechas(f1, f2).stream().map(m -> conversor.matriculaToDto(m)).collect(Collectors.toList());
	}

	@Override
	public List<CursoDto> cursosPosibleMatricula(String usuario) {
		return cursosDao.findByAlumnoNoMatriculado(usuario).stream().map(c -> conversor.cursoToDto(c)).collect(Collectors.toList());
	}
}
