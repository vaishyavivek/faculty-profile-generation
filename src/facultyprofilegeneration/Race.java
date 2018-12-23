package facultyprofilegeneration;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

/**
 * @author eobardthawne
 */
public class Race extends JPanel{
    boolean machineType;
    Font font;
    private int counter;
    private DynamicTextField raceDefinition, raceResult;
    private JComboBox typeOfRace, monthHappened, yearHappened;
    private JPanel topPanel, bottomPanel;
    String finalString;
    
    Race(){}
    Race(boolean mType, Dimension dm){
        machineType = mType;
        
        setSize(dm);
        setMaximumSize(dm);
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        font = new Font("roboto", Font.TRUETYPE_FONT, 10);
        counter = 0;
        
        setupTopPanel();
        add(topPanel);
        
        setupBottomPanel();
        add(bottomPanel);
    }
    
    private void setupTopPanel(){
        topPanel = new JPanel();
        topPanel.setBorder(null);
        topPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight()*0.5) - 5));
        topPanel.setSize(new Dimension(getWidth(), (int) (getHeight()*0.45)));
        
        DefaultComboBoxModel typeOfRaceOptions = new DefaultComboBoxModel();
        typeOfRaceOptions.addElement("Select one");
        typeOfRaceOptions.addElement("Workshop");
        typeOfRaceOptions.addElement("Seminar");
        typeOfRace = new JComboBox(typeOfRaceOptions);
        typeOfRace.setPreferredSize(new Dimension((int)(topPanel.getWidth()*0.25), (int)(topPanel.getHeight()*0.75)));
        topPanel.add(typeOfRace, BorderLayout.WEST);
        
        raceDefinition = new DynamicTextField("Race was titled as...", new Dimension((int)(topPanel.getWidth()*0.7), (int)(topPanel.getHeight()*0.9)));
        raceDefinition.setLabelText("Titled as ");
        raceDefinition.setLabelFont(font);
        topPanel.add(raceDefinition, BorderLayout.CENTER);
    }
    
    
    private void setupBottomPanel(){
        bottomPanel = new JPanel();
        bottomPanel.setBorder(null);
        bottomPanel.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.5) - 5));
        bottomPanel.setSize(new Dimension(getWidth(), (int)(getHeight()*0.45)));
        
        raceResult = new DynamicTextField("You received credits for... (optional)", new Dimension((int)(bottomPanel.getWidth()*0.6), (int)(bottomPanel.getHeight()*0.9)));
        raceResult.setLabelText("And I earnt");
        raceResult.setLabelFont(font);
        bottomPanel.add(raceResult, BorderLayout.CENTER);
        
        JPanel timePanel = new JPanel();
        timePanel.setBorder(null);
        timePanel.setPreferredSize(new Dimension((int)(bottomPanel.getWidth()*0.35), (int)(bottomPanel.getHeight()*0.8)));
        timePanel.setSize(new Dimension((int)(bottomPanel.getWidth()*0.35), (int)(bottomPanel.getHeight()*0.8)));
        
        DefaultComboBoxModel monthHappenedOptions = new DefaultComboBoxModel();
        monthHappenedOptions.addElement("Month");
        monthHappenedOptions.addElement("January");
        monthHappenedOptions.addElement("February");
        monthHappenedOptions.addElement("March");
        monthHappenedOptions.addElement("April");
        monthHappenedOptions.addElement("May");
        monthHappenedOptions.addElement("June");
        monthHappenedOptions.addElement("July");
        monthHappenedOptions.addElement("August");
        monthHappenedOptions.addElement("September");
        monthHappenedOptions.addElement("October");
        monthHappenedOptions.addElement("November");
        monthHappenedOptions.addElement("December");
        monthHappened = new JComboBox(monthHappenedOptions);
        monthHappened.setPreferredSize(new Dimension(timePanel.getWidth()/2 - 5, (int)(timePanel.getHeight()*0.85)));
        timePanel.add(monthHappened, BorderLayout.WEST);
        
        DefaultComboBoxModel yearHappenedOptions = new DefaultComboBoxModel();
        yearHappenedOptions.addElement("Year");
        yearHappenedOptions.addElement("2014");
        yearHappenedOptions.addElement("2015");
        yearHappenedOptions.addElement("2016");
        yearHappenedOptions.addElement("2017");
        yearHappenedOptions.addElement("2018");
        yearHappened = new JComboBox(yearHappenedOptions);
        yearHappened.setPreferredSize(new Dimension(timePanel.getWidth()/2 - 5, (int)(timePanel.getHeight()*0.85)));
        timePanel.add(yearHappened, BorderLayout.EAST);
        
        bottomPanel.add(timePanel, BorderLayout.EAST);
    }
    
    final int validateData(){
        /*
        if everything is fine, set finalString to "You can add it now" and return true
        else if any error occurs, set finalString accordingly and return false
        */
        if(typeOfRace.getSelectedIndex() == 0)
            return 1;
        else if(raceDefinition.getText().equals("Race was titled as..."))
            return 2;
        else if(monthHappened.getSelectedIndex() == 0 || yearHappened.getSelectedIndex() == 0)
            return 3;
        //everything is fine
        return 0;
    }
    
    final Vector<Object> getFinalString(){
        Vector<Object> row = new Vector<>();
        row.add(counter++);//0
        row.add(typeOfRace.getSelectedItem());//1
        row.add(raceDefinition.getText());//2
        row.add(raceResult.getText());//3
        row.add(monthHappened.getSelectedIndex());//4
        row.add(yearHappened.getSelectedItem());//5
        return row;
    }
    
    /*
    if everything goes well, no error found, only then call this method
    this clears up the field and make it empty
    */
    final void resetField(){
        removeAll();
        revalidate();
        repaint();
        
        setupTopPanel();
        add(topPanel);
        
        setupBottomPanel();
        add(bottomPanel);
    }
    
}
