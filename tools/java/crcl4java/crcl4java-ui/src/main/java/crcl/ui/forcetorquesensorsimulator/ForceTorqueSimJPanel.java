/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.ui.forcetorquesensorsimulator;

import com.sun.istack.logging.Logger;
import crcl.base.CRCLStatusType;
import crcl.base.ConfigureStatusReportType;
import crcl.base.ForceTorqueSensorStatusType;
import crcl.base.SensorStatusesType;
import crcl.utils.CRCLSocket;
import crcl.utils.server.CRCLServerClientState;
import crcl.utils.server.CRCLServerSocket;
import crcl.utils.server.CRCLServerSocketEvent;
import crcl.utils.server.CRCLServerSocketEventListener;
import crcl.utils.server.CRCLServerSocketStateGenerator;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class ForceTorqueSimJPanel extends javax.swing.JPanel {

    /**
     * Creates new form ForceTorqueSimJPanel
     */
    @SuppressWarnings("initialization")
    public ForceTorqueSimJPanel() {
        status = new CRCLStatusType();
        status.setSensorStatuses(new SensorStatusesType());
        sensorStatus = new ForceTorqueSensorStatusType();
        status.getSensorStatuses().getForceTorqueSensorStatus().add(sensorStatus);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelCommunications = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBoxStartServer = new javax.swing.JCheckBox();
        jPanelOffsets = new javax.swing.JPanel();
        valueJPanelFx = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();
        valueJPanelFy = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();
        valueJPanelFz = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();
        valueJPanelTx = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();
        valueJPanelTy = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();
        valueJPanelTz = new crcl.ui.forcetorquesensorsimulator.ValueJPanel();

        jLabel1.setText("Port: ");

        jTextField1.setText("8888");

        jCheckBoxStartServer.setText("Start Server");
        jCheckBoxStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStartServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCommunicationsLayout = new javax.swing.GroupLayout(jPanelCommunications);
        jPanelCommunications.setLayout(jPanelCommunicationsLayout);
        jPanelCommunicationsLayout.setHorizontalGroup(
            jPanelCommunicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommunicationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommunicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCommunicationsLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE))
                    .addGroup(jPanelCommunicationsLayout.createSequentialGroup()
                        .addComponent(jCheckBoxStartServer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCommunicationsLayout.setVerticalGroup(
            jPanelCommunicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommunicationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCommunicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxStartServer)
                .addContainerGap(522, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Communications", jPanelCommunications);

        valueJPanelFx.setName("Fx"); // NOI18N

        valueJPanelFy.setName("Fy"); // NOI18N

        valueJPanelFz.setName("Fz"); // NOI18N

        valueJPanelTx.setName("Tx"); // NOI18N

        valueJPanelTy.setName("Ty"); // NOI18N

        valueJPanelTz.setName("Tz"); // NOI18N

        javax.swing.GroupLayout jPanelOffsetsLayout = new javax.swing.GroupLayout(jPanelOffsets);
        jPanelOffsets.setLayout(jPanelOffsetsLayout);
        jPanelOffsetsLayout.setHorizontalGroup(
            jPanelOffsetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOffsetsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelOffsetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valueJPanelFx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueJPanelTz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueJPanelFy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueJPanelTx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueJPanelFz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueJPanelTy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOffsetsLayout.setVerticalGroup(
            jPanelOffsetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOffsetsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valueJPanelFx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueJPanelFy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueJPanelFz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueJPanelTx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueJPanelTy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueJPanelTz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Offset: ", jPanelOffsets);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStartServerActionPerformed
        boolean doStart = jCheckBoxStartServer.isSelected();
        try {
            if (doStart) {
                startServer();
            } else if (null != crclServerSocket) {
                crclServerSocket.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(ForceTorqueSimJPanel.class).log(Level.SEVERE, "connect=" + doStart, ex);
        }
    }//GEN-LAST:event_jCheckBoxStartServerActionPerformed

    public void startServer() {
        int port = Integer.parseInt(jTextField1.getText());
        try {
            startServer(port);
        } catch (Exception ex) {
            Logger.getLogger(ForceTorqueSimJPanel.class).log(Level.SEVERE, "port=" + port, ex);
        }
    }

    private void startServer(int port) throws IOException {
        if(!jCheckBoxStartServer.isSelected()) {
            jCheckBoxStartServer.setSelected(true);
        }
        crclServerSocket = new CRCLServerSocket<>(port, FORCE_TORQUE_SIM_STATE_GENERATOR);
        crclServerSocket.addListener(crclSocketEventListener);
        crclServerSocket.setServerSideStatus(status);
        crclServerSocket.setAutomaticallySendServerSideStatus(true);
        crclServerSocket.setAutomaticallyConvertUnits(true);
        crclServerSocket.setUpdateStatusRunnable(this::updateSensorStatus);
        crclServerSocket.start();
    }

    public static class ForceTorqueSimClientState extends CRCLServerClientState {

        public ForceTorqueSimClientState(CRCLSocket cs) {
            super(cs);
            final ConfigureStatusReportType configureStatusReportType = new ConfigureStatusReportType();
            configureStatusReportType.setReportGripperStatus(false);
            configureStatusReportType.setReportPoseStatus(false);
            configureStatusReportType.setReportSettingsStatus(false);
            configureStatusReportType.setReportJointStatuses(false);
            configureStatusReportType.setReportSensorsStatus(true);
            super.filterSettings.setConfigureStatusReport(configureStatusReportType);
        }
        int i;
    }
    private final CRCLStatusType status;
    private final ForceTorqueSensorStatusType sensorStatus;

    public static final CRCLServerSocketStateGenerator<ForceTorqueSimClientState> FORCE_TORQUE_SIM_STATE_GENERATOR
            = ForceTorqueSimClientState::new;

    @SuppressWarnings("initialization")
    private final CRCLServerSocketEventListener<ForceTorqueSimClientState> crclSocketEventListener
            = this::handleCrclServerSocketEvent;

    private CRCLServerSocket<ForceTorqueSimClientState> crclServerSocket;

    private void handleCrclServerSocketEvent(CRCLServerSocketEvent<ForceTorqueSimClientState> evt) {

    }

    private void updateSensorStatus() {
        sensorStatus.setFx(valueJPanelFx.getValue());
        sensorStatus.setFy(valueJPanelFy.getValue());
        sensorStatus.setFz(valueJPanelFz.getValue());
        sensorStatus.setTx(valueJPanelTx.getValue());
        sensorStatus.setTy(valueJPanelTy.getValue());
        sensorStatus.setTz(valueJPanelTz.getValue());
        sensorStatus.setSensorID("ForceTorqueSim");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxStartServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelCommunications;
    private javax.swing.JPanel jPanelOffsets;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelFx;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelFy;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelFz;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelTx;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelTy;
    private crcl.ui.forcetorquesensorsimulator.ValueJPanel valueJPanelTz;
    // End of variables declaration//GEN-END:variables
}