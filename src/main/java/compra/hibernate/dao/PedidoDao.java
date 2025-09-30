package compra.hibernate.dao;

import compra.hibernate.util.HibernateUtil;
import compra.hibernate.model.Pedido;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PedidoDao {

    public List<Pedido> listar() {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pedido p ORDER BY p.id", Pedido.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}