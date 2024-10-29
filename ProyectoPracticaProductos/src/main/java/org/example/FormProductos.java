package org.example;


import javax.swing.*;
import java.sql.*;

public class FormProductos {
    private JTable table1;
    private JPanel panel1;
    private JScrollPane FormProductos;
    private Connection conn;

    public FormProductos(Connection conn) {
        this.conn=conn;
        JFrame frame = new JFrame("FormProductos");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
