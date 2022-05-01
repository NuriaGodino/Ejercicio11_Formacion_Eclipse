package testing;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import dao.AlumnoDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestMocks {
	@Mock
	AlumnoDao alumnosDao;
	
	@Mock
	CursosDao cursosDao;
	
	List<Alumno> alumnos;
	List<Curso> cursos;
	
	@BeforeEach //para que antes de cada llamada al metdo test se cosntruya la mini bbdd en memora
	void init(){
		cursos=List.of(new Curso(1,"curso1",100,10,null),
				new Curso(2,"curso2",200,20,null),
				new Curso(3,"curso3",300,30,null));
		alumnos=List.of(new Alumno("user1","pwd1","n1","e1",10,List.of(cursos.get(0),cursos.get(1))),
				new Alumno("user2","pwd2","n2","e2",20,List.of(cursos.get(0))),
				new Alumno("user3","pwd3","n3","e3",30,List.of(cursos.get(2))),
				new Alumno("user4","pwd4","n4","e4",10,List.of(cursos.get(0),cursos.get(2))));
		when(alumnosDao.findByUsuarioAndPassword("user1", "pwd1")).thenReturn(alumnos.get(0));
		when(alumnosDao.findByUsuarioAndPassword("user7", "aaa")).thenReturn(null);
		when(alumnosDao.findByCurso("curso1")).thenReturn(List.of(alumnos.get(0), alumnos.get(1), alumnos.get(3)));
	}
}
