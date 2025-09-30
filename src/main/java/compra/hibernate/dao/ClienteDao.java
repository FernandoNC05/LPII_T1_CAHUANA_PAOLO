package compra.hibernate.dao;

import compra.hibernate.util.HibernateUtil;
import compra.hibernate.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class ClienteDao {

	    public void crear(Cliente c) {
	        EntityManager em = HibernateUtil.getEmf().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(c);
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	    }

	    public void actualizar(Cliente c) {
	        EntityManager em = HibernateUtil.getEmf().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(c);
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	    }

	    public Cliente buscarPorId(Long id) {
	        EntityManager em = HibernateUtil.getEmf().createEntityManager();
	        try {
	            return em.find(Cliente.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    public Cliente buscarPorCelular(String celular) {
	        EntityManager em = HibernateUtil.getEmf().createEntityManager();
	        try {
	            TypedQuery<Cliente> q = em.createQuery(
	                "SELECT c FROM Cliente c WHERE c.celular = :cel", Cliente.class);
	            q.setParameter("cel", celular);
	            List<Cliente> res = q.getResultList();
	            return res.isEmpty() ? null : res.get(0);
	        } finally {
	            em.close();
	        }
	    }

	    public List<Cliente> listar() {
	        EntityManager em = HibernateUtil.getEmf().createEntityManager();
	        try {
	            return em.createQuery("SELECT c FROM Cliente c ORDER BY c.id", Cliente.class)
	                     .getResultList();
	        } finally {
	            em.close();
	        }
	    }
}