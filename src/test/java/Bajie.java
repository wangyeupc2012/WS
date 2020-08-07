package test.java;

import javax.swing.*;

public class Bajie extends JPanel {
    private static Bajie instance = new Bajie();

    private Bajie() {
        JLabel l1 = new JLabel(new ImageIcon("/Users/wangye/Desktop/111.png"));
        this.add(l1);
    }

    public static Bajie getInstance() {
        return instance;
    }
}
