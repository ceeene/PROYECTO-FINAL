
package AccesoADatos;

import Entidades.Producto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class ProductoData {
    private Connection con = null;
     
     
     public ProductoData() {

 con = Conexion.getConexion();
 
 
 
 }
     public void guardarProducto(Producto producto){
     
         String sql= "INSERT INTO producto(nombreProducto, descripcion, precioActual,stock,estado) VALUES(?, ?, ?, ?, ?)";
         
         try{
              PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
              ps.setString(1, producto.getNombreProducto());
              ps.setString(2, producto.getDescripcion());
              ps.setDouble(3, producto.getPrecioActual());
              ps.setInt(4, producto.getStock());
              ps.setBoolean(5, producto.isActivo());
              ps.executeUpdate(); 
            
              ResultSet rs=ps.getGeneratedKeys(); 
              
              if(rs.next()){
                producto.setIdProducto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                
            }
            ps.close();
         }catch(SQLException ex){
             
             JOptionPane.showMessageDialog(null,"Error al acceder a la tabla producto");
            System.out.println(ex.getMessage());
            System.out.println("Codigo de error "+ex.getErrorCode());
         }
 }
     
     
     public void modificarProducto(Producto producto){
          String sql="UPDATE producto SET nombreProducto=?, descripcion=?, precioActual=?, stock=?, estado=?"
            + "WHERE idProducto=? " ;
          
           try {
            PreparedStatement ps=con.prepareStatement(sql);
              ps.setString(1, producto.getNombreProducto());
              ps.setString(2, producto.getDescripcion());
              ps.setDouble(3, producto.getPrecioActual());
              ps.setInt(4, producto.getStock());
              ps.setBoolean(5, producto.isActivo());
            int exito=ps.executeUpdate();
            if(exito==1){
                
                JOptionPane.showMessageDialog(null,"Producto modificado");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder al Producto");
        }
         
         
     }
     
     
     
     public void eliminarProducto(int id){
         String sql= "UPDATE producto SET estado= 0 WHERE idProducto= ? ";
         try{ 
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, id);
             int exito=ps.executeUpdate();
             
             if(exito==1){
                 JOptionPane.showMessageDialog(null, "Producto eliminado");
             }
         }catch (SQLException ex){
         JOptionPane.showMessageDialog(null,"Error al acceder al Producto");
     }
     }
     
     
     
}
