import javax.swing.*;
import java.awt.*;

/**
 * Created by mat_k on 12.01.2018.
 */
public class Easy extends JFrame{
    private int data[][] = {
            {1,6,0,0,0,5},
            {0,0,5,2,0,0},
            {5,0,0,0,3,0},
            {0,4,0,0,0,1},
            {0,0,4,1,0,0},
            {3,0,0,0,5,4},
    };

    private Color[] colors = {
            new Color(205, 255, 18),
            new Color(251, 255, 164),
            new Color(255, 204, 238),
            new Color(184, 255, 222),
            new Color(204, 144, 255),
            new Color(255, 216, 30),
            new Color(51, 255, 242),
            new Color(255, 221, 222),
            new Color(251, 132, 255),
    };

    private void initScene(){
        Field g = new Field(6);
        for(int i=0;i<6;++i) {
            for (int j = 0; j < 6; ++j) {
                Bean bean;
                if (data[i][j] == 0)
                    bean = new Bean();
                else
                    bean = new Bean(data[i][j]);
                bean.setFont(new Font("Arial", Font.BOLD, 36));
                int top, left, bottom, right ;

                top = left = bottom = right = 1;

                if(i == 0)
                    top = 3;

                if(j == 0)
                    left = 3;

                if(i % 2 == 1)
                    bottom = 3;

                if(j % 3 == 2)
                    right = 3;

                bean.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                bean.setGroup((i / 2) + 3 * (j / 3));
                bean.setHorizontalAlignment(JTextField.CENTER);
                bean.setBackground(colors[bean.getGroup()]);
                bean.setColor(colors[bean.getGroup()]);
                add(bean);
                g.setBean(i, j, bean);
            }
        }
    }

    public Easy() {
        super("Easy Sudoku");
        initScene();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setLayout(new GridLayout(6,6,0,0));
        setResizable(false);
        setVisible(true);
    }
}
