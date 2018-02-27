import javax.swing.*;
import java.awt.*;

/**
 * Created by mat_k on 07.01.2018.
 */
public class Bean extends JTextField {
    private boolean constant;
    private boolean wrong;
    private boolean empty;
    private Color color;
    private int value;
    private int group;
    private Field field;
    private int column;
    private int row;
    private int prevValue;

    public Bean(){
        constant = false;
        empty = true;
        wrong = false;
    }

    public Bean(int x){
        value = x;
        constant = true;
        wrong = false;
        empty = false;
        setText(value + "");
        setEditable(false);
        setHorizontalAlignment(JLabel.CENTER);
        color = new Color(137, 137, 34);
        setForeground(color);
    }

    int getPrevValue() {
        return prevValue;
    }

    int getColumn(){return column;}

    void setColumn(int x){column = x;}

    int getRow(){return row;}

    void setRow(int x){row = x;}

    Field getField(){
        return field;
    }

    void setField(Field x){
        field = x;
    }

    public void setGroup(int x){
        group = x;
    }

    public int getGroup(){
        return group;
    }

    void setEmpty(boolean x){
        empty = x;
    }

    boolean getEmpty(){
        return empty;
    }

    void setConstant(boolean x){
        constant = x;
    }

    boolean getConstant(){
        return constant;
    }

    void setWrong(boolean x){
        wrong = x;
    }

    boolean getWrong(){
        return wrong;
    }

    public void setColor(Color x){
        color = x;
    }

    Color getColor(){
        return color;
    }

    void setValue(int x){
        prevValue = value;
        value = x;
        setEmpty(false);
        setText(value + "");
    }

    int getValue(){
        if(getEmpty())
            return -1;

        return value;
    }
}
