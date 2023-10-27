
package AccesoADatos;

import java.sql.Connection;


public class ProductoData {
    private Connection con = null;
     
     
     public ProductoData() {

 con = Conexion.getConexion();
 
 }
}
