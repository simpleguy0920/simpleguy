package com.fx;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author simpleguy
 * @see [相关类/方法]（可选）
 * @since [产品 /模块版本] （可选）
 */
public class TestF {
    private JTextField textField1;
    private JCheckBox checkBox1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JPanel jp;

    public TestF() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(textField1.getText() + "1");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TestF");
        frame.setContentPane(new TestF().jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
