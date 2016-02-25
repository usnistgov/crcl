/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui;

import java.awt.Frame;
import java.util.logging.Logger;
import javax.swing.JDialog;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 */
public class MultiLineStringJPanel extends javax.swing.JPanel {

    /**
     * Creates new form MultiLineStringJPanel
     */
    public MultiLineStringJPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancel = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        FormListener formListener = new FormListener();

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(formListener);

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(formListener);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOK))
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                MultiLineStringJPanel.this.jButtonCancelActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonOK) {
                MultiLineStringJPanel.this.jButtonOKActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.cancelled = false;
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
       this.cancelled = true;
       this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed


    private JDialog dialog = null;
    private boolean cancelled = false;
    
    private static String editTextPrivate(JDialog _dialog, String init) {
        MultiLineStringJPanel panel = new MultiLineStringJPanel();
        panel.jTextArea1.setText(init);
        panel.jScrollPane1.getVerticalScrollBar().setValue(0);
        panel.jTextArea1.setCaretPosition(0);
        panel.dialog = _dialog;
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        if(panel.cancelled) {
            return null;
        }
        return panel.jTextArea1.getText();
    }
    
    public static String editText(String init, Frame _owner,
            String _title,
            boolean _modal) {
        JDialog dialog = new JDialog(_owner, _title, _modal);
        return editTextPrivate(dialog, init);
    }

    public static String editText(String init) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        return editTextPrivate(dialog, init);
    }
    
    
    private static boolean showTextPrivate(JDialog _dialog, String init) {
        MultiLineStringJPanel panel = new MultiLineStringJPanel();
        panel.jTextArea1.setText(init);
//        panel.jTextArea1.setEditable(false);
        panel.jScrollPane1.getVerticalScrollBar().setValue(0);
        panel.jTextArea1.setCaretPosition(0);
        panel.dialog = _dialog;
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        return !panel.cancelled;
    }
    
    public static boolean showText(String init, Frame _owner,
            String _title,
            boolean _modal) {
        JDialog dialog = new JDialog(_owner, _title, _modal);
        return showTextPrivate(dialog, init);
    }

    public static boolean showText(String init) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        return showTextPrivate(dialog, init);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(MultiLineStringJPanel.class.getName());
}
