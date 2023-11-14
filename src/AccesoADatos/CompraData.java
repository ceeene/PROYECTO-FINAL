package AccesoADatos;

import java.sql.*;
import Entidades.Compra;
import Entidades.DetalleCompra;
import Entidades.Producto;
import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import java.sql.Date;
import java.sql.Statement;
import static java.time.temporal.TemporalQueries.localDate;

public class CompraData {

    private Connection con = null;
    private ProveedorData prov=new ProveedorData();
    private ProductoData prodd= new ProductoData();
    private CompraData cmd= new CompraData();
    private DetalleCompraData dcd= new DetalleCompraData();

    public CompraData() {

        con = Conexion.getConexion();

    }
    
  public void GuardarCompra (Compra comp){
          String sql= "INSERT INTO compra (idProveedor, fecha,estado) "
          + "VALUES (?,?)";
   try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,comp.getProveedor().getIdProveedor());
            ps.setLocalDate(2,comp.getCompra().Date.valueOf(Compra.getfecha()));
            ps.setEstado(3,comp.getActivo(true));
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
            
            compra.setIdCompra(rs.getInt(1));
            JOptionPane.showMessageDialog(null,"Compra Registrada");
            
        }
           ps.close(); 
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla compra");
            
        }   
   
   
 public List<Compra> BuscarCompra(int idCompra){
 
 String sql= "SELECT * "
         + "FROM  Compra "
         + "WHERE idCompra = ?";
 
 ArrayList<Compra> ListCompra= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,idCompra);
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Compra abc= new Compra();
                abc.setIdCompra(rs.getInt("idCompra"));
                Proveedor pro=prov.buscarProveedorPorId(rs.getInt("idProveedor"));
                abc.setProveedor(pro);
                abc.setFecha(rs.getDate("fecha").toLocalDate());
 
                
                ListCompra.add(abc);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
         }
        return ListCompra;
  

 public List<Producto> ListaProductoXFechadeCompra(Date fecha){
 
 String sql= "SELECT p.idProducto, nombreProducto, descripcion, precioActual,stock, estado "
         + "FROM Producto p JOIN Compra c ON (c.idCompra=d.idCompra) JOIN DetalleCompra d ON (d.idproducto=p.idProducto)"
         + "WHERE fecha = ?";
 
 ArrayList<Producto> productos= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setDate(1,fecha);
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
             Producto producto= new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioActual(rs.getInt("Precioactual"));
                producto.setStock(rs.getInt("stock"));
                producto.setActivo (true);
                
                productos.add(producto);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
         }
        return productos;
  
     
 public List<Compra> ListaComprasXProveedor (int idProveedor){
 
 String sql= "SELECT c.idCompra, idProveedor, fecha, estado FROM compra c, detallecompra d "
         + "WHERE c.idCompra=d.idCompra"
         + "AND c.idProveedor = ?";
 
 ArrayList<Compra> compras= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,idProveedor);
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Compra compr= new Compra ();
                compr.setIdCompra(rs.getInt("idCompra"));
                Proveedor pro=prov.buscarProveedorPorId(rs.getInt("idProveedor"));
                compr.setProveedor(pro);
                compr.setFecha(rs.getDate("fecha").toLocalDate());
                
                compras.add(compr);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
         }
        return compras;
 
      public List<Producto> ListaproductosXCompra (int idCompra){
 
         String sql= "SELECT  d.idDetalle, d.idProducto, d.idCompra,d.cantidad, d.precioCosto"
         + "  FROM detallecompra d,compra c"
         + " WHERE d.idCompra=c.idCompra"
         + " AND idCompra= ?";
 
 ArrayList<Producto> productos= new ArrayList<>();
 
 
         try{
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,idCompra);
             ResultSet rs= ps.executeQuery();
             while (rs.next()){
               DetalleCompra detal= new DetalleCompra();
               detal.setIdDetalle(rs.getInt("idDetalle"));
               Producto pr= prodd.BuscarProducto(rs.getInt("idProducto"));
               detal.setProducto(pr); 
               Compra compd =cmd.BuscarCompra(rs.getInt"idCompra");
               detal.setCompra(compd);
               detal.setCantidad(rs.getInt("cantidad"));
               detal.setPrecioCosto(rs.getDouble"precioCosto");       
               productos.add(detal);
                  
                JOptionPane.showMessageDialog(null,"exito");
                     
        }
               ps.close();
               
               } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error");
               }
           

    
    
    
    


