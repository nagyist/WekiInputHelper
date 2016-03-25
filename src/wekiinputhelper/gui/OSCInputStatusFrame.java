/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wekiinputhelper.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import wekiinputhelper.WekiInputHelper;
import wekiinputhelper.osc.OSCReceiver;
import wekiinputhelper.util.Util;
import wekiinputhelper.util.WeakListenerSupport;

/**
 *
 * @author rebecca
 */
public class OSCInputStatusFrame extends javax.swing.JFrame {
    private final WekiInputHelper w;
   // private final PropertyChangeListener oscReceiverListener = this::oscReceiverPropertyChanged;
        private final PropertyChangeListener oscReceiverListener = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            oscReceiverPropertyChanged(evt);
        }
    };

    private final WeakListenerSupport wls = new WeakListenerSupport();

    /**
     * Creates new form OSCInputStatusFrame
     */
    public OSCInputStatusFrame() {
        initComponents();
        this.w = null;
    }

    public OSCInputStatusFrame(final WekiInputHelper w) {
        initComponents();
        this.w = w;
        updateGUIForConnectionState(w.getOSCReceiver().getConnectionState());
        w.getOSCReceiver().addPropertyChangeListener(wls.propertyChange(oscReceiverListener));
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                w.getOSCReceiver().removePropertyChangeListener(oscReceiverListener);
            }
        });
        
        int p = w.getOSCReceiver().getReceivePort();
        fieldOscPort.setText(Integer.toString(p));
    }
    
    private void oscReceiverPropertyChanged(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == OSCReceiver.PROP_CONNECTIONSTATE) {
            updateGUIForConnectionState((OSCReceiver.ConnectionState) evt.getNewValue());
        }
    }
    
    private void updateGUIForConnectionState(OSCReceiver.ConnectionState cs) {
        if (cs == OSCReceiver.ConnectionState.CONNECTED) {
            labelOscStatus.setText("Listening on port " + w.getOSCReceiver().getReceivePort());
            buttonOscListen.setText("Stop listening");
            //  buttonNext.setEnabled(true);
        } else if (cs == OSCReceiver.ConnectionState.FAIL) {
            labelOscStatus.setText("Failed to set up listener");
            buttonOscListen.setText("Start listening");
            //  buttonNext.setEnabled(false);
        } else if (cs == OSCReceiver.ConnectionState.NOT_CONNECTED) {
            labelOscStatus.setText("Not listening");
            buttonOscListen.setText("Start listening");
            //  buttonNext.setEnabled(false);
        } else if (cs == OSCReceiver.ConnectionState.CONNECTING) {
            labelOscStatus.setText("Connecting...");
            buttonOscListen.setText("Stop listening");
            //  buttonNext.setEnabled(false);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fieldOscPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buttonOscListen = new javax.swing.JButton();
        labelOscStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OSC Input Status");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Input Helper listening for inputs and control on port:");

        fieldOscPort.setText("6448");
        fieldOscPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldOscPortActionPerformed(evt);
            }
        });
        fieldOscPort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldOscPortKeyTyped(evt);
            }
        });

        jLabel2.setText("Status:");

        buttonOscListen.setText("Start listening");
        buttonOscListen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOscListenActionPerformed(evt);
            }
        });

        labelOscStatus.setText("Not connected");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonOscListen)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelOscStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldOscPort)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelOscStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fieldOscPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonOscListen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fieldOscPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldOscPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldOscPortActionPerformed

    private void fieldOscPortKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldOscPortKeyTyped
        char enter = evt.getKeyChar();
        if (!(Character.isDigit(enter))) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldOscPortKeyTyped

    private void buttonOscListenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOscListenActionPerformed
        if (w.getOSCReceiver().getConnectionState()
            == OSCReceiver.ConnectionState.CONNECTED) {
            w.getOSCReceiver().stopListening();
        } else {
            int port;
            try {
                port = Integer.parseInt(fieldOscPort.getText());
            } catch (NumberFormatException ex) {
                Util.showPrettyErrorPane(this, "Port must be a valid integer greater than 0");
                return;
            }
            if (port <= 0) {
                Util.showPrettyErrorPane(this, "Port must be a valid integer greater than 0");
                return;
            }

            w.getOSCReceiver().setReceivePort(port);
            w.getOSCReceiver().startListening();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //Necessary to be handled correctly by main gui!

            this.dispose();
        }
    }//GEN-LAST:event_buttonOscListenActionPerformed

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
            java.util.logging.Logger.getLogger(OSCInputStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OSCInputStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OSCInputStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OSCInputStatusFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OSCInputStatusFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonOscListen;
    private javax.swing.JTextField fieldOscPort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelOscStatus;
    // End of variables declaration//GEN-END:variables
}
