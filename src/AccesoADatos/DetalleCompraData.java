
package AccesoADatos;

import Entidades.DetalleCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class DetalleCompraData {
    private Connection con = null;
     
     
     public DetalleCompraData() {

 con = Conexion.getConexion();
 
 }
     
         public void GuardarDetalleDeCompras(DetalleCompra detallecompra) {
        String sql = "INSERT INTO detallecompra(cantidad, preciocosto, compra, producto)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detallecompra.getCantidad());
            ps.setDouble(2, detallecompra.getPrecioCosto());
            ps.setInt(3, detallecompra.getCompra().getIdCompra());
            ps.setInt(4, detallecompra.getProducto().getIdProducto());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                detallecompra.setIdDetalle(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Detalle de compra Agregado");
            }
            ps.close();

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de detallesCompras");
        }
    
}
