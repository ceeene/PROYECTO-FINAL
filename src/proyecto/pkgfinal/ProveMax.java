package proyecto.pkgfinal;

import AccesoADatos.CompraData;
import AccesoADatos.Conexion;
import AccesoADatos.DetalleCompraData;
import Entidades.Compra;
import Entidades.DetalleCompra;
import java.sql.Connection; 
import java.time.LocalDate; 
import java.util.ArrayList; 
import java.util.List; 

public class ProveMax {

    public static void main(String[] args) {
        
         Connection con= Conexion.getConexion();
        
        

//        CompraData compData = new CompraData();
//        Compra comp = compData.obtenerCompraPorId(1);
//        DetalleCompraData detCompra = new DetalleCompraData();
//        for(DetalleCompra item:detCompra.buscarDetallePorCompra(comp)){
//            System.out.println(item.getIdDetalle());
//            System.out.println(item.getCantidad());
//            System.out.println(item.getPrecioCosto());
//            System.out.println(item.getProducto().getNombreProducto());
//            
//        }
        
        
        

    }

}
