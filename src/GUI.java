import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by mat_k on 12.01.2018.
 */
public class GUI extends JFrame{

    private JButton standardSudoku = new JButton("Standard Sudoku");
    private JButton fancySudoku = new JButton("Fancy Sudoku");
    private JButton easySudoku = new JButton("Easy Sudoku");

    public GUI(){
        setLayout(null);
        initUI();
    }

    private void initUI(){

        standardSudoku.addActionListener(e -> new Standard());
        standardSudoku.setFont(new Font("Arial", Font.PLAIN , 14));
        standardSudoku.setBounds(100, 70, 200, 200);

        add(standardSudoku);

        fancySudoku.addActionListener(e -> new Fancy());
        fancySudoku.setFont(new Font("Arial", Font.PLAIN , 14));
        fancySudoku.setBounds(400, 70, 200, 200);

        add(fancySudoku);

        easySudoku.addActionListener(e -> new Easy());
        easySudoku.setFont(new Font("Arial", Font.PLAIN , 14));
        easySudoku.setBounds(700, 70, 200, 200);

        add(easySudoku);


        setTitle("Sudoku Menu");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GUI ex = new GUI();
            ex.setVisible(true);
        });
    }
}
