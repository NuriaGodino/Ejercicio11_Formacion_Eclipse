package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Alumno;

@Repository
public class AlumnoDaoImpl implements AlumnoDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Alumno findByUsuarioAndPassword(String usuario, String password) {
		String jpql = "select a from Alumno a where a.usuario=?1 AND a.password=?2";
		TypedQuery<Alumno> query = em.createQuery(jpql, Alumno.class);
		query.setParameter(1, usuario);
		query.setParameter(2, password);
		try {
			return query.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Alumno> findByCurso(String nombreCurso) {
		String jpql = "select a from Alumno a join a.cursos c where c.nombre=:nombre";
		TypedQuery<Alumno> query = em.createQuery(jpql,Alumno.class);
		query.setParameter("nombre", nombreCurso);
		return query.getResultList();
	}

	@Override
	public Alumno findById(String usuario) {
		return em.find(Alumno.class, usuario);
	}
	
	public List<Alumno> findAll(){
		String jpql = "select a from Alumno a";
		TypedQuery<Alumno> quert = em.createQuery(jpql, Alumno.class);
		return quert.getResultList();
	}

	@Transactional
	@Override
	public void update(Alumno alumno) {
		em.merge(alumno);
	}

}
