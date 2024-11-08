package org.example;

import com.google.gson.annotations.SerializedName;

public class DatosConexionBD{

	@SerializedName("puerto")
	private int puerto;

	@SerializedName("clave")
	private String clave;

	@SerializedName("dirServer")
	private String dirServer;

	@SerializedName("usuario")
	private String usuario;

	@SerializedName("baseDatos")
	private String baseDatos;

	public int getPuerto(){
		return puerto;
	}

	public String getClave(){
		return clave;
	}

	public String getDirServer(){
		return dirServer;
	}

	public String getUsuario(){
		return usuario;
	}

	public String getBaseDatos(){
		return baseDatos;
	}
}