package facultyprofilegeneration;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

/**
 * @author eobardthawne
 */
public final class FacultyProfileGeneration extends JFrame implements WindowListener, ActionListener {

    Color mainColor;
    JButton addNewBtn, aboutUsBtn, getHelpBtn, searchBtn;
    JPanel leftContainer, centerContainer, topContainer;
    JComboBox sortByOption, departmentToFilter, yearToFilter;
    DynamicTextField searchBox;
    String searchString;
    JRadioButton facultyIDRadBtn, facultyNameRadBtn;
    
    //@SuppressWarnings("LeakingThisInConstructor")
    FacultyProfileGeneration() {
        super("Faculty Profile Generation Program - Admin");
        addWindowListener(this);
        
        mainColor = new Color(0f, 0f, 1f, .2f);
        setSize(1024, 768);

        prepareLeftContainer();
        add(leftContainer, BorderLayout.WEST);
        
        searchString = "";
        JPanel tempTopContainer = new JPanel();
        tempTopContainer.setBackground(mainColor);
        tempTopContainer.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.17)));
        tempTopContainer.setLayout(new BoxLayout(tempTopContainer, BoxLayout.X_AXIS));
        tempTopContainer.add(Box.createRigidArea(new Dimension(getWidth() / 8, 0)));
        prepareTopContainer();
        tempTopContainer.add(topContainer);
        add(tempTopContainer, BorderLayout.NORTH);

        //prepare centerContainer which will contain our sql data
        centerContainer = new JPanel();
        centerContainer.setBackground(mainColor);
        centerContainer.setPreferredSize(new Dimension(getWidth() * 7/8, (int) (getHeight() * 0.83)));
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        add(centerContainer, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        updateSqlDataTable();
    }

    void prepareLeftContainer() {
        leftContainer = new JPanel();
        leftContainer.setBackground(mainColor);
        leftContainer.setLayout(new BoxLayout(leftContainer, BoxLayout.Y_AXIS));
        //set preferred size to 12.5% of width and 100% of height
        leftContainer.setPreferredSize(new Dimension(getWidth() / 8, getHeight()));

        //add blank space of 1/60% of window height
        leftContainer.add(Box.createRigidArea(new Dimension(0, getHeight() / 60)));

        //add pce logo at the top left corner
        ImageIcon pceIcon = new ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/PCElogo1.png");
        Image pceImg = pceIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel pceLogo = new JLabel(new ImageIcon(pceImg));
        pceLogo.setAlignmentX(CENTER_ALIGNMENT);
        leftContainer.add(pceLogo);

        //add blank space of 1/60% of window height
        leftContainer.add(Box.createRigidArea(new Dimension(0, getHeight() / 60)));

        ImageIcon addNewBtnIcon = new ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/AddNewBtn.png");
        Image addNewBtnImg = addNewBtnIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        addNewBtn = new JButton();
        addNewBtn.setMaximumSize(new Dimension(50, 50));
        addNewBtn.setToolTipText("Add new faculty profile");
        addNewBtn.setIcon(new ImageIcon(addNewBtnImg));
        addNewBtn.setBorderPainted(false);
        addNewBtn.setActionCommand("AddNew");
        addNewBtn.addActionListener(this);
        addNewBtn.setAlignmentX(CENTER_ALIGNMENT);
        leftContainer.add(addNewBtn);

        //add blank space of 1/60% of window height
        leftContainer.add(Box.createRigidArea(new Dimension(0, getHeight() / 60)));

        ImageIcon getHelpBtnIcon = new ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/GetHelpBtn.png");
        Image getHelpBtnImg = getHelpBtnIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        getHelpBtn = new JButton();
        getHelpBtn.setMaximumSize(new Dimension(50, 50));
        getHelpBtn.setToolTipText("Get help using the app");
        getHelpBtn.setIcon(new ImageIcon(getHelpBtnImg));
        getHelpBtn.setBorderPainted(false);
        getHelpBtn.setActionCommand("GetHelp");
        getHelpBtn.addActionListener(this);
        getHelpBtn.setAlignmentX(CENTER_ALIGNMENT);
        leftContainer.add(getHelpBtn);

        //add blank space of 1/60% of window height
        leftContainer.add(Box.createRigidArea(new Dimension(0, getHeight() / 60)));
        
        ImageIcon aboutUsBtnIcon = new ImageIcon("/home/eobardthawne/Dropbox/Projects/NetBeansProjects/FacultyProfileGeneration/Resources/AboutUsBtn.png");
        Image aboutUsBtnImg = aboutUsBtnIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        aboutUsBtn = new JButton(new ImageIcon(aboutUsBtnImg));
        aboutUsBtn.setMaximumSize(new Dimension(50, 50));
        aboutUsBtn.setToolTipText("Know about us");
        aboutUsBtn.setBorderPainted(false);
        aboutUsBtn.setActionCommand("AboutUs");
        aboutUsBtn.addActionListener(this);
        aboutUsBtn.setAlignmentX(CENTER_ALIGNMENT);
        leftContainer.add(aboutUsBtn);

        //add blank space of 1/60% of window height
        leftContainer.add(Box.createRigidArea(new Dimension(0, getHeight() / 60)));
    }

    //this container has sorting combo box
    void prepareTopContainer() {
        topContainer = new JPanel();
        topContainer.setBackground(new Color(0f, 0f, 0f, .0f));
        topContainer.setPreferredSize(new Dimension(getWidth() * 7/8, (int) (getHeight() * 0.17)));
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));

        topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0f, 0f, 0f, .0f));
        searchPanel.setPreferredSize(new Dimension(topContainer.getPreferredSize().width, (topContainer.getHeight() - 45) / 2 + 12));
        
        searchBox = new DynamicTextField("Search", new Dimension((int) (searchPanel.getPreferredSize().width*0.3) - 5, 50));
        searchBox.setLabelFont(new Font("roboto", Font.TRUETYPE_FONT, 12));
        searchBox.setLabelText("Enter your search query here");
        searchPanel.add(searchBox);
        
        facultyIDRadBtn = new JRadioButton("Faculty ID");
        facultyIDRadBtn.setBackground(new Color(0f, 0f, 0f, .0f));
        facultyNameRadBtn = new JRadioButton("Name");
        facultyNameRadBtn.setBackground(new Color(0f, 0f, 0f, .0f));
        ButtonGroup searchByGroup = new ButtonGroup();
        searchByGroup.add(facultyIDRadBtn);
        searchByGroup.add(facultyNameRadBtn);
        
        searchPanel.add(facultyIDRadBtn);
        searchPanel.add(facultyNameRadBtn);
        
        searchBtn = new JButton("Search");
        searchBtn.setPreferredSize(new Dimension(100, searchBox.getPreferredSize().height));
        searchBtn.setToolTipText("Add new faculty profile");
        searchBtn.setBorderPainted(false);
        searchBtn.setActionCommand("Search");
        searchBtn.addActionListener(this);
        searchPanel.add(searchBtn);
        
        topContainer.add(searchPanel);
        
        topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPanel arrangementPanel = new JPanel();
        arrangementPanel.setBackground(new Color(0f, 0f, 0f, .0f));
        arrangementPanel.setPreferredSize(new Dimension(topContainer.getPreferredSize().width, (topContainer.getHeight() - 45) / 2 - 7));
        arrangementPanel.setLayout(new FlowLayout());
        
        JLabel departmentLabel = new JLabel("Filter by");
        departmentLabel.setHorizontalAlignment(JLabel.CENTER);
        arrangementPanel.add(departmentLabel);
        
        DefaultComboBoxModel options = new DefaultComboBoxModel();
        options.addElement("All Departments");
        options.addElement("Computer Engineering");
        options.addElement("IT Engineering");
        options.addElement("Mechanical Engineering");
        options.addElement("Automobile Engineering");
        options.addElement("Electronics Engineering");
        options.addElement("Electronics and Telecommunication Engineering");
        
        departmentToFilter = new JComboBox(options);
        departmentToFilter.setActionCommand("FilterDepartmentBy");
        departmentToFilter.addActionListener(this);
        departmentToFilter.setMaximumSize(new Dimension(120, 40));
        arrangementPanel.add(departmentToFilter);
        
        JLabel sortByLabel = new JLabel("Sort profiles by ");
        sortByLabel.setHorizontalAlignment(JLabel.CENTER);
        arrangementPanel.add(sortByLabel);

        DefaultComboBoxModel options1 = new DefaultComboBoxModel();
        options1.addElement("Faculty Id");
        options1.addElement("Faculty Name");
        options1.addElement("Department");

        sortByOption = new JComboBox(options1);
        sortByOption.setActionCommand("SortBy");
        sortByOption.addActionListener(this);
        sortByOption.setMaximumSize(new Dimension(120, 40));
        arrangementPanel.add(sortByOption);
        
        topContainer.add(arrangementPanel);
        
        topContainer.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    void updateSqlDataTable() {
        int sortBy = sortByOption.getSelectedIndex();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection sqlCon = DriverManager.getConnection("jdbc:mysql://localhost/faculty", "user", "pass");
            Statement stmt = sqlCon.createStatement();

            String query = "SELECT FacultyID, Name, EmailID, PhoneNumber, Department FROM faculty.facultyInfo ";
            
            if(!searchString.equals("")){
                query += "WHERE ";
                if(facultyIDRadBtn.isSelected())
                    query += "FacultyID LIKE '%" + searchString + "%' ";
                else
                    query += "Name LIKE '%" + searchString + "%' ";
            }
            
            if(departmentToFilter.getSelectedIndex() > 0){
                if(searchString.equals(""))
                    query += " WHERE Department = '" + departmentToFilter.getSelectedItem().toString() + "' ";
                else
                    query += " AND Department = '" + departmentToFilter.getSelectedItem().toString() + "' ";
            }
            
            switch(sortBy){
                case 1:
                    query += "ORDER BY Name";
                    break;
                case 2:
                    query += "ORDER BY Department";
                    break;
                default:
                    query += "ORDER BY FacultyID";
            }
            
            System.out.print(query);
            
            ResultSet resStr = stmt.executeQuery(query);

            Vector<Vector<Object>> rows = new Vector<>();
            while (resStr.next()) {
                Vector<Object> anotherRow = new Vector<>();
                anotherRow.add(resStr.getString("FacultyID"));
                anotherRow.add(resStr.getString("Name"));
                anotherRow.add(resStr.getString("EmailID"));
                anotherRow.add(resStr.getString("PhoneNumber"));
                anotherRow.add(resStr.getString("Department"));
                anotherRow.add(resStr.getString("FacultyID"));
                rows.add(anotherRow);
            }

            Vector<Object> cols = new Vector<>();
            cols.add("Faculty Id");
            cols.add("Name");
            cols.add("EmailID");
            cols.add("Phone Number");
            cols.add("Department");
            cols.add("");

            JTable sqlDataTable = new JTable(rows, cols);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            sqlDataTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            sqlDataTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            sqlDataTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

            sqlDataTable.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
            sqlDataTable.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

            sqlDataTable.setPreferredScrollableViewportSize(new Dimension(300, 300));
            sqlDataTable.setFillsViewportHeight(true);

            //set different column widths
            TableColumn column = sqlDataTable.getColumnModel().getColumn(0);
            column.setMaxWidth(70);
            column = sqlDataTable.getColumnModel().getColumn(1);
            column.setPreferredWidth(80);
            column = sqlDataTable.getColumnModel().getColumn(3);
            column.setMinWidth(100);
            column.setMaxWidth(100);
            column = sqlDataTable.getColumnModel().getColumn(4);
            column.setPreferredWidth(75);
            column = sqlDataTable.getColumnModel().getColumn(5);
            column.setMaxWidth(50);

            sqlDataTable.setRowHeight(30);

            //scroll pane will ensure showing scroll bar if all the contents do not fit in
            JScrollPane pane = new JScrollPane(sqlDataTable);

            //update the center container by removing all previous elements and adding the new table
            centerContainer.removeAll();
            centerContainer.add(pane);
            centerContainer.updateUI();
            sqlCon.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new FacultyProfileGeneration();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("SortBy") || command.equals("FilterDepartmentBy"))
            updateSqlDataTable();
        else if(command.equals("Search")){
            searchString = searchBox.getText();
            updateSqlDataTable();
        }
        else if (command.equals("AddNew")) 
            new GetFacultyID();
        else if(command.equals("AboutUs"))
            new AboutUs();
        else if(command.equals("GetHelp"))
            new HelpFrame();
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
