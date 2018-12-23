package facultyprofilegeneration;

import com.placeholder.PlaceHolder;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends javax.swing.JFrame implements Runnable {

    String u1, p1;
    PlaceHolder holder;

    public LoginPage() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        //bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel4 = new javax.swing.JLabel();
        cLabel1 = new com.bolivia.label.CLabel();
        cLabel2 = new com.bolivia.label.CLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        UsernameTextField = new javax.swing.JTextField();
        PasswordTextField = new javax.swing.JPasswordField();
        LoginBtn = new javax.swing.JButton();
        VerificationErrorField = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 59, 114));
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(35, 77, 131));

        jPanel2.setBackground(new java.awt.Color(41, 99, 175));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SIGN IN");

        UsernameTextField.setBackground(new java.awt.Color(69, 149, 205));
        UsernameTextField.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        UsernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        holder = new PlaceHolder(UsernameTextField, "USERNAME");
        UsernameTextField.setToolTipText("Enter the username");
        UsernameTextField.setBorder(null);
        UsernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTextFieldActionPerformed(evt);
            }
        });
        UsernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameTextFieldKeyPressed(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                UsernameTextFieldKeyTyped(evt);
            }
        });

        PasswordTextField.setBackground(new java.awt.Color(69, 149, 205));
        PasswordTextField.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        PasswordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        holder = new PlaceHolder(PasswordTextField, "*****");
        PasswordTextField.setToolTipText("Enter the password");
        PasswordTextField.setBorder(null);
        PasswordTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordTextFieldKeyPressed(evt);
            }
        });

        LoginBtn.setBackground(new java.awt.Color(58, 142, 201));
        LoginBtn.setFont(new java.awt.Font("Yu Gothic UI Semilight", 0, 16)); // NOI18N
        LoginBtn.setForeground(new java.awt.Color(18, 47, 68));
        LoginBtn.setText("LOGIN");
        LoginBtn.setBorder(null);
        LoginBtn.setBorderPainted(false);
        LoginBtn.setOpaque(false);
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        VerificationErrorField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        VerificationErrorField.setForeground(new java.awt.Color(255, 255, 255));
        VerificationErrorField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(54, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(10, 10, 10))
                                                        .addComponent(UsernameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(PasswordTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(48, 48, 48))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(VerificationErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(71, 71, 71))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(103, 103, 103))))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(44, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(UsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(VerificationErrorField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
        );

        UsernameTextField.getAccessibleContext().setAccessibleName("");

        jLabel1.setIcon(new javax.swing.ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/PCElogo1.png")); // NOI18N

        //org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jLabel1, org.jdesktop.beansbinding.ELProperty.create("${background}"), jLabel1, org.jdesktop.beansbinding.BeanProperty.create("background"));
        //bindingGroup.addBinding(binding);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        //bindingGroup.bind();
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTextFieldActionPerformed

    }//GEN-LAST:event_UsernameTextFieldActionPerformed

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        System.out.println("Im clicked");

        String usernameStr = UsernameTextField.getText();
        String passwordStr = PasswordTextField.getText();
        System.out.println(usernameStr + " " + passwordStr);

        if (!usernameStr.equals("USERNAME") && passwordStr != "*****") {

            if (usernameStr.equals("root")) {
                if (passwordStr.equals("toor")) {
                    new FacultyProfileGeneration();
                    dispose();
                    setVisible(false);
                }
            } else {

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    Connection sqlCon = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
                    Statement stmt = sqlCon.createStatement();

                    String query = "Select PhoneNumber from faculty.facultyInfo Where FacultyID = '" + usernameStr + "';";
                    ResultSet resStr = stmt.executeQuery(query);

                    while (resStr.next()) {
                        String actualPassword = resStr.getString("PhoneNumber");
                        if (passwordStr.equals(actualPassword)) {
                            new ShowProfilePage(usernameStr);
                            dispose();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            VerificationErrorField.setText("Invalid Password/Username !");
        }

    }//GEN-LAST:event_LoginBtnActionPerformed

    private void PasswordTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordTextFieldKeyPressed

    }//GEN-LAST:event_PasswordTextFieldKeyPressed

    private void UsernameTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameTextFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTextFieldKeyPressed

    private void UsernameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameTextFieldKeyTyped

    }//GEN-LAST:event_UsernameTextFieldKeyTyped

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JLabel VerificationErrorField;
    private com.bolivia.label.CLabel cLabel1;
    private com.bolivia.label.CLabel cLabel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    //private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
