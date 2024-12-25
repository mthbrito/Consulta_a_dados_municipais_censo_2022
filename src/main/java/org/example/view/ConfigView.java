package org.example.view;

import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import javax.swing.*;

public class ConfigView {

    public static void resetData1(JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados) {
        if(txt1.isVisible() && txt2.isVisible()) {
            txt2.setVisible(false);
        } else if (!txt1.isVisible() && txt2.isVisible()) {
            txt1.setVisible(true);
            txt2.setVisible(false);
        } else if (!txt1.isVisible() && !txt2.isVisible()) {
            txt1.setVisible(true);
        }
        txt1.setText(null);
        txt2.setText(null);
        textArea.setText(null);
        textArea2.setText(null);
        lblResultados.setText(null);
    }

    public static void resetData2(JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados) {
        if(txt1.isVisible() && !txt2.isVisible()) {
            txt2.setVisible(true);
        } else if (!txt1.isVisible() && txt2.isVisible()) {
            txt1.setVisible(true);
        } else if (!txt1.isVisible() && !txt2.isVisible()) {
            txt1.setVisible(true);
            txt2.setVisible(true);
        }
        txt1.setText(null);
        txt2.setText(null);
        textArea.setText(null);
        textArea2.setText(null);
        lblResultados.setText(null);
    }

    public static void resetMap(JMapFrame mainMapFrame, JMapFrame localMapFrame) {
        if (mainMapFrame.getMapContent() != null) {
            mainMapFrame.getMapContent().dispose();
            mainMapFrame.setMapContent(new MapContent());
        }
        if (localMapFrame.getMapContent() != null) {
            localMapFrame.getMapContent().dispose();
            localMapFrame.setMapContent(new MapContent());
        }
    }

    public static void localMapVisible(JMapPane localMapPane, JMapFrame localMapFrame, JTextArea textArea2) {
        if(!localMapFrame.isVisible() && !localMapPane.isVisible()) {
            localMapFrame.setVisible(true);
            localMapPane.setVisible(true);
        }
        textArea2.setVisible(false);
    }

    public static void localMapNotVisible(JMapPane localMapPane, JMapFrame localMapFrame, JTextArea textArea2) {
        if(localMapFrame.isVisible() && localMapPane.isVisible()) {
            localMapFrame.setVisible(false);
            localMapPane.setVisible(false);
        }
        textArea2.setVisible(true);
    }

    public static void configRdbtn1(JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados, JMapFrame mainMapFrame, JMapFrame localMapFrame, JMapPane localMapPane){
        resetData1(txt1, txt2, textArea, textArea2, lblResultados);
        resetMap(mainMapFrame, localMapFrame);
        localMapVisible(localMapPane, localMapFrame, textArea2);
    }

    public static void configRdbtn2(JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados, JMapFrame mainMapFrame, JMapFrame localMapFrame, JMapPane localMapPane){
        resetData2(txt1, txt2, textArea, textArea2, lblResultados);
        resetMap(mainMapFrame, localMapFrame);
        localMapNotVisible(localMapPane, localMapFrame, textArea2);
    }

}
