package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AlumnoDao;
import dao.CursosDao;
import model.Alumno;
import model.Curso;

@Service
public class ServiceFormacionImpl implements ServiceFormacion {

	//@Autowired Se lo quitamos y se lo ponemos por un constructor
	AlumnoDao alumnosDao;
	
	//@Autowired Se lo quitamos y se lo ponemos por un constructor
	CursosDao cursosDao;
	
	public ServiceFormacionImpl(@Autowired AlumnoDao alumnosDao, @Autowired CursosDao cursosDao) {
		this.alumnosDao = alumnosDao;
		this.cursosDao = cursosDao;
	}
	
	@Override
	public Alumno validarUsuario(String usuario, String password) {
		return alumnosDao.findByUsuarioAndPassword(usuario, password);
	}

	@Override
	public List<Curso> cursosDelAlumno(String usuario) {
		return cursosDao.findByAlumno(usuario);
	}

	@Override
	public List<Curso> listaDeCursos() {
		return cursosDao.findAll();
	}
	
	public List<Alumno> listaDeAlumnos(){
		return alumnosDao.findAll();
	}

	@Override
	public List<Alumno> alumosPorCurso(String nombreCurso) {
		return alumnosDao.findByCurso(nombreCurso);
	}
	
	
	@Override
	public void matriculaAlumno(String usuario, int idCurso) {
		Curso curso = cursosDao.findById(idCurso);
		Alumno alumno = alumnosDao.findById(usuario);
		if(curso != null && alumno != null) {
			alumnosDao.update(alumno);
		}
		
	}

//	@PersistenceContext
//	EntityManager em;
//	
//	@Override
//	public Alumno validarUsuario(String usuario, String password) {
//		String jpql = "select a from Alumno a where a.usuario=?1 AND a.password=?2";
//		TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
//		query.setParameter(1, usuario);
//		query.setParameter(2, password);
//		List<Alumno> alumnos = query.getResultList();
//		return alumnos.size()>0?alumnos.get(0):null;
//	}
//
//	@Override
//	public List<Curso> cursosDelAlumno(String usuario) {
//		String jpql = "select distinct(c) from Curso c join c.alumnos a where a.usuario=?1";
//		TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
//		query.setParameter(1, usuario);
//		return query.getResultList();
//	}
//
//	@Override
//	public List<Curso> listaDeCursos() {
//		String jpql = "select distinct(c) from Curso c";
//		TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
//		return query.getResultList();
//	}
//
//	@Override
//	public List<Alumno> alumosPorCurso(String nombre) {
//		String jpql = "select a from Alumno a join Curso c where c.nombre=?1";
//		TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
//		query.setParameter(1, nombre);
//		List<Alumno> alumnos = query.getResultList();
//		return alumnos.size()>0?alumnos:null;
//	}
//	
//	@Transactional
//	@Override
//	public void matriculaAlumno(String usuario, int idCurso) {
//		Alumno a = buscaAlumno(usuario);
//		Curso c = buscaCurso(idCurso);
//		if(a != null && c != null) {
//			a.getCursos().add(c);
//			em.merge(a);
//		}
//	}
//	
//	public Alumno buscaAlumno(String usuario) {
//		String jpql = "select a from Alumno a where a.usuario=:usuario";
//		TypedQuery<Alumno> query=em.createQuery(jpql, Alumno.class);
//		query.setParameter("usuario", usuario);
//		try {
//			return query.getSingleResult();
//		}catch(NoResultException e){
//			return null;
//		}
//	}
//	
//	public Curso buscaCurso(Integer idCurso) {
//		String jpql = "select c from Curso c where c.idCurso=:idCurso";
//		TypedQuery<Curso> query=em.createQuery(jpql, Curso.class);
//		query.setParameter("idCurso", idCurso);
//		try {
//			return query.getSingleResult();
//		}catch(NoResultException e) {
//			return null;
//		}
//	}


}
