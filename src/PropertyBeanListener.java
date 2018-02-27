import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Created by mat_k on 07.01.2018.
 */
public class PropertyBeanListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Bean bean = (Bean) evt.getSource();

        if(bean.getEmpty())
            return;

        bean.setWrong(false);
        bean.getField().checkColumn(bean.getColumn());
        bean.getField().checkGroup(bean.getGroup());
        bean.getField().checkRow(bean.getRow());
    }
}
