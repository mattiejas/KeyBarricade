package main;

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("KeyBarricade");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Game());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
