package compra.hibernate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedido")
    private Long id;

    @NotNull
    @Min(0)
    @Column(name = "total_items", nullable = false)
    private Integer totalItems;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente cliente;

    public Pedido() {}
    public Pedido(Integer totalItems, BigDecimal precio, Cliente cliente) {
        this.totalItems = totalItems;
        this.precio = precio;
        this.cliente = cliente;
    }

    public Long getId() { return id; }
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    @Override public String toString() {
        return "Pedido{id=%d, totalItems=%d, precio=%s, clienteId=%s}"
                .formatted(id, totalItems, precio, cliente != null ? cliente.getId() : null);
    }
}