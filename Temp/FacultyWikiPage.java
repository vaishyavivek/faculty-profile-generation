package facultyprofilegeneration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author eobardthawne
 */
public class FacultyWikiPage extends JFrame implements ActionListener, WindowListener{

    
    FacultyWikiPage(){
        super("facultyWikiPage");
    }
    
    FacultyWikiPage(String facultyId){
        super("Faculty Wiki: " + facultyId);
    }
    
    //final void 
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
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
