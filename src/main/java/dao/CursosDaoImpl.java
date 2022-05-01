package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import model.Curso;

@Repository
public class CursosDaoImpl implements CursosDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Curso findById(int idCurso) {
		em.find(Curso.class, idCurso);
		return null;
	}

	@Override
	public List<Curso> findAll() {
		String jpql = "select distinct(c) from Curso c";
		TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
		return query.getResultList();
	}

	@Override
	public List<Curso> findByAlumno(String usuario) {
		String jpql = "select distinct(c) from Curso c join c.alumnos a where a.usuario=?1";
		TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
		query.setParameter(1, usuario);
		return query.getResultList();
	}

}
