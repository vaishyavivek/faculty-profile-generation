package facultyprofilegeneration;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author eobardthawne
 */
public class GetFacultyID extends JFrame implements WindowListener {

    DynamicTextField idField;
    JButton nextBtn;
    Color mainColor;
    
    GetFacultyID() {
        super("Create new Profile");
        addWindowListener(this);
        setSize(400, 150);
        
        mainColor = new Color(0f, 0f, 1f, .5f);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(mainColor);
        mainPanel.setSize(getSize());

        JLabel label = new JLabel("Enter your faculty ID provided by MES authorities");
        label.setBackground(new Color(0f, 0f, 0f, .0f));
        mainPanel.add(label, BorderLayout.NORTH);

        idField = new DynamicTextField("A10099", new Dimension((int) (getWidth() * 0.85), 50));
        idField.setLabelText("This is gonna be your login id for the app hereafter");
        idField.setLabelFont(new Font("roboto", Font.TRUETYPE_FONT, 10));
        idField.requestFocusInWindow();
        mainPanel.add(idField, BorderLayout.CENTER);

        Action proceedAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isValidFacultyID()) {
                    new CreateNewProfile(idField.getText());
                    dispose();
                    setVisible(false);
                } else {
                    idField.setLabelText("This is crucial, enter a valid faculty ID to continue");
                    idField.setError(true);
                }
            }
        };

        nextBtn = new JButton("Proceed");
        nextBtn.setBackground(mainColor);
        nextBtn.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        nextBtn.getActionMap().put("Enter", proceedAction);

        nextBtn.addActionListener(proceedAction);
        mainPanel.add(nextBtn, BorderLayout.SOUTH);
        add(mainPanel);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    final boolean isValidFacultyID() {
        if (!idField.getText().equals("A10099") && idField.getText().length() == 6) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
                Statement st = con.createStatement();
                st.executeQuery("SELECT * FROM faculty.facultyInfo WHERE FacultyID = '" + idField.getText() + "'");
                ResultSet res = st.getResultSet();
                con.close();
                if (!res.next())
                    return true;
            } catch (ClassNotFoundException | SQLException ex) {}
        } 
        return false;
    }

    @Override
    public void windowClosing(WindowEvent we) {
        dispose();
        setVisible(false);
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
