/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package crcl.ui.misc;

import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.ui.client.CrclSwingClientJPanel;
import crcl.utils.outer.interfaces.PendantClientOuter;
import java.math.BigDecimal;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TransformSetupJFrame extends javax.swing.JFrame {
    
    /**
     * Set the value of parent
     *
     * @param parent new value of parent
     */
    public void setPendantClient(CrclSwingClientJPanel parent) {
        transformJPanel1.setPendantClient(parent);
    }

    /**
     * Creates new form TransformSetupJFrame
     */
    @SuppressWarnings("initialization")
    public TransformSetupJFrame() {
        initComponents();
        
    }

    static public PointType getPointFromTable(JTable table) {
        final TableModel model = table.getModel();
        PointType pt = new PointType();
        pt.setX(Double.parseDouble(model.getValueAt(0, 1).toString()));
        pt.setY(Double.parseDouble(model.getValueAt(1, 1).toString()));
        pt.setZ(Double.parseDouble(model.getValueAt(2, 1).toString()));
        return pt;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transformJPanel1 = new crcl.ui.misc.TransformJPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transformJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transformJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(858, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Get the value of origPoint1
     *
     * @return the value of origPoint1
     */
    public PointType getOrigPoint1() {
        return this.transformJPanel1.getOrigPoint1();
    }

    /**
     * Set the value of origPoint1
     *
     * @param origPoint1 new value of origPoint1
     */
    public void setOrigPoint1(PointType origPoint1) {
        this.transformJPanel1.setOrigPoint1(origPoint1);
    }
    
    private volatile boolean ignoreModelUpdates = false;
    
    
    public void updatePointTable(JTable table, PointType pt) {
        this.transformJPanel1.updatePointTable(table, pt);
    }
    
    
    /**
     * Get the value of transform
     *
     * @return the value of transform
     */
    public PoseType getTransform() {
        return this.transformJPanel1.getTransform();
    }

    /**
     * Set the value of transform
     *
     * @param transform new value of transform
     */
    public void setTransform(PoseType transform) {
        this.transformJPanel1.setTransform(transform);
    }
    
    
    /**
     * Get the value of origPoint2
     *
     * @return the value of origPoint2
     */
    public PointType getOrigPoint2() {
        return this.transformJPanel1.getOrigPoint2();
    }

    /**
     * Set the value of origPoint2
     *
     * @param origPoint2 new value of origPoint2
     */
    public void setOrigPoint2(PointType origPoint2) {
        this.transformJPanel1.setOrigPoint2(origPoint2);
    }
    
    
    /**
     * Get the value of newPoint1
     *
     * @return the value of newPoint1
     */
    public PointType getNewPoint1() {
        return this.transformJPanel1.getNewPoint1();
    }

    /**
     * Set the value of newPoint1
     *
     * @param newPoint1 new value of newPoint1
     */
    public void setNewPoint1(PointType newPoint1) {
        this.transformJPanel1.setNewPoint1(newPoint1);
    }
    
    
    /**
     * Get the value of newPoint2
     *
     * @return the value of newPoint2
     */
    public PointType getNewPoint2() {
        return this.transformJPanel1.getNewPoint2();
    }

    /**
     * Set the value of newPoint2
     *
     * @param newPoint2 new value of newPoint2
     */
    public void setNewPoint2(PointType newPoint2) {
        this.transformJPanel1.setNewPoint2(newPoint2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransformSetupJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransformSetupJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransformSetupJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransformSetupJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransformSetupJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private crcl.ui.misc.TransformJPanel transformJPanel1;
    // End of variables declaration//GEN-END:variables
}
