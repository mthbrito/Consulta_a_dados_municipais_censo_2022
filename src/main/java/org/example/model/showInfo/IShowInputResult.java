package org.example.model.showInfo;

import javax.swing.*;

public interface IShowInputResult {

    void showInputResult(int option, JTextField txt1, JTextArea textArea, JLabel lblResultados);

    void showInputResult(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JLabel lblResultados);

}
