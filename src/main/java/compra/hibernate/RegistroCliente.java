package compra.hibernate;

import compra.hibernate.dao.ClienteDao;
import compra.hibernate.model.Cliente;
import compra.hibernate.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class RegistroCliente {

    private static final Scanner in = new Scanner(System.in);
    private static final ClienteDao dao = new ClienteDao();

    public static void main(String[] args) {
        try {
            boolean seguir = true;
            while (seguir) {
                System.out.println("\n==== MENU CLIENTES ====");
                System.out.println("1) Registrar nuevo cliente");
                System.out.println("2) Actualizar cliente (por ID o celular)");
                System.out.println("3) Listar clientes");
                System.out.println("0) Salir");
                System.out.print("Opción: ");
                String op = in.nextLine().trim();

                switch (op) {
                    case "1" -> registrar();
                    case "2" -> actualizar();
                    case "3" -> listar();
                    case "0" -> seguir = false;
                    default  -> System.out.println("Opción inválida.");
                }
            }
        } finally {
            HibernateUtil.close();
            in.close();
        }
    }

    private static void registrar() {
        System.out.println("\n-- Registrar cliente --");
        String nombres = leerNoVacio("Nombres: ");
        String apellidos = leerNoVacio("Apellidos: ");
        String celular = leerNoVacio("Celular: ");

        if (dao.buscarPorCelular(celular) != null) {
            System.out.println("Ya existe un cliente con ese celular.");
            return;
        }

        Cliente c = new Cliente(nombres, apellidos, celular);
        dao.crear(c);
        System.out.println("Registrado con ID: " + c.getId());
    }

    private static void actualizar() {
        System.out.println("\n-- Actualizar cliente --");
        System.out.println("¿Cómo deseas ubicar el cliente?");
        System.out.println("1) Por ID");
        System.out.println("2) Por celular");
        System.out.print("Opción: ");
        String modo = in.nextLine().trim();

        Cliente c = null;
        if ("1".equals(modo)) {
            Long id = leerLong("ID a actualizar: ");
            c = dao.buscarPorId(id);
        } else if ("2".equals(modo)) {
            String cel = leerNoVacio("Celular a actualizar: ");
            c = dao.buscarPorCelular(cel);
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        if (c == null) {
            System.out.println("No se encontró el cliente.");
            return;
        }

        System.out.println("Cliente actual: " + c);
        String nombres = leerOpcional("Nuevo nombres (Enter para mantener): ");
        String apellidos = leerOpcional("Nuevo apellidos (Enter para mantener): ");
        String celular = leerOpcional("Nuevo celular (Enter para mantener): ");

        if (!nombres.isBlank()) c.setNombres(nombres);
        if (!apellidos.isBlank()) c.setApellidos(apellidos);
        if (!celular.isBlank()) c.setCelular(celular);

        if (c.getNombres() == null || c.getNombres().isBlank()
                || c.getApellidos() == null || c.getApellidos().isBlank()
                || c.getCelular() == null || c.getCelular().isBlank()) {
            System.out.println("Error: nombres, apellidos y celular no pueden quedar vacíos.");
            return;
        }

        dao.actualizar(c);
        System.out.println("Cliente actualizado.");
    }

    private static void listar() {
        System.out.println("\n-- Lista de clientes --");
        List<Cliente> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("(sin registros)");
            return;
        }
        lista.forEach(System.out::println);
    }

    private static String leerNoVacio(String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim();
            if (!s.isBlank()) return s;
            System.out.println("No puede estar vacío.");
        }
    }

    private static String leerOpcional(String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
    }

    private static Long leerLong(String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine().trim();
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        }
    }
}