package View;

import Helper.Hash;
import Model.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

class Limite extends PlainDocument {

    private int limite;

    Limite(int limit) {
        super();
        this.limite = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limite) {
            super.insertString(offset, str, attr);
        }
    }
}

/**
 *
 * @author krawz
 */
public class vRegistro extends javax.swing.JFrame {

    /**
     * Creates new form vLogin
     */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String PATRON_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+" + "(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
    private static final String NOMBRE = "^[A-Za-z]{1,}\\ ?[A-Za-z]{0,}?$";
    private static final String LETRAS = "^[A-Za-z]{1,}$";

    boolean evaluar() {
        boolean result = true;
        if (txtNombre.getText().isEmpty()) {
            lblNombre.setForeground(Color.red);
            result = false;
        } else {
            lblNombre.setForeground(Color.white);
        }

        if (txtApellidoP.getText().isEmpty()) {
            lblApellidoP.setForeground(Color.red);
            result = false;
        } else {
            lblApellidoP.setForeground(Color.white);
        }

        try {
            if (fechaNacimiento.getDate().toString().isEmpty()) {
                lblFechaN.setForeground(Color.red);
                result = false;
            } else {
                lblFechaN.setForeground(Color.white);
            }
        } catch (NullPointerException ex) {
            lblFechaN.setForeground(Color.red);
            result = false;
        }

        if (txtColonia.getText().isEmpty()) {
            lblColonia.setForeground(Color.red);
            result = false;
        } else {
            lblColonia.setForeground(Color.white);
        }
        
        if (txtCalle2.getText().isEmpty()) {
            lblCalle2.setForeground(Color.red);
            result = false;
        } else {
            lblCalle2.setForeground(Color.white);
        }
        
        if (txtCalle1.getText().isEmpty()) {
            lblCalle1.setForeground(Color.red);
            result = false;
        } else {
            lblCalle1.setForeground(Color.white);
        }

        if (txtCorreo.getText().isEmpty()) {
            lblCorreo.setForeground(Color.red);
            result = false;
        } else {
            if (!txtCorreo.getText().matches(PATRON_CORREO)) {
                lblCorreo.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "El email no es válido",
                    "Error", JOptionPane.ERROR_MESSAGE);
                result = false;
            } else {
                lblCorreo.setForeground(Color.white);
            }
        }

        if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            lblPassword.setForeground(Color.red);
            result = false;
        } else if (String.valueOf(txtPassword.getPassword()).length() < 5) {
            //JOptionPane.showMessageDialog(null, "La contraseña debe tener más de 5 carácteres");
            lblPassword.setForeground(Color.red);
            result = false;
        } else {
            lblPassword.setForeground(Color.white);
            if (!String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtPasswordRepeat.getPassword()))) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden",
                    "Error", JOptionPane.ERROR_MESSAGE);
                lblPasswordRepeat.setForeground(Color.red);
                result = false;
            } else {
                lblPasswordRepeat.setForeground(Color.white);
            }
        }

        return result;
    }

    public vRegistro() {
        setTitle("");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date maxYear = cal.getTime();
        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -110);
        Date minYear = cal.getTime();

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        UIManager.put(CalendarHeaderHandler.uiControllerID, "org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler");
        UIManager.put(SpinningCalendarHeaderHandler.ARROWS_SURROUND_MONTH, Boolean.TRUE);
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        fechaNacimiento.getMonthView().setZoomable(true);
        fechaNacimiento.getMonthView().setLowerBound(minYear);
        fechaNacimiento.getMonthView().setUpperBound(maxYear);
        fechaNacimiento.getEditor().setEditable(false);
        fechaNacimiento.setFormats(formatter);
        txtCalle1.setDocument(new Limite(45));
        txtCalle1.setTransferHandler(null);
        txtCalle2.setDocument(new Limite(45));
        txtCalle2.setTransferHandler(null);
        txtColonia.setDocument(new Limite(45));
        txtColonia.setTransferHandler(null);
        txtNombre.setDocument(new Limite(30));
        txtNombre.setTransferHandler(null);
        txtApellidoM.setDocument(new Limite(30));
        txtApellidoM.setTransferHandler(null);
        txtApellidoP.setDocument(new Limite(30));
        txtApellidoP.setTransferHandler(null);
        txtCorreo.setDocument(new Limite(50));
        txtCorreo.setTransferHandler(null);
        txtPassword.setDocument(new Limite(40));
        txtPassword.setTransferHandler(null);
        txtPasswordRepeat.setDocument(new Limite(40));
        txtPasswordRepeat.setTransferHandler(null);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApellidoP = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblApellidoM = new javax.swing.JLabel();
        lblFechaN = new javax.swing.JLabel();
        fechaNacimiento = new org.jdesktop.swingx.JXDatePicker();
        lblColonia = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtPasswordRepeat = new javax.swing.JPasswordField();
        lblPasswordRepeat = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtApellidoM = new javax.swing.JTextField();
        txtApellidoP = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        txtCalle1 = new javax.swing.JTextField();
        lblCalle1 = new javax.swing.JLabel();
        txtCalle2 = new javax.swing.JTextField();
        lblCalle2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(39, 39, 39));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre: *");
        lblNombre.setAlignmentX(0.5F);

        lblApellidoP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblApellidoP.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoP.setText("Apellido Paterno: *");

        txtNombre.setToolTipText("Campo obligatorio");
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(51, 255, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Registro");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 153, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Registrarse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblApellidoM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblApellidoM.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoM.setText("Apellido Materno:");

        lblFechaN.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFechaN.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaN.setText("Fecha de Nacimiento: *");

        fechaNacimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                fechaNacimientoMouseReleased(evt);
            }
        });

        lblColonia.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblColonia.setForeground(new java.awt.Color(255, 255, 255));
        lblColonia.setText("Colonia y Número de Casa: *");

        lblCorreo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreo.setText("Correo: *");

        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Contraseña: * (Debe ser mayor a 5 carácteres)");

        lblPasswordRepeat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPasswordRepeat.setForeground(new java.awt.Color(255, 255, 255));
        lblPasswordRepeat.setText("Repetir Contraseña: *");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Los campos con * son obligatorios.");

        txtApellidoM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoMFocusLost(evt);
            }
        });
        txtApellidoM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoMKeyTyped(evt);
            }
        });

        txtApellidoP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoPFocusLost(evt);
            }
        });
        txtApellidoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoPKeyTyped(evt);
            }
        });

        lblCalle1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCalle1.setForeground(new java.awt.Color(255, 255, 255));
        lblCalle1.setText("Calle 1: *");

        lblCalle2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCalle2.setForeground(new java.awt.Color(255, 255, 255));
        lblCalle2.setText("Calle 2: *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 161, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCalle1)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPasswordRepeat)
                            .addComponent(lblColonia)
                            .addComponent(lblApellidoM)
                            .addComponent(lblApellidoP)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre)
                            .addComponent(lblFechaN)
                            .addComponent(fechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword)
                            .addComponent(txtPasswordRepeat)
                            .addComponent(txtCorreo)
                            .addComponent(txtApellidoM)
                            .addComponent(txtApellidoP)
                            .addComponent(txtColonia)
                            .addComponent(txtCalle1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblCorreo)
                                .addComponent(lblCalle2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtCalle2))
                        .addGap(91, 91, 91)))
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblApellidoP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblApellidoM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFechaN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblColonia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblCalle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCalle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCalle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCalle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblPasswordRepeat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Point mouseDownCompCoords = null;

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        vLogin v = new vLogin();
        v.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        Point currCoords = evt.getLocationOnScreen();
        setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        mouseDownCompCoords = evt.getPoint();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        // TODO add your handling code here:
        mouseDownCompCoords = null;
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (!evaluar()) {
            JOptionPane.showMessageDialog(null, "Revise los campos con texto en color rojo",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Usuario usuario = new Usuario();
            usuario.setNombre(txtNombre.getText());
            usuario.setApellidoP(txtApellidoP.getText());
            usuario.setApellidoM(txtApellidoM.getText());
            usuario.setFechaN(formatter.format(fechaNacimiento.getDate()));
            usuario.setCalle1(txtCalle1.getText());
            usuario.setCalle2(txtCalle2.getText());
            usuario.setColonia(txtColonia.getText());
            usuario.setCorreo(txtCorreo.getText());
            usuario.setContrasena(Hash.sha1(String.valueOf(txtPassword.getPassword())));
            try {
                if (usuario.crearUsuario()) {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Creación de Usuario", JOptionPane.INFORMATION_MESSAGE);
                    vLogin d = new vLogin();
                    d.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar crear el usuario",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar crear el usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println(ex.getMessage());
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void fechaNacimientoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaNacimientoMouseReleased
        // TODO add your handling code here:
        try {
            System.out.println("Fecha: " + fechaNacimiento.getDate().toString());
        } catch (NullPointerException ex) {

        }
    }//GEN-LAST:event_fechaNacimientoMouseReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        if (!evaluarNombre(evt.getKeyChar())) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoPKeyTyped
        // TODO add your handling code here:
        if (!evaluarLetra(evt.getKeyChar())) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtApellidoPKeyTyped

    private void txtApellidoMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoMKeyTyped
        // TODO add your handling code here:
        if (!evaluarLetra(evt.getKeyChar())) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtApellidoMKeyTyped

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        txtNombre.setText(mayuscula(txtNombre.getText()));
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtApellidoPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoPFocusLost
        // TODO add your handling code here:
        txtApellidoP.setText(mayuscula(txtApellidoP.getText()));
    }//GEN-LAST:event_txtApellidoPFocusLost

    private void txtApellidoMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoMFocusLost
        // TODO add your handling code here:
        txtApellidoM.setText(mayuscula(txtApellidoM.getText()));
    }//GEN-LAST:event_txtApellidoMFocusLost

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        // TODO add your handling code here:
        if(!txtNombre.getText().isEmpty()&&txtNombre.getText().charAt(0)==' '){
            evt.consume();
                getToolkit().beep();
                txtNombre.setText(txtNombre.getText().substring(1, txtNombre.getText().length()));
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    String mayuscula(String entrada) {
        String salida = "";
        if (!entrada.isEmpty()) {
            salida = entrada.substring(0, 1).toUpperCase() + entrada.substring(1);
        }
        return salida;
    }

    boolean evaluarLetra(char c) {
        if (!String.valueOf(c).matches(LETRAS) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            return false;
        }
        return true;
    }

    boolean evaluarNombre(char c) {
        if (c != ' ') {
            if (!String.valueOf(c).matches(LETRAS) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                return false;
            }
        }else{
            if(txtNombre.getText().contains(" ")){
                return false;
            }else if(txtNombre.getText().isEmpty()){
                return false;
            }
        }
        return true;
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
            java.util.logging.Logger.getLogger(vRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker fechaNacimiento;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblApellidoM;
    private javax.swing.JLabel lblApellidoP;
    private javax.swing.JLabel lblCalle1;
    private javax.swing.JLabel lblCalle2;
    private javax.swing.JLabel lblColonia;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFechaN;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordRepeat;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtCalle1;
    private javax.swing.JTextField txtCalle2;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordRepeat;
    // End of variables declaration//GEN-END:variables
}
