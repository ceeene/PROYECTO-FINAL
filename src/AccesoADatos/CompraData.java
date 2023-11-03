
package AccesoADatos;

import Entidades.Producto;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;


public class CompraData {
    private Connection con = null;
     
     
     public CompraData() {

 con = Conexion.getConexion();
 
 }
     public List<Producto> ListaProductoXFechadeCompra (localDate fecha){
 
 String sql= "SELECT * FROM producto WHERE fecha = ?";
 
 ArrayList<Producto> productos= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
         
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Producto producto= new Producto ();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setnombreProducto(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setprecioActual(rs.getInt("idPrecioactual"));
                 producto.setStock(rs.getInt("stock"));
                producto.setActivo (true);
                
                productos.add(producto);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error");
         }
        return productos;
 }
     
     public List<Compra> ListaComprasXProveedor (Proveedor proveedor){
 
 String sql= "SELECT * FROM compra WHERE proveedor = ?";
 
 ArrayList<Compra> compras= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
         
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Compra compras= new Compra ();
                compra.setIdCompra(rs.getInt("idCompra"));
                compra.proveedor(rs.getString("proveedor"));
                /*falta linea to local date*/
                compra.setActivo (true);
                
                compras.add(compra);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error");
         }
        return compras;
 }
     
}
     

