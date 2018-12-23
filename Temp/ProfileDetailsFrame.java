package facultyprofilegeneration;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 * @author eobardthawne
 */
public class ProfileDetailsFrame extends JFrame implements WindowListener{

    ProfileDetailsFrame() {
        super("Profile details");
    }

    ProfileDetailsFrame(String profileID) {
        super("Profile Details");
        addWindowListener(this);
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                Connection sqlCon = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
                Statement stmt = sqlCon.createStatement();

                ResultSet resStr = stmt.executeQuery("SELECT * FROM faculty.facultyInfo WHERE FacultyID = '" + profileID + "';");

                while (resStr.next()) {
                    setTitle("Faculty Details: " + resStr.getString("Name"));
                    prepareUI();
                }
                setSize(800, 600);
                setResizable(false);
                setLocationRelativeTo(null);
                setVisible(true);
                sqlCon.close();
            } catch (SQLException e) {
                e.printStackTrace();}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();}
    }

    final void prepareUI(){
        
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