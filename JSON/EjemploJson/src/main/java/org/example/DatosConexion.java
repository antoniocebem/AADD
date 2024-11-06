package org.example;

public class DatosConexion {
    private String dirServer;
    private String baseDatos;
    private int puerto;
    private String usuario;
    private String clave;


    public String getDirServer() {
        return dirServer;
    }

    public void setDirServer(String dirServer) {
        this.dirServer = dirServer;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "DatosConexion{" +
                "dirServer='" + dirServer + '\'' +
                ", baseDatos='" + baseDatos + '\'' +
                ", puerto=" + puerto +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
