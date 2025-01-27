package org.cebem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        SessionFactory sessionFactory=AuxSession.getSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();


//        Region nuevaRegion=new Region();
//        nuevaRegion.setId(5);
//        nuevaRegion.setRegionDescription("Región Vigo");
//        session.persist(nuevaRegion);
//        session.getTransaction().commit();

        Region nuevaRegion2=new Region();
        nuevaRegion2=session.get(Region.class,5);
        session.remove(nuevaRegion2);
        session.getTransaction().commit();


        //Consultar a partir de un id (Clave primaria)
        Region region1=session.get(Region.class, 2);
        //System.out.println(region1);

        //Listar a partir de una consulta
        List<Region> listRegiones=session.createNativeQuery("select * from Region",Region.class).list();
        for(Region r:listRegiones){
            //System.out.println(r);
        }
        //Listar a partir de una consulta HQL
        List<Region> listRegiones2=session.createQuery("from Region",Region.class).list();
        for(Region r:listRegiones2){
            System.out.println(r);
        }
        session.close();

    }

}
