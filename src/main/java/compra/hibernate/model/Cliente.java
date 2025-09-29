package compra.hibernate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Long id;

    @NotBlank(message = "nombres no puede ser vacío")
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @NotBlank(message = "apellidos no puede ser vacío")
    @Column(name = "apellidos", nullable = false, length = 120)
    private String apellidos;

    @NotBlank(message = "celular no puede ser vacío")
    @Column(name = "celular", nullable = false, length = 20, unique = true)
    private String celular;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}
    public Cliente(String nombres, String apellidos, String celular) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
    }

    public Long getId() { return id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public List<Pedido> getPedidos() { return pedidos; }

    @Override public String toString() {
        return "Cliente{id=%d, nombres='%s', apellidos='%s', celular='%s'}"
                .formatted(id, nombres, apellidos, celular);
    }
}