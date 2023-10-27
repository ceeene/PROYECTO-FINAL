
package AccesoADatos;

import java.sql.Connection;


public class ProveedorData {
     private Connection con = null;
     
     
     public ProveedorData() {

 con = Conexion.getConexion();
 
 }
     
     
}
