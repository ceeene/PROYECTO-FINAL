package AccesoADatos;

import Entidades.Compra;
import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CompraData {

    private Connection con = null;

    public CompraData() {

        con = Conexion.getConexion();

    }

    public List<Compra> ObtenerComprasPorProveedor(Proveedor proveedor) {
        List<Compra> compras = new ArrayList<>();

        try {
            String sql = "SELECT idCompra, fecha FROM compra WHERE idProveedor=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, proveedor.getIdProveedor());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Compra compra = new Compra();
               compra.setIdCompra(rs.getInt("idCompra"));
               compra.setProveedor(proveedor);
               compra.setFecha(rs.getDate("fecha").toLocalDate());
               compras.add(compra); 
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla compras");
        }
        return compras; 

    }
    
    public Compra obtenerCompraPorId(int id ){
        
        Compra compra= null; 
        
        try {
            String sql= "SELECT * FROM compra WHERE idCompra=?"; 
            PreparedStatement ps= con.prepareStatement(sql); 
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery(); 
            
           while (rs.next()) {
              compra = new Compra();
               compra.setIdCompra(id);
               Proveedor prov = new Proveedor(); 
               prov.setIdProveedor(rs.getInt("idProveedor"));
               compra.setProveedor(prov);
               compra.setFecha(rs.getDate("fecha").toLocalDate());
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ala acceder a la tabla compra"+ex.getMessage());
        }
        return compra; 
    }
    
    
    
    
    

}
