/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import AccesoADatos.ProveedorData;
import Entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Celia
 */
public class ListarProveedores extends javax.swing.JInternalFrame {
    private DefaultTableModel modelo; 
    private List<Proveedor> listaPA;
    private ProveedorData pData;
  
    
    
    public ListarProveedores() {
        initComponents();
        armarCabeceraTabla();
        pData=new ProveedorData();
        listaPA = pData.listaProveedoresActivos();
        modelo=new DefaultTableModel();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JRBActivos = new javax.swing.JRadioButton();
        JRBInactivos = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTProveedores = new javax.swing.JTable();

        setTitle("Listar Proveedores");

        jLabel1.setText("Control de Proveedores activos e inactivos");

        JRBActivos.setText("Activos");
        JRBActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRBActivosActionPerformed(evt);
            }
        });

        JRBInactivos.setText("Inactivos");

        JTProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTProveedores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(JRBActivos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JRBInactivos)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRBActivos)
                    .addComponent(JRBInactivos))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargaDatosActivos(){
    listaPA = pData.listaProveedoresActivos();
    for (Proveedor p: listaPA){
        modelo.addRow(new Object[] {p.getIdProveedor(), p.getRazonSocial(), p.getDomicilio(), p.getTelefono(), p.isActivo()});
    }
}
    
    
    
    private void JRBActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRBActivosActionPerformed
        borrarFilaTabla();
        JRBInactivos.setSelected(false);
        cargaDatosActivos();
        JRBActivos.setEnabled(true);
        JRBInactivos.setEnabled(false);
        
        
    }//GEN-LAST:event_JRBActivosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton JRBActivos;
    private javax.swing.JRadioButton JRBInactivos;
    private javax.swing.JTable JTProveedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

private void armarCabeceraTabla(){
        ArrayList<Object> filaCabecera = new ArrayList<>(); 
        filaCabecera.add("ID"); 
        filaCabecera.add("Razon Social"); 
        filaCabecera.add("Domicilio"); 
        filaCabecera.add("Telefono");
        filaCabecera.add("Estado");
        for(Object it: filaCabecera){
            modelo.addColumn(it); 
        }
        JTProveedores.setModel(modelo);
    }

private void borrarFilaTabla(){
    int indice = modelo.getRowCount()-1;
    for (int i = indice; i>=0;i--){
        modelo.removeRow(i);
    }
}

}
