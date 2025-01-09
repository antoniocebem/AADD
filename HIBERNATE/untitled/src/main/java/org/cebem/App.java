package org.cebem;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
// Construye el SessionFactory leyendo hibernate.cfg.xml de /resources
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // Abre la sesión
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Consulta HQL para listar todos los productos
            // "Producto" es el nombre de la clase (@Entity)
            List<Product> productos = session.createQuery("FROM Product", Product.class).list();

            // Muestra los productos en consola
            for (Product p : productos) {
                System.out.println("ID: " + p.getId() + ", Nombre: " + p.getProductName());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra el SessionFactory
            sessionFactory.close();
        }

    }
}
