import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by mat_k on 07.01.2018.
 */

public class Fancy extends JFrame {

    private int data[][] = {
            {0,0,0,0,4,0,1,3,0},
            {0,0,0,0,1,0,0,0,0},
            {5,0,0,0,0,0,0,0,6},
            {0,3,0,0,7,0,0,5,0},
            {0,0,5,0,0,0,7,0,0},
            {0,8,0,0,3,0,0,1,0},
            {7,0,0,0,0,0,0,0,8},
            {0,0,0,0,9,0,0,0,0},
            {0,6,1,0,5,0,0,0,0},
    };

    private int group[][] = {
            {0,0,3,3,3,3,8,8,8},
            {0,0,3,4,4,8,8,8,8},
            {0,0,3,3,4,6,6,8,8},
            {0,2,2,3,4,6,6,6,7},
            {0,2,2,3,4,5,6,6,7},
            {0,2,2,2,4,5,6,6,7},
            {1,1,2,2,4,5,5,7,7},
            {1,1,1,1,4,4,5,7,7},
            {1,1,1,5,5,5,5,7,7},
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
        Field g = new Field(9);
        for(int i=0;i<9;++i) {
            for (int j = 0; j < 9; ++j) {
                Bean bean;
                if (data[i][j] == 0)
                    bean = new Bean();
                else
                    bean = new Bean(data[i][j]);
                bean.setFont(new Font("Arial", Font.BOLD, 36));
                int top, left, bottom, right ;

                top = left = bottom = right = 1;

                if(i == 0)
                    top = 4;

                if(j == 0)
                    left = 4;

                if(i == 8 || group[i][j] != group[i+1][j])
                    bottom = 4;

                if(j == 8 || group[i][j] != group[i][j+1])
                    right = 4;

                bean.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                bean.setGroup(group[i][j]);
                bean.setHorizontalAlignment(JTextField.CENTER);
                bean.setBackground(colors[group[i][j]]);
                bean.setColor(colors[group[i][j]]);
                add(bean);
                g.setBean(i, j, bean);
            }
        }
    }

    public Fancy() {
        super("Fancy Sudoku");
        initScene();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,800);
        setLayout(new GridLayout(9,9,0,0));
        setResizable(false);
        setVisible(true);
    }

    //public static void main(String[] args) {
      //  new Fancy();
    //}
}
