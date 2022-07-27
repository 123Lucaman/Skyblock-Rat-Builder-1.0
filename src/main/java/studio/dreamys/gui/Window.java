package studio.dreamys.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(String title) {
        super(title);
        setSize(500,500);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
//        getContentPane().setBackground(Color.BLACK);
//        getRootPane().setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
    }
}
