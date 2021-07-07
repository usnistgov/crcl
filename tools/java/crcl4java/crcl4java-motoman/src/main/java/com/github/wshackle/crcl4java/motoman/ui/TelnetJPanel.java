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
package com.github.wshackle.crcl4java.motoman.ui;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TelnetJPanel extends javax.swing.JPanel {

    private @Nullable MotomanTelnetClient tc = null;

    /**
     * Creates new form TelnetJPanel
     */
    @SuppressWarnings({"nullness","initialization"})
    public TelnetJPanel() {
        initComponents();
        Font font = this.jTextArea1.getFont();
//        System.out.println("font = " + font);
        this.jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 15));
        this.jTextField1.setFont(new Font("Monospaced", Font.PLAIN, 15));
        font = this.jTextArea1.getFont();
//        System.out.println("font = " + font);
        font = this.jTextField1.getFont();
//        System.out.println("font = " + font);
    }

    private String host;
    private int port;
    public void connect() throws IOException {
        if (null != tc) {
            tc.disconnect();
        }
        host = jTextFieldHostName.getText();
        if(host.trim().length() < 1) {
            jTextFieldHostName.setText(MotomanTelnetClient.DEFAULT_MOTOMAN_HOST);
            host = MotomanTelnetClient.DEFAULT_MOTOMAN_HOST;
        }
        try {
            port = Integer.parseInt(jTextFieldPort.getText().trim());
            
        } catch (Exception exception) {
            port = MotomanTelnetClient.DEFAULT_PORT;
            jTextFieldPort.setText(Integer.toString(port));
        }
        tc = MotomanTelnetClient.defaultMotomanWithHostPort(host, port, getPrintStream(), getInputStream());
        if (!jCheckBoxConnect.isSelected()) {
            this.jCheckBoxConnect.setSelected(true);
        }
    }

    public void disconnect() throws IOException {
        if (null != tc) {
            tc.disconnect();
        }
        tc = null;
        if (jCheckBoxConnect.isSelected()) {
            this.jCheckBoxConnect.setSelected(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerMaxLines = new javax.swing.JSpinner();
        jCheckBoxPauseOutput = new javax.swing.JCheckBox();
        jCheckBoxConnect = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldHostName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(1);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Max Lines:");

        jSpinnerMaxLines.setValue(250);

        jCheckBoxPauseOutput.setText("Pause");

        jCheckBoxConnect.setText("Connect");
        jCheckBoxConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxConnectActionPerformed(evt);
            }
        });

        jLabel2.setText("Host: ");

        jTextFieldHostName.setText("192.168.1.33");

        jLabel3.setText("Port: ");

        jTextFieldPort.setText("23");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerMaxLines, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxPauseOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxConnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHostName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jSpinnerMaxLines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxPauseOutput)
                        .addComponent(jCheckBoxConnect)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldHostName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxConnectActionPerformed
        try {
            if (this.jCheckBoxConnect.isSelected()) {
                connect();
            } else {
                disconnect();
            }
        } catch (Exception ex) {
             Logger.getLogger(TelnetJPanel.class.getName()).log(Level.SEVERE, "", ex);
             try {
                 disconnect();
             } catch(Exception ignored) {
                 
             }
             appendText("Connection  to "+host+":"+port +" failed: "+ex +"\n");
        }
    }//GEN-LAST:event_jCheckBoxConnectActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            String line = jTextField1.getText();
            inputQueue.put(line + "\r\n");
            jTextField1.setText("");
        } catch (InterruptedException ex) {
            Logger.getLogger(TelnetJPanel.class.getName()).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private class TelnetJPanelPrintStream extends PrintStream {

        final private PrintStream ps;

        public TelnetJPanelPrintStream(PrintStream ps) {
            super(ps, true);
            this.ps = ps;
            if (null == ps) {
                throw new IllegalArgumentException("PrintStream ps may not be null");
            }
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);

            final String s = new String(buf, off, len);
            if (javax.swing.SwingUtilities.isEventDispatchThread()) {
                appendText(s);
            } else {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        appendText(s);
                    }
                });
            }
        }
    }

    final private TelnetJPanelPrintStream printStream = new TelnetJPanelPrintStream(System.out);

    public PrintStream getPrintStream() {
        return printStream;
    }

    final java.util.concurrent.LinkedBlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();

    private class TelnetJPanelInputStream extends InputStream {

        private @Nullable String curString = null;
        private int curPos = -1;

        @Override
        public int read() throws IOException {
            if (curString == null || curPos < 0 || curPos >= curString.length()) {
                try {
                    curString = inputQueue.take();
//                    System.out.println("curString = " + curString);
                    curPos = 0;
                } catch (InterruptedException ex) {
                    throw new IOException(ex);
                }
            }
            char c = curString.charAt(curPos);
            curPos++;
//            System.out.println("c = " + c);
//            System.out.println("curPos = " + curPos);
            return (int) c;
        }

    }

    private final TelnetJPanelInputStream inputStream = new TelnetJPanelInputStream();

    public InputStream getInputStream() {
        return inputStream;
    }

    final List<String> logLines = new ArrayList<>();

