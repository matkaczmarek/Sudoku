import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mat_k on 07.01.2018.
 */
public class Field implements Serializable {
    private Bean[][] beans;
    private int size;

    public Field(int x){
        size = x;
        beans = new Bean[size][size];
    }

    int getSize() {return size;}

    ArrayList<Bean> getGroup(int x){
        ArrayList<Bean> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(beans[i][j].getGroup() == x)
                    out.add(beans[i][j]);
            }
        }

        return out;
    }

    ArrayList<Bean> getColumn(int x){
        ArrayList<Bean> out = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            out.add(beans[i][x]);
        }

        return out;
    }

    ArrayList<Bean> getRow(int x){
        ArrayList<Bean> out = new ArrayList<>();

        out.addAll(Arrays.asList(beans[x]).subList(0, size));

        return out;
    }

    boolean checkConstatnFields(Bean bean, int insertedVal){
        ArrayList<Bean> beans = new ArrayList<>();

        beans.addAll(getRow(bean.getRow()));
        beans.addAll(getGroup(bean.getGroup()));
        beans.addAll(getColumn(bean.getColumn()));

        for (Bean other : beans){
            if(bean != other && insertedVal == other.getValue() && other.getConstant())
                return false;
        }

        return true;
    }

    void checkBeans(Bean bean, Bean other){

        if(bean == other)
            return;

        if (bean.getEmpty() || other.getEmpty())
            return;

        if(bean.getValue() == other.getValue()){
            if(bean.getConstant()) {
                return;
            }

            if(other.getConstant()) {
                return;
            }

            bean.setBackground(new Color(255, 142, 129));
            other.setBackground(new Color(255,142,129));
            bean.setWrong(true);
            other.setWrong(true);
        }

    }

    void checkGroup(int x){
        for (Bean bean: getGroup(x)) {
            for (Bean other : getGroup(x)) {
                checkBeans(bean, other);
            }
        }
    }

    void checkColumn(int x){
        for (Bean bean: getColumn(x)) {
            for (Bean other : getColumn(x)) {
                checkBeans(bean, other);
            }
        }
    }

    void checkRow(int x){
        for (Bean bean: getRow(x)) {
            for (Bean other : getRow(x)) {
                checkBeans(bean, other);
            }
        }
    }

    void resetScene(){
        for (Bean[] row : beans)
            for (Bean x : row) {
            x.setWrong(false);

            checkRow(x.getRow());
            checkColumn(x.getColumn());
            checkGroup(x.getGroup());

            if(!x.getWrong())
                x.setBackground(x.getColor());
            }
    }

    public void setBean(int i, int j, Bean bean){

        PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
        VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(new PropertyBeanListener());
        vetoableChangeSupport.addVetoableChangeListener(new VetoableBeanListener());

        bean.setColumn(j);
        bean.setRow(i);
        bean.setWrong(false);
        bean.setField(this);

        bean.addActionListener(e ->  {
                try {
                    vetoableChangeSupport.fireVetoableChange(new PropertyChangeEvent(bean, e.getActionCommand(), bean, e.getActionCommand()));
                } catch (PropertyVetoException e1) {
                    if(bean.getEmpty()){
                        bean.setText("");
                    }else{
                        bean.setText(bean.getValue() + "");
                    }

                    return;
                }

                if(e.getActionCommand().isEmpty()){
                    bean.setEmpty(true);
                    bean.setWrong(false);
                    bean.setText("");
                }else{
                    bean.setEmpty(false);
                    bean.setValue(Integer.parseInt(e.getActionCommand()));
                    propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(bean, e.getActionCommand(), bean, e.getActionCommand()));
                    System.out.println("Set value " + Integer.parseInt(e.getActionCommand()));
                }

                resetScene();
        });

        bean.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                bean.getActionListeners()[0].actionPerformed(new ActionEvent(bean, ActionEvent.ACTION_PERFORMED, bean.getText()));
            }
        });

        beans[i][j] = bean;
    }
}
