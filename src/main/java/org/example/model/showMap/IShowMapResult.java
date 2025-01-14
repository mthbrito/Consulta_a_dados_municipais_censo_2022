package org.example.model.showMap;

import org.geotools.swing.JMapFrame;

import javax.swing.*;

public interface IShowMapResult {

    void showMapResult(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JMapFrame mainMapFrame, JMapFrame localMapFrame);

}