//    private static String tabsToSpaces(String in) {
//        return in.replaceAll("[\t]", "\\t").replaceAll("[ ]", "|s|");
////        String sa[] = in.split("\t");
////        String out = sa[0];
////        for (int i = 1; i < sa.length; i++) {
////            switch (out.length() % 4) {
////                case 0:
////                    out = out + "    " + sa[i];
////                    break;
////
////                case 1:
////                    out = out + "   " + sa[i];
////                    break;
////
////                case 2:
////                    out = out + "  " + sa[i];
////                    break;
////
////                case 3:
////                    out = out + " " + sa[i];
////                    break;
////
////            }
////        }
////        
////        System.out.println("sa = " + Arrays.toString(sa));
////        System.out.println("in = " + in);
////        System.out.println("out = " + out);
////        return out;
//    }
    private void appendLine(String l) {
        int maxLines = 100;
        try {
            maxLines = (int) jSpinnerMaxLines.getValue();
            if (maxLines < 1) {
                jSpinnerMaxLines.setValue(1);
                maxLines = 1;
            }
        } catch (Exception ignored) {
        }
        if (logLines.size() < maxLines) {
            addLogLine(l);
            if (!jCheckBoxPauseOutput.isSelected()) {
                jTextArea1.append(l);
            }
        } else {
            while (logLines.size() >= maxLines) {
                logLines.remove(0);
            }
            addLogLine(l);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < logLines.size(); i++) {
                sb.append(logLines.get(i));
            }
            if (!jCheckBoxPauseOutput.isSelected()) {
                jTextArea1.setText(sb.toString());
            }
        }
        if (!jCheckBoxPauseOutput.isSelected()) {
            jTextArea1.setCaretPosition(jTextArea1.getText().length());
        }
    }

    private void addLogLine(String l) {
        if (logLines.size() > 0) {
            String lastLine = logLines.get(logLines.size() - 1);
            if (lastLine.endsWith("\n")) {
                logLines.add(l);
            } else {
                logLines.set(logLines.size() - 1, lastLine + l);
            }
        } else {
            logLines.add(l);
        }
    }

    public void appendText(String text) {
        String txt2 = text.replace("\r\n", "\n");
        String lines[] = txt2.split("\n");
        if (lines.length <= 1 || (lines.length == 2) && lines[1].length() < 1) {
            appendLine(txt2);
        } else {
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (i < lines.length - 1 || txt2.endsWith("\n")) {
                    appendLine(line + System.lineSeparator());
                } else {
                    appendLine(line);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxConnect;
    private javax.swing.JCheckBox jCheckBoxPauseOutput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerMaxLines;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldHostName;
    private javax.swing.JTextField jTextFieldPort;
    // End of variables declaration//GEN-END:variables
}
