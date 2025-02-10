package org.cebem;
import org.cebem.session.*;
import org.cebem.modelo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Scanner;

public class GestionProductos {

    public static void main(String[] args) {

        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre del producto:");
        String productName=scanner.nextLine();

        System.out.println("Introduce el ID de la categoría:");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce el ID del proveedor:");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce el precio del producto:");
        double UnitPrice=scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Introduce el stock disponible:");
        product.setUnitsInStock(scanner.nextShort());
        scanner.nextLine();


        int nuevoProducto=insertProduct(sessionFactory,productName,categoryId,supplierId,"",UnitPrice, (short) 0, (short) 0,(short)0,false);
        modificarProduct(sessionFactory,nuevoProducto);

        borrarProduct(sessionFactory,122);
    }

    public static int insertProduct(SessionFactory sessionFactory, String productName,int categoryID,
                                     int supplierId,
                                     String quantityPerUnit, double unitPrice, short unitsInStock,
                                     short unitsOnOrder, short reorderLevel, boolean discontinued) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Category category = session.get(Category.class, categoryID);
            Supplier supplier = session.get(Supplier.class, supplierId);

            if (category == null || supplier == null) {
                throw new Exception("Categoría o proveedor no encontrados");
            }

            Product product = new Product();
            product.setProductName(productName);
            product.setCategoryID(category);
            product.setSupplierID(supplier);
            product.setQuantityPerUnit(quantityPerUnit);
            product.setUnitPrice(unitPrice);
            product.setUnitsInStock(unitsInStock);
            product.setUnitsOnOrder(unitsOnOrder);
            product.setReorderLevel(reorderLevel);
            product.setDiscontinued(discontinued);

            session.persist(product);
            transaction.commit();

            System.out.println("Producto insertado con éxito: " + product.getId());
            System.out.println(session.get(Product.class, product.getId()));
            return product.getId();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                return -1;
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return -1;
    }

    public static void modificarProduct(SessionFactory sessionFactory,int idProducto){
        Scanner scanner=new Scanner(System.in);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, idProducto);

        System.out.println("Nombre antiguo: "+product.getProductName());
        System.out.println("Introduce el nuevo nombre del producto:");
        String productName=scanner.nextLine();

        System.out.println("Categoría del producto :"+product.getCategoryID());
        System.out.println("Introduce el ID de la nueva categoría:");
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Id actual del proveedor :"+product.getSupplierID());
        System.out.println("Introduce el ID del proveedor:");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Precio actual del producto:"+product.getUnitPrice());
        System.out.println("Introduce el nuevo precio del producto:");
        double UnitPrice=scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Stock actual del producto:"+product.getUnitsInStock());
        System.out.println("Introduce el nuevo stock disponible:");
        product.setUnitsInStock(scanner.nextShort());
        scanner.nextLine();

        product.setProductName(productName);
        product.setSupplierID(session.get(Supplier.class, supplierId));
        product.setCategoryID(session.get(Category.class, categoryId));
        product.setUnitPrice(UnitPrice);
        product.setUnitsInStock(product.getUnitsInStock());

        session.merge(product);
        session.getTransaction().commit();
        System.out.println("Producto modificado satisfactoriamente");
    }
    public static void borrarProduct(SessionFactory sessionFactory,int idProducto){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.get(Product.class, idProducto));
        session.getTransaction().commit();
        System.out.println("Producto borrado satisfactoriamente");
    }
}
