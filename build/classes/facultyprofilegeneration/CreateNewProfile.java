package facultyprofilegeneration;

import com.placeholder.PlaceHolder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * @author eobardthawne
 */
public class CreateNewProfile extends JFrame implements WindowListener, ActionListener {

    String FacultyID;
    JPanel mainPanel, firstPage, secondPage, thirdPage, summaryPage, sidePane;
    CardLayout card;
    PlaceHolder holder;
    DynamicTextField firstName, lastName, midName, designation, eMail, phoneNumber;
    JComboBox departmentCb;
    JTable raceInputTable, raceOutputTable;
    Vector<Vector<Object>> raceInputDetails, raceOutputDetails;
    JLabel errorStringOnPage1, errorStringOnPage2, errorStringOnPage3;
    //JButton nextBtn, previousBtn;

    CreateNewProfile() {
        super("Createnewprofile-testWindow");
    }

    CreateNewProfile(String facultyID) {
        super("Create New Profile");
        FacultyID = facultyID;

        addWindowListener(this);

        setSize(800, 480);
        setBackground(Color.WHITE);

        mainPanel = new JPanel();
        mainPanel.setSize(new Dimension((int) (getWidth() * 0.7), getHeight()));

        card = new CardLayout();
        mainPanel.setLayout(card);
        add(mainPanel, BorderLayout.CENTER);

        prepareSidePane();
        add(sidePane, BorderLayout.WEST);

        prepareFirstPage();
        mainPanel.add(firstPage);

        prepareSecondPage();
        mainPanel.add(secondPage);

        prepareThirdPage();
        mainPanel.add(thirdPage);

        prepareSummaryPage();
        mainPanel.add(summaryPage);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    //logic expressions for firstPage started from here
    final void prepareFirstPage() {
        firstPage = new JPanel();
        firstPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        firstPage.setSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setPreferredSize(new Dimension(firstPage.getWidth(), (int) (firstPage.getHeight() * 0.2)));
        //titlePanel.setBackground(Color.red);

        JLabel titleLb = new JLabel("Create your MES Faculty Profile");
        titleLb.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        titlePanel.add(titleLb);

        JLabel subTitleLb = new JLabel("to continue manage your data");
        subTitleLb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        titlePanel.add(subTitleLb);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel idLb = new JLabel("Faculty ID: " + FacultyID);
        idLb.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        titlePanel.add(idLb);

        firstPage.add(titlePanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        contentPanel.setSize(new Dimension(firstPage.getWidth(), (int) (firstPage.getHeight() * 0.8)));

        Font font = new Font("roboto", Font.TRUETYPE_FONT, 12);

        JPanel namePanel = new JPanel();
        namePanel.setSize(new Dimension(contentPanel.getWidth(), 50));

        firstName = new DynamicTextField("First Name", new Dimension(namePanel.getWidth() / 3 - 5, 50));
        firstName.setLabelFont(font);
        namePanel.add(firstName, BorderLayout.WEST);

        midName = new DynamicTextField("Middle Name", new Dimension(namePanel.getWidth() / 3 - 5, 50));
        midName.setLabelFont(font);
        namePanel.add(midName, BorderLayout.CENTER);

        lastName = new DynamicTextField("Last Name", new Dimension(namePanel.getWidth() / 3 - 5, 50));
        lastName.setLabelFont(font);
        namePanel.add(lastName, BorderLayout.EAST);

        contentPanel.add(namePanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel departmentPanel = new JPanel();
        departmentPanel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
        departmentPanel.setSize(new Dimension(contentPanel.getWidth(), 50));
        departmentPanel.setLayout(new BoxLayout(departmentPanel, BoxLayout.X_AXIS));
        DefaultComboBoxModel options = new DefaultComboBoxModel();
        options.addElement("Select Department");
        options.addElement("Computer Engineering");
        options.addElement("IT Engineering");
        options.addElement("Mechanical Engineering");
        options.addElement("Automobile Engineering");
        options.addElement("Electronics Engineering");
        options.addElement("Electronics and Telecommunication Engineering");
        departmentCb = new JComboBox(options);
        departmentCb.setPreferredSize(new Dimension(departmentPanel.getWidth() / 2 - 20, 50));
        departmentPanel.add(departmentCb);
        
        //departmentPanel.add(Box.createHorizontalGlue());
        
        designation = new DynamicTextField("Designation", new Dimension(departmentPanel.getWidth()/2 - 20, 50));
        designation.setLabelFont(font);
        departmentPanel.add(designation, BorderLayout.CENTER);
        
        contentPanel.add(departmentPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel pNumPanel = new JPanel();
        pNumPanel.setSize(new Dimension(contentPanel.getWidth(), 50));
        pNumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        phoneNumber = new DynamicTextField("1234567890", new Dimension(pNumPanel.getWidth() / 2, 50));
        phoneNumber.setLabelFont(font);
        phoneNumber.setLabelText("Your Phone Number");
        pNumPanel.add(phoneNumber);
        contentPanel.add(pNumPanel);

        JPanel phoneNotePanel = new JPanel();
        phoneNotePanel.setBorder(BorderFactory.createEmptyBorder(-7, 2, 0, 0));
        phoneNotePanel.setSize(new Dimension(contentPanel.getWidth(), 30));
        phoneNotePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel phoneNote = new JLabel("This number will be used as your login password");
        phoneNote.setFont(new Font(Font.SERIF, Font.TRUETYPE_FONT, 12));
        phoneNotePanel.add(phoneNote);
        contentPanel.add(phoneNotePanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel emailPanel = new JPanel();
        emailPanel.setSize(new Dimension(contentPanel.getWidth(), 50));
        emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        eMail = new DynamicTextField("your-email-id@mes.ac.in", new Dimension(pNumPanel.getWidth() / 2, 50));
        eMail.setLabelFont(font);
        eMail.setLabelText("Your e-mail ID");
        emailPanel.add(eMail);
        contentPanel.add(emailPanel);

        firstPage.add(contentPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setPreferredSize(new Dimension(firstPage.getWidth(), 50));
        navigationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));

        errorStringOnPage1 = new JLabel("Are you sure to go ahead");
        errorStringOnPage1.setFont(new Font("roboto", Font.TRUETYPE_FONT, 10));
        errorStringOnPage1.setVisible(false);
        navigationPanel.add(errorStringOnPage1);

        JButton nextBtn = new JButton("Proceed >");
        nextBtn.setPreferredSize(new Dimension(120, 30));
        nextBtn.setActionCommand("ProceedAt1");
        nextBtn.addActionListener(this);
        navigationPanel.add(nextBtn);

        firstPage.add(navigationPanel, BorderLayout.SOUTH);
    }

    void removeAllErrors() {
        errorStringOnPage1.setVisible(false);
        firstName.setError(false);
        midName.setError(false);
        lastName.setError(false);
        phoneNumber.setError(false);
        eMail.setError(false);
    }

    boolean isValidEmail(String emailID) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailID == null || emailID.equals("your-email-id@mes.ac.in")) {
            return false;
        }
        return pat.matcher(emailID).matches();
    }

    boolean isValidPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(phoneNumber);
        return (m.find() && m.group().equals(phoneNumber));
    }

    final boolean insertDataOnFirstPage() {

        String facultyID = FacultyID;
        removeAllErrors();

        String name;
        if (firstName.getText().isEmpty() || firstName.getText().equals("First Name")) {
            firstName.setError(true);
            errorStringOnPage1.setVisible(true);
            errorStringOnPage1.setText("Certainly you missed writing your name properly.");
            return false;
        }
        name = firstName.getText() + " ";
        
        if (!midName.getText().isEmpty() && !midName.getText().equals("Middle Name"))
            name += midName.getText() + " ";
        
        if (lastName.getText().isEmpty() || lastName.getText().equals("Last Name")) {
            lastName.setError(true);
            errorStringOnPage1.setVisible(true);
            errorStringOnPage1.setText("Certainly you missed writing your name properly.");
            return false;
        }
        name += lastName.getText();

        if (departmentCb.getSelectedIndex() == 0) {
            errorStringOnPage1.setVisible(true);
            errorStringOnPage1.setText("Are you certain, you're above any of the departments?");
            return false;
        }
        String department = departmentCb.getSelectedItem().toString();

        String post = "";
        if(!designation.getText().isEmpty() && !designation.getText().equals("Designation"))
            post = designation.getText();
        
        String phone = phoneNumber.getText();
        if (!isValidPhoneNumber(phone)) {
            phoneNumber.setError(true);
            errorStringOnPage1.setVisible(true);
            errorStringOnPage1.setText("Are you sure, this phone number belongs to you?");
            return false;
        }

        String email = eMail.getText();
        if (!isValidEmail(email)) {
            eMail.setError(true);
            errorStringOnPage1.setVisible(true);
            errorStringOnPage1.setText("You probably messed up while writing your mail!");
            return false;
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
            Statement st = con.createStatement();
            String query = "INSERT INTO faculty.facultyInfo(FacultyID, Name, EmailID, PhoneNumber, Department, Post) VALUES ('"
                    + facultyID + "','" + name + "','" + email + "','" + phone + "','" + department + "','" + post + "');";
            System.out.println(query);
            st.executeUpdate(query);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    
    //logic expressions for secondPage started from here
    final void prepareSecondPage() {
        secondPage = new JPanel();
        secondPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        secondPage.setSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setPreferredSize(new Dimension(secondPage.getWidth(), (int) (secondPage.getHeight() * 0.2)));

        JLabel titleLb = new JLabel("Enter Races you attended");
        titleLb.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        titlePanel.add(titleLb);

        JLabel subTitleLb = new JLabel("to help differentiate yourself");
        subTitleLb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        titlePanel.add(subTitleLb);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel idLb = new JLabel("Faculty ID: " + FacultyID);
        idLb.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        titlePanel.add(idLb);

        secondPage.add(titlePanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(secondPage.getWidth(), (int) (secondPage.getHeight() * 0.8) - 96));
        contentPanel.setSize(new Dimension(secondPage.getWidth(), (int) (secondPage.getHeight() * 0.8) - 96));
        //contentPanel.setBackground(Color.yellow);

        raceInputDetails = new Vector<>();
        Vector<Object> cols = new Vector<>();
        cols.add("SrNo.");
        cols.add("Race Type");
        cols.add("Title");
        cols.add("Description");
        cols.add("Attended in");

        raceInputTable = new JTable(raceInputDetails, cols);

        JScrollPane pane = new JScrollPane(raceInputTable);
        pane.setMaximumSize(new Dimension((int) (contentPanel.getWidth() * 0.95), contentPanel.getHeight() - 160));
        pane.setPreferredSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 20));
        contentPanel.add(pane);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        Race r1 = new Race(true, new Dimension(contentPanel.getWidth(), 100));
        contentPanel.add(r1);

        JPanel verificationPanel = new JPanel();
        verificationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        verificationPanel.setMaximumSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 30));
        verificationPanel.setPreferredSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 30));

        JButton verifyBtn = new JButton("Add");
        errorStringOnPage2 = new JLabel("");
        errorStringOnPage2.setFont(new Font("roboto", Font.TRUETYPE_FONT, 10));
        verificationPanel.add(errorStringOnPage2);
        verificationPanel.add(verifyBtn);

        verifyBtn.addActionListener((ActionEvent e) -> {
            switch (r1.validateData()) {
                case 0:
                    raceInputDetails.add(r1.getFinalString());
                    raceInputTable.updateUI();
                    errorStringOnPage2.setText("This one added, add another?");
                    r1.resetField();
                    break;
                case 1:
                    errorStringOnPage2.setText("Whether it was workshop or seminar?");
                    break;
                case 2:
                    errorStringOnPage2.setText("It must have been titled something.");
                    break;
                case 3:
                    errorStringOnPage2.setText("Its fine to forget exact time but an approximation can still be applied.");
                    break;
            }
        });

        contentPanel.add(verificationPanel);

        secondPage.add(contentPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBorder(null);
        navigationPanel.setPreferredSize(new Dimension(secondPage.getWidth(), 50));

        JButton previousBtn = new JButton("< Previous");
        previousBtn.setPreferredSize(new Dimension(120, 30));
        previousBtn.setActionCommand("PreviousAt2");
        previousBtn.addActionListener(this);
        navigationPanel.add(previousBtn, BorderLayout.WEST);

        JLabel placeHolder = new JLabel("");
        placeHolder.setPreferredSize(new Dimension(secondPage.getWidth() - 260, 30));
        navigationPanel.add(placeHolder, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Proceed >");
        nextBtn.setPreferredSize(new Dimension(120, 30));
        nextBtn.setActionCommand("ProceedAt2");
        nextBtn.addActionListener(this);
        navigationPanel.add(nextBtn, BorderLayout.EAST);

        secondPage.add(navigationPanel, BorderLayout.SOUTH);
    }

    final boolean insertDataOnSecondPage() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
            Statement st = con.createStatement();
            
            for (Vector<Object> anotherRow : raceInputDetails) {
                String query = "INSERT INTO faculty.Programs(FacultyID, Title, Description, Type, Direction, TimeMonth, TimeYear) VALUES(";
                query += "'" + FacultyID + "' ,";
                query += "'" + anotherRow.elementAt(2) + "' ,";
                query += "'" + anotherRow.elementAt(3) + "',";
                query += "'" + anotherRow.elementAt(1) + "',";
                query += "'Attended',";
                query += anotherRow.elementAt(4) + ",";
                query += anotherRow.elementAt(5) + "); ";
                st.executeUpdate(query);
            }
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //logic expressions for thirdPage started from here
    final void prepareThirdPage() {
        thirdPage = new JPanel();
        thirdPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        thirdPage.setSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setPreferredSize(new Dimension(thirdPage.getWidth(), (int) (thirdPage.getHeight() * 0.2)));

        JLabel titleLb = new JLabel("Enter Races you conducted");
        titleLb.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        titlePanel.add(titleLb);

        JLabel subTitleLb = new JLabel("becuase your greatness matters");
        subTitleLb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        titlePanel.add(subTitleLb);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel idLb = new JLabel("Faculty ID: " + FacultyID);
        idLb.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        titlePanel.add(idLb);

        thirdPage.add(titlePanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(thirdPage.getWidth(), (int) (thirdPage.getHeight() * 0.8) - 96));
        contentPanel.setSize(new Dimension(thirdPage.getWidth(), (int) (thirdPage.getHeight() * 0.8) - 96));
        //contentPanel.setBackground(Color.yellow);

        raceOutputDetails = new Vector<>();
        Vector<Object> cols = new Vector<>();
        cols.add("SrNo.");
        cols.add("Race Type");
        cols.add("Title");
        cols.add("Description");
        cols.add("Conducted in");

        raceOutputTable = new JTable(raceOutputDetails, cols);

        JScrollPane pane = new JScrollPane(raceOutputTable);
        pane.setMaximumSize(new Dimension((int) (contentPanel.getWidth() * 0.95), contentPanel.getHeight() - 160));
        pane.setPreferredSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 20));
        contentPanel.add(pane);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        Race r1 = new Race(true, new Dimension(contentPanel.getWidth(), 100));
        contentPanel.add(r1);

        JPanel verificationPanel = new JPanel();
        verificationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        verificationPanel.setMaximumSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 30));
        verificationPanel.setPreferredSize(new Dimension((int) (contentPanel.getWidth() * 0.95), 30));

        JButton verifyBtn = new JButton("Add");
        errorStringOnPage3 = new JLabel("");
        errorStringOnPage3.setFont(new Font("roboto", Font.TRUETYPE_FONT, 10));
        verificationPanel.add(errorStringOnPage3);
        verificationPanel.add(verifyBtn);

        verifyBtn.addActionListener((ActionEvent e) -> {
            switch (r1.validateData()) {
                case 0:
                    raceOutputDetails.add(r1.getFinalString());
                    raceOutputTable.updateUI();
                    errorStringOnPage3.setText("This one added, add another?");
                    r1.resetField();
                    break;
                case 1:
                    errorStringOnPage3.setText("Whether it was workshop or seminar?");
                    break;
                case 2:
                    errorStringOnPage3.setText("It must have been titled something.");
                    break;
                case 3:
                    errorStringOnPage3.setText("Its fine to forget exact time but an approximation can still be applied.");
                    break;
            }
        });

        contentPanel.add(verificationPanel);

        thirdPage.add(contentPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBorder(null);
        navigationPanel.setPreferredSize(new Dimension(thirdPage.getWidth(), 50));

        JButton previousBtn = new JButton("< Previous");
        previousBtn.setPreferredSize(new Dimension(120, 30));
        previousBtn.setActionCommand("PreviousAt3");
        previousBtn.addActionListener(this);
        navigationPanel.add(previousBtn, BorderLayout.WEST);

        JLabel placeHolder = new JLabel("");
        placeHolder.setPreferredSize(new Dimension(thirdPage.getWidth() - 260, 30));
        navigationPanel.add(placeHolder, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Proceed >");
        nextBtn.setPreferredSize(new Dimension(120, 30));
        nextBtn.setActionCommand("ProceedAt3");
        nextBtn.addActionListener(this);
        navigationPanel.add(nextBtn, BorderLayout.EAST);

        thirdPage.add(navigationPanel, BorderLayout.SOUTH);
    }

    final boolean insertDataOnThirdPage() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
            Statement st = con.createStatement();
            
            for (Vector<Object> anotherRow : raceOutputDetails) {
                String query = "INSERT INTO faculty.Programs(FacultyID, Title, Description, Type, Direction, TimeMonth, TimeYear) VALUES(";
                query += "'" + FacultyID + "',";
                query += "'" + anotherRow.elementAt(2) + "',";
                query += "'" + anotherRow.elementAt(3) + "',";
                query += "'" + anotherRow.elementAt(1) + "',";
                query += "'Conducted',";
                query += anotherRow.elementAt(4) + ",";
                query += anotherRow.elementAt(5) + "); ";
                st.executeUpdate(query);
            }
            con.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    //do we really need anything on summaryPage, why the hell you didn't disappear of the thanos's snap?
    final void prepareSummaryPage() {
        summaryPage = new JPanel();
        summaryPage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        summaryPage.setSize(new Dimension(mainPanel.getWidth() - 20, mainPanel.getHeight() - 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setPreferredSize(new Dimension(summaryPage.getWidth(), (int) (summaryPage.getHeight() * 0.2)));

        JLabel titleLb = new JLabel("Summary");
        titleLb.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        titlePanel.add(titleLb);

        JLabel subTitleLb = new JLabel("final lookup to everything you entered...");
        subTitleLb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        titlePanel.add(subTitleLb);

        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel idLb = new JLabel("Faculty ID: " + FacultyID);
        idLb.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        titlePanel.add(idLb);

        summaryPage.add(titlePanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(summaryPage.getWidth(), (int) (summaryPage.getHeight() * 0.8) - 96));
        contentPanel.setSize(new Dimension(summaryPage.getWidth(), (int) (summaryPage.getHeight() * 0.8) - 96));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel line1 = new JLabel("In case you've missed something, you can go back and edit it.");
        line1.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, 12));
        contentPanel.add(line1);
        JLabel line2 = new JLabel("Changes made here will be permanent and can not be edited later.");
        line2.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, 12));
        contentPanel.add(line2);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JLabel line3 = new JLabel("But we've got you covered.");
        contentPanel.add(line3);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JLabel line4 = new JLabel("Information provided here can be changed with admin previliges.");
        line4.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, 12));
        contentPanel.add(line4);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JLabel line5 = new JLabel("Thanks for co-operating with us.");
        line5.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, 12));
        contentPanel.add(line5);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        JLabel line6 = new JLabel("Click on Finish when you're done editing. " + " Database will be auto updated.");
        line6.setFont(new Font(Font.DIALOG, Font.TRUETYPE_FONT, 12));
        contentPanel.add(line6);

        summaryPage.add(contentPanel, BorderLayout.CENTER);

        JPanel navigationPanel = new JPanel();
        navigationPanel.setBorder(null);
        navigationPanel.setPreferredSize(new Dimension(summaryPage.getWidth(), 50));

        JButton previousBtn = new JButton("< Previous");
        previousBtn.setPreferredSize(new Dimension(120, 30));
        previousBtn.setActionCommand("PreviousAt3");
        previousBtn.addActionListener(this);
        navigationPanel.add(previousBtn, BorderLayout.WEST);

        JLabel placeHolder = new JLabel("");
        placeHolder.setPreferredSize(new Dimension(summaryPage.getWidth() - 260, 30));
        navigationPanel.add(placeHolder, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Finish");
        nextBtn.setPreferredSize(new Dimension(120, 30));
        nextBtn.setActionCommand("ProceedAt4");
        nextBtn.addActionListener(this);
        navigationPanel.add(nextBtn, BorderLayout.EAST);

        summaryPage.add(navigationPanel, BorderLayout.SOUTH);
    }

    final void prepareSidePane() {
        sidePane = new JPanel();
        sidePane.setLayout(new GridLayout());//1, 2));
        sidePane.setPreferredSize(new Dimension((int) (getWidth() * 0.3) - 10, getHeight()));
        sidePane.setBackground(new Color(69, 149, 205));

        ImageIcon pceIcon = new ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/PCElogo1.png");
        Image pceImg = pceIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel pceLogo = new JLabel(new ImageIcon(pceImg));
        pceLogo.setAlignmentX(CENTER_ALIGNMENT);

        sidePane.add(pceLogo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("ProceedAt1") && insertDataOnFirstPage())
            card.next(mainPanel);
        else if (action.equals("ProceedAt2") && insertDataOnSecondPage())
            card.next(mainPanel);
        else if (action.equals("ProceedAt3") && insertDataOnThirdPage())
            card.next(mainPanel);
        else if(action.equals("ProceedAt4"))
            dispose();
        else if (e.getActionCommand().equals("PreviousAt2") || e.getActionCommand().equals("PreviousAt3")) {
            card.previous(mainPanel);
        }
    }


    @Override
    public void windowClosing(WindowEvent e) {
        dispose();
        setVisible(false);
    }

    //ahead are just overridden methods
    //just ignore them, even I don't know what do they do. HAHAHAHA
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
