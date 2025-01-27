package org.cebem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FormCategories {
    private JPanel Formulario;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JButton button3;
    private JScrollPane ScrollTabla;

    public FormCategories() {
        cargarDatosTabla();
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow != -1) {
                    String regionId=table1.getValueAt(selectedRow, 0).toString();
                    String descripcion = table1.getValueAt(selectedRow, 1).toString();
                    System.out.println("RegionId: " + regionId);
                    System.out.println("Descripción: " + descripcion);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
        });
    }

    private void cargarDatosTabla() {
        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Region> listRegiones = session.createNativeQuery("select * from Region", Region.class).list();

            String[] columnas = {"RegionId", "Descripción"};
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

            for (Region region : listRegiones) {
                Object[] fila = {
                        region.getId(),
                        region.getRegionDescription()
                };
                modelo.addRow(fila);
            }


            table1.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            JFrame frame = new JFrame("FormCategories");
            frame.setContentPane(new FormCategories().Formulario);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar la ventana
            frame.setVisible(true);

    }
}
