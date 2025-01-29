package org.cebem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FormCategories {
    private JPanel Formulario;
    private JTextField textField1;
    private JTable table1;
    private JButton button1;
    private JScrollPane ScrollTabla;
    private JLabel EtiquetaId;
    private JLabel EtiquetaDescripcion;
    private JButton button2;
    private JButton button3;
    private JTextField txtFieldDescripcion;

    public FormCategories() {
        cargarDatosTabla();
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow != -1) {
                    String regionId=table1.getValueAt(selectedRow, 0).toString();
                    String descripcion = table1.getValueAt(selectedRow, 1).toString();
                    txtFieldDescripcion.setText(descripcion);
                    textField1.setText(regionId);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regionId = textField1.getText();
                String nuevaDescripcion = txtFieldDescripcion.getText();

                if (regionId.isEmpty() || nuevaDescripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una categoría y asegúrate de que todos los campos estén completos.");
                    return;
                }

                modificarCategoria(Integer.parseInt(regionId), nuevaDescripcion);
                cargarDatosTabla();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaDescripcion = txtFieldDescripcion.getText();

                if (nuevaDescripcion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce una descripción para la nueva región.");
                    //return;
                }

                agregarRegion(nuevaDescripcion);
                cargarDatosTabla();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regionId = textField1.getText();

                if (regionId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una región para eliminar.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar esta región?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    eliminarRegion(Integer.parseInt(regionId));
                    textField1.setText("");
                    cargarDatosTabla();
                }
            }
        });
    }

    private void cargarDatosTabla() {
        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Region> listRegiones = session.createQuery("from Region", Region.class).list();

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

    private void modificarCategoria(int regionId, String nuevaDescripcion) {
        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Region region = session.get(Region.class, regionId);
            if (region != null) {
                region.setRegionDescription(nuevaDescripcion); // Actualiza la descripción
                session.update(region); // Actualiza en la base de datos
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "La categoría se ha modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La categoría no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al modificar la categoría.");
        }
    }
    private void agregarRegion(String descripcion) {
        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Integer maxId = (Integer) session.createQuery("SELECT MAX(r.id) FROM Region r").uniqueResult();
            int newId = (maxId == null) ? 1 : maxId + 1;

            // Crear una nueva instancia de Region
            Region nuevaRegion = new Region();
            nuevaRegion.setId(newId); // Asignar manualmente el ID
            nuevaRegion.setRegionDescription(descripcion); // Establecer la descripción

            session.persist(nuevaRegion); // Guardar en la base de datos
            session.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "La nueva región se ha añadido correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al añadir la nueva región.");
        }
    }
    private void eliminarRegion(int regionId) {
        SessionFactory sessionFactory = AuxSession.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Obtener la región a eliminar
            Region region = session.get(Region.class, regionId);
            if (region != null) {
                session.delete(region);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "La región se ha eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La región no existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la región.");
        }
    }
}
