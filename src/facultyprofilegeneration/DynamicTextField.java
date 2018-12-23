package facultyprofilegeneration;

import com.placeholder.PlaceHolder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 *
 * @author eobardthawne
 */
public class DynamicTextField extends JPanel implements MouseListener, PropertyChangeListener {

    PlaceHolder holder;
    String originalInputStr, originalHoverText;
    JTextField input;
    JLabel label;
    JSeparator line;
    boolean isErrorThrown;
    Color mainColor;
    
    
    DynamicTextField() {}

    DynamicTextField(String text, Dimension dm) {
        originalInputStr = originalHoverText = text;
        isErrorThrown = false;
        mainColor = new Color(0f, 0f, 1f, .2f);
        setPreferredSize(dm);
        setMaximumSize(dm);
        setBorder(BorderFactory.createEmptyBorder((int) (dm.height * 0.05), 2, (int) (dm.height * 0.05), 2));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setBackground(mainColor);
                
        label = new JLabel(text);
        label.setPreferredSize(new Dimension(getWidth(), (int) (dm.height * 0.2)));
        label.setBorder(null);
        label.setForeground(Color.blue);
        label.setBackground(new Color(0f, 0f, 0f, .0f));
        add(label);

        add(Box.createRigidArea(new Dimension(0, (int) (dm.height * 0.1))));

        input = new JTextField();
        input.setPreferredSize(new Dimension(getWidth(), (int) (dm.height * 0.75)));
        input.setBackground(new Color(0f, 0f, 0f, .0f));
        input.setBorder(null);
        input.addMouseListener(this);
        holder = new PlaceHolder(input, text);
        add(input);

        line = new JSeparator();
        line.setBackground(Color.GRAY);
        line.setForeground(Color.GRAY);
        add(line);
        label.setVisible(false);
        input.addPropertyChangeListener(this);
    }

    void setError(boolean errorState) {
        isErrorThrown = errorState;
        if (isErrorThrown) {
            line.setBackground(Color.RED);
            line.setForeground(Color.RED);
            label.setVisible(true);
            label.setForeground(Color.RED);
            label.setText(originalHoverText + " * ");
        }
        else{
            line.setBackground(Color.GRAY);
            line.setForeground(Color.GRAY);
            label.setForeground(Color.BLUE);
            label.setText(originalHoverText);
        }
    }

    void setLabelText(String text) {
        label.setText(text);
        originalHoverText = text;
    }

    void setLabelFont(Font font) {
        label.setFont(font);
    }

    void setFieldFont(Font font) {
        input.setFont(font);
    }

    String getText() {
        return input.getText();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (!isErrorThrown) {
            if (label.isVisible() && input.getText().length() == 0) {
                line.setBackground(Color.GRAY);
                line.setForeground(Color.GRAY);
                label.setVisible(false);
            } else {
                line.setBackground(Color.BLUE);
                line.setForeground(Color.BLUE);
                label.setVisible(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (!isErrorThrown) {
            if (input.isFocusOwner() && input.getText().length() == 0) {
                line.setBackground(Color.GRAY);
                line.setForeground(Color.GRAY);
                label.setVisible(false);
            } else if (!input.getText().equals(originalInputStr) && input.getText().length() > 0) {
                line.setBackground(Color.BLUE);
                line.setForeground(Color.BLUE);
                label.setVisible(true);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals("foreground")){
            label.setVisible(true);
            line.setBackground(Color.BLUE);
            line.setForeground(Color.BLUE);
        }else{
            label.setVisible(false);
            line.setBackground(Color.GRAY);
            line.setForeground(Color.GRAY);
        }
    }

}
