package facultyprofilegeneration;

import com.placeholder.PlaceHolder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author eobardthawne
 */
public class LoginPageFrame extends Frame implements WindowListener, ActionListener {

    JTextField uNameField;
    JPasswordField pWordField;
    JButton loginBtn;
    PlaceHolder holder;
    JLabel loginDetailsLb, needHelpLb;
    JPanel centerContainer, rightContainer;

    LoginPageFrame() {
        super("Faculty Profile: Login");
        addWindowListener(this);

        setSize(600, 330);
        //setMaximumSize(new Dimension(480, 320));
        setBackground(new Color(49, 59, 114));

        prepareCenterContainer();
        add(centerContainer, BorderLayout.CENTER);

        prepareRightContainer();
        add(rightContainer, BorderLayout.EAST);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    final void prepareCenterContainer() {
        centerContainer = new JPanel();
        centerContainer.setLayout(new GridLayout());
        centerContainer.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        centerContainer.setBackground(new Color(35, 77, 131));

        ImageIcon pceIcon = new ImageIcon("/home/eobardthawne/Dropbox/NetBeansProjects/FacultyProfileGeneration/Resources/PCElogo1.png");
        Image pceImg = pceIcon.getImage().getScaledInstance((int) (getHeight() * 0.85), (int) (getHeight() * 0.85), Image.SCALE_SMOOTH);
        JLabel pceLogo = new JLabel(new ImageIcon(pceImg));
        pceLogo.setAlignmentX(CENTER_ALIGNMENT);
        centerContainer.add(pceLogo);
    }

    final void prepareRightContainer() {
        rightContainer = new JPanel();
        //rightContainer.setSize(new Dimension(getWidth()/2, getHeight()));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipadx = 150;
        gbc.ipady = 20;

        rightContainer.setLayout(layout);
        rightContainer.setPreferredSize(new Dimension(getWidth() / 2, getHeight()));
        rightContainer.setBackground(new Color(41, 99, 175));

        uNameField = new JTextField();
        uNameField.setBackground(new Color(69, 149, 205));
        uNameField.setPreferredSize(new Dimension((int)(getWidth()*0.8), 25));
        uNameField.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18));
        uNameField.setHorizontalAlignment(JTextField.CENTER);
        uNameField.setBorder(null);
        holder = new PlaceHolder(uNameField, "User Name");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        rightContainer.add(uNameField, gbc);

        pWordField = new JPasswordField();
        pWordField.setBackground(new Color(69, 149, 205));
        pWordField.setPreferredSize(new Dimension(rightContainer.getWidth(), 25));
        pWordField.setHorizontalAlignment(JTextField.CENTER);
        pWordField.setBorder(null);
        holder = new PlaceHolder(pWordField, "Password");

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);

        rightContainer.add(pWordField, gbc);

        needHelpLb = new JLabel("Need help");
        needHelpLb.setBackground(Color.CYAN);
        
        gbc.gridy = 2;
        gbc.ipadx = 50;
        gbc.ipady = 10;
        gbc.gridwidth = 1;
        rightContainer.add(needHelpLb, gbc);
        
        loginBtn = new JButton("Login");
        loginBtn.setActionCommand("Login");
        loginBtn.addActionListener(this);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        rightContainer.add(loginBtn, gbc);
        
        loginDetailsLb = new JLabel();
        loginDetailsLb.setHorizontalTextPosition(JLabel.CENTER);
        
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        rightContainer.add(loginDetailsLb, gbc);
    }

    public static void main(String args[]) {
        new LoginPageFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            if (!uNameField.getText().equals("root")) {
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    try {
                        Connection sqlCon = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
                        Statement stmt = sqlCon.createStatement();

                        ResultSet resStr = stmt.executeQuery("SELECT * FROM faculty.facultyInfo WHERE FacultyID = " + uNameField.getText());

                        while (resStr.next()) {
                            if (resStr.getString("PhoneNumber").equals(pWordField.getText())) {
                                loginDetailsLb.setText("Logging you in now...");
                                dispose();
                                setVisible(false);
                                new ProfileDetailsFrame(uNameField.getText());
                            } else {
                                loginDetailsLb.setText("Login Failed");
                            }
                        }
                    } catch (SQLException exc) {
                        loginDetailsLb.setText("Login Failed");
                    }
                } catch (ClassNotFoundException exc) {
                    exc.printStackTrace();
                }
            }else if(pWordField.getText().equals("toor")){
                dispose();
                setVisible(false);
                new FacultyProfileGeneration();
            }else 
                loginDetailsLb.setText("Login Failed");
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        setVisible(false);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }
}
