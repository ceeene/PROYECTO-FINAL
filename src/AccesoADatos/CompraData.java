package AccesoADatos;

import Entidades.Compra;
import Entidades.Producto;
import Entidades.Proveedor;
import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;


public class CompraData {
    private Connection con = null;
    private ProveedorData prov=new ProveedorData();
   private ProductoData prodd= new ProductoData();
   private CompraData cmd= new CompraData();
    
 
  public CompraData() {

 con = Conexion.getConexion();
 
 }
  public void GuardarCompra (Compra Comp)
          String sql= "INSERT INTO compra (idProveedor, fechacompra) "
          + "VALUES (?,?)"
   try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,comp.getProveedor().getidProveedor();
            ps.setDate(2,comp.getCompra .Date.valueOf(Compra.getfecha()));
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
            
            Compra.setIdCompra(rs.getInt(1));
            JOptionPane.showMessageDialog(null,"Compra Registrada");
            
        }
           ps.close(); 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla compra");
            
            
            /*DEBO CREAR UNA VARIABLE DE COMPRA Y PASARLE POR PARAMETRO UN PROVEEDOR Y UNA FECHA*/
  
}
  
  
  
  
     public List<Producto> ListaProductoXFechadeCompra (Date fecha){
 
 String sql= "SELECT p.idProducto, nombreProducto, descripcion, precioActual,stock, estado "
         + "FROM Producto p JOIN Compra c ON (c.idCompra=d.idCompra) JOIN DetalleCompra d ON (d.idproducto=p.idProducto)"
         + "WHERE Fecha = ?";
 
 ArrayList<Producto> productos= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setDate(1,fecha)
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Producto producto= new Producto ();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setnombreProducto(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setprecioActual(rs.getInt("Precioactual"));
                producto.setStock(rs.getInt("stock"));
                producto.setActivo (true);
                
                productos.add(producto);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error");
         }
        return productos;
        
 }
     
     /*PRUEBA
     CompraData prod= new prod()
     for (Producto producto:prod.ListaProductoXFechadeCompra);
     
     sout (producto.getIdproducto);
     sout (producto.getNombre);
     sout (producto.getStock);
     */
     
     public List<Compra> ListaComprasXProveedor (int idProveedor){
 
 String sql= "SELECT idCompra, idProveedor, fecha FROM compra c, DetalleCompra d "
         + "WHERE c.idCompra=d.idCompra"
         + "AND c.idProveedor = ?";
 
 ArrayList<Compra> compras= new ArrayList<>();
 
 
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,idProveedor)
         
             
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
              Compra compr= new Compra ();
                Compra.setIdCompra(rs.getInt("idCompra"));
                Proveedor pro=prov.buscarProveedorPorId(rs.getInt("idProveedor"))
                Compra.setProveedor(pro);
                Compra.setfecha(rs.getDate("fecha").toLocalDate());
                
                compras.add(compr);
 }
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error");
         }
        return compras;
 }
     public List<Producto> ListaproductosXCompra (int idCompra){
 
 String sql= "SELECT  d.idDetalle, d.idProducto, d.idCompra,d.cantidad, d.precioCosto"
         + "  FROM detallecompra d,compra c"
         + " WHERE d.idCompra=c.idCompra"
         + "AND idCompra= ?"
 
 ArrayList<Producto> productos= new ArrayList<>();
 
 
         try{
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,idCompra);
             ResultSet rs= ps.executeQuery();
             while (rs.next()){
                detallecompra detal= new detallecompra();
                detal.setidDetalle(rs.getInt("idDetalle"));
                Producto pr= prodd.BuscarProducto(rs.getInt("idProducto"))
                detal.setProducto(pr)       
               Compra compd =cmd.BuscarCompra(rs.getInt"idCompra")
               detal.setCompra(compd)
               detal.setcantidad(rs.getInt("cantidad"))   
                detal.setprecioCosto(rs.getdouble"precioCosto")       
              
                
               } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "error");
         }
                productos.add(detal);
 }
     ps.close();
/*
}
