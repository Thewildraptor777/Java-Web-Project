package com.start;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mouse extends JFrame {
  private JPanel panel;

  public mouse() {
    panel = new JPanel();
    panel.addMouseMotionListener(new MouseMotionListener() {
      public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse position: (" + e.getX() + ", " + e.getY() + ")");
      }

      public void mouseDragged(MouseEvent e) {}
    });

    add(panel);
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new mouse();
  }
}