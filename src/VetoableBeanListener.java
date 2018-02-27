import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Created by mat_k on 07.01.2018.
 */
public class VetoableBeanListener implements VetoableChangeListener {
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

        if (evt.getPropertyName().isEmpty())
            return;

        int value;

        try {
            value = Integer.parseInt(evt.getPropertyName());
        } catch (NumberFormatException e){
            throw new PropertyVetoException("Number Error", evt);
        }

        Bean bean = (Bean) evt.getSource();
        int fieldSize = bean.getField().getSize();

        if(value > fieldSize || value < 1){
            throw new PropertyVetoException("Out of range error", evt);
        }

        if(!bean.getField().checkConstatnFields(bean, value))
            throw new PropertyVetoException("Constant error", evt);
    }
}
