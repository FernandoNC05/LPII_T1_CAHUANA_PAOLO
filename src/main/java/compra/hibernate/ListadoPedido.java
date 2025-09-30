package compra.hibernate;

import compra.hibernate.dao.PedidoDao;
import compra.hibernate.model.Pedido;
import compra.hibernate.util.HibernateUtil;

import java.util.List;

public class ListadoPedido {

    public static void main(String[] args) {
        PedidoDao dao = new PedidoDao();

        System.out.println("\n==== LISTADO DE PEDIDOS ====");
        List<Pedido> pedidos = dao.listar();

        if (pedidos.isEmpty()) {
            System.out.println("(No hay pedidos registrados)");
        } else {
            for (Pedido p : pedidos) {
                System.out.printf("ID: %d | Total Items: %d | Precio: %.2f | Cliente ID: %d%n",
                        p.getId(),
                        p.getTotalItems(),
                        p.getPrecio(),
                        p.getCliente() != null ? p.getCliente().getId() : null
                );
            }
        }

        HibernateUtil.close();
    }
}