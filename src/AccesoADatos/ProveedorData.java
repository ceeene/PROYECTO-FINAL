
package AccesoADatos;

import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProveedorData {
     private Connection con = null;
     Proveedor proveedor=null;
     
     
     
     public ProveedorData() {

 con = Conexion.getConexion();
 
 }
  public void guardarProveedor(Proveedor proveedor){
     String sql="INSERT INTO proveedor(razonSocial, domicilio, telefono, estado)"
             + "VALUES(?, ?, ?,?)"; 
     
        try { 
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getDomicilio());
            ps.setInt(3, proveedor.getTelefono());
            ps.setBoolean(4, proveedor.isActivo());
            ps.executeUpdate(); 
            
            ResultSet rs=ps.getGeneratedKeys(); 
            if(rs.next()){
                proveedor.setIdProveedor(rs.getInt(1)); 
                JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente");
                
            }
            ps.close();
            //limpiarCampos();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla proveedor");
            System.out.println(ex.getMessage());
            System.out.println("Codigo de error "+ex.getErrorCode());
        } 
        
        
 } 
  
   public void eliminarProveedor(int id) {
    String sql= "UPDATE alumno SET estado= 0 WHERE idProveedor= ? ";
         try{ 
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, id);
             int exito=ps.executeUpdate();
             
             if(exito==1){
                 JOptionPane.showMessageDialog(null, "Proveedor eliminado");
             }
         }catch (SQLException ex){
         JOptionPane.showMessageDialog(null,"Error al acceder a la tabla de proveedores");
     }
 } 
   
    public Proveedor buscarProveedor(int id){
    String sql="SELECT razonSocial, domicilio, telefono FROM proveedor WHERE idProveedor = ? AND estado=1"; 
    PreparedStatement ps=null;
    try{
    ps=con.prepareStatement(sql);
    ps.setInt(1,id);
    ResultSet rs=ps.executeQuery();
    if(rs.next()){
        
        proveedor=new Proveedor();
        proveedor.setIdProveedor(id);
        proveedor.setRazonSocial(rs.getString("razonSocial"));
        proveedor.setDomicilio(rs.getString("domicilio"));
        proveedor.setTelefono(rs.getInt("telefono"));
        proveedor.setActivo(true);
    
    }else{
        
       JOptionPane.showMessageDialog(null,"Proveedor no encontrado, verifique id"); 
    }
    ps.close();
    
    }catch (SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al acceder a la tabla de proveedores");
    }
return proveedor;
    }
       
     
public void modificarProveedor (Proveedor proveedor){
    String sql="UPDATE proveedor SET razonSocial= ?, domicilio= ?, telefono= ?"
        + "WHERE  idProveedor=? " ;
     try {
    PreparedStatement ps= con.prepareStatement(sql);
    ps.setString(1, proveedor.getRazonSocial());
    ps.setString(2, proveedor.getDomicilio());
    ps.setInt(3, proveedor.getTelefono());
    int exito=ps.executeUpdate();
    if (exito==1) {
 
 JOptionPane.showMessageDialog(null, "Proveedor modificado correctamente");
} 

}catch (SQLException ex) {
ex.printStackTrace();
JOptionPane.showMessageDialog(null,"Error al acceder a los datos de proveedores");
} 
 }
 
    
    public Proveedor buscarProveedorPorRazonSocial(String razonSocial){
    String sql="SELECT idProveedor, domicilio, telefono FROM proveedor WHERE razonSocial = ? AND estado=1"; 
    PreparedStatement ps=null;
    try{
    ps=con.prepareStatement(sql);
    ps.setString(1,razonSocial);
    ResultSet rs=ps.executeQuery();
    if(rs.next()){
        
        proveedor=new Proveedor();
        proveedor.setRazonSocial(razonSocial);
        proveedor.setDomicilio(rs.getString("domicilio"));
        proveedor.setTelefono(rs.getInt("telefono"));
        proveedor.setIdProveedor(rs.getInt("idProveedor"));
        proveedor.setActivo(true);
    
    }else{
        
       JOptionPane.showMessageDialog(null,"Proveedor no encontrado, verifique Razon Social"); 
    }
    ps.close();
    
    }catch (SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al acceder a la tabla de proveedores");
    }
return proveedor;
    }
       
    /* public List<Proveedor> ListaProveedores (){
 
 String sql= "SELECT idProveedor, razonSocial, direccion, telefono, estado FROM proveedor WHERE estado = 1";
 
 ArrayList<Proveedor> proveedores= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
         
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Proveedor proveedor= new Proveedor ();
                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setRazonSocial(rs.getString("razonSocial"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setTelefono(rs.getInt("telefono"));
                proveedor.setActivo (true);
                
                proveedor.add(proveedor);
 }
} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
         }
        return proveedor;
 }*/
    
}

