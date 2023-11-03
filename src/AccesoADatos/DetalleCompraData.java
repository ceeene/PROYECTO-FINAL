package AccesoADatos;

import Entidades.Compra;
import Entidades.DetalleCompra;
import Entidades.Producto;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DetalleCompraData {

    private Connection con = null;
    private CompraData cp = new CompraData();
    private ProductoData pd = new ProductoData();

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

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de detallesCompras");
        }

    }

//    public List<DetalleCompra> obtenerDetallesDeCompra(){
//        List<DetalleCompra> obtenerDetalles = new ArrayList<>();
//        String sql = "SELECT * FROM detallecompra ";
//
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                DetalleCompra detalle = new DetalleCompra();
//                detalle.setIdDetalle(rs.getInt("idDetalle"));
//                detalle.setCantidad(rs.getInt("cantidad"));
//                detalle.setPrecioCosto(rs.getDouble("preciocosto"));
//                // Obtener y configurar la referencia a la Compra y el Producto aquí
//                obtenerDetalles.add(detalle);
//            }
//        } catch (SQLException ex) {
//            // Manejar excepciones
//        }
//        return obtenerDetalles;
//    }

}
