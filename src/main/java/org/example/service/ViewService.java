package org.example.service;

import org.example.model.showInfo.ShowInfo;
import org.example.model.showMap.ShowMap;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import javax.swing.*;

public class ViewService extends ShowMap {

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

//    public static void configBtnEnter(JRadioButton rdbtnMun, JRadioButton rdbtnEst, JRadioButton rdbtnPop, JRadioButton rdbtnArea, JCheckBox checkBox, JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados, JMapFrame mainMapFrame, JMapFrame localMapFrame) {
//        int valor1, valor2, nRows;
//        String nome1, nome2, infoMunicipio, infoEstado, infoPopulacao, infoArea, text;
//        MapContent mainMapContent, localMapContent;
//
//        InfoService info = new InfoService();
//        MapService map = new MapService();
//
//        if (rdbtnMun.isSelected()) {
//            nome1 = txt1.getText();
//            try {
//                infoMunicipio = info.getInfoMunicipio(nome1);
//                textArea.setText(infoMunicipio);
//
//                nRows = info.getRowsMunicipio(nome1);
//                lblResultados.setText(nRows + " resultado(s)");
//
//                if (checkBox.isSelected() && nRows == 1){
//                    mainMapContent = map.getMapMunicipio(nome1);
//                    localMapContent = map.getLocalMapMunicipio(nome1);
//
//                    resetMap(mainMapFrame, localMapFrame);
//                    mainMapFrame.setMapContent(mainMapContent);
//                    localMapFrame.setMapContent(localMapContent);
//
//                    txt2.setVisible(false);
//                    txt2.setText(null);
//                }
//                else if(checkBox.isSelected() && nRows > 1){
//                    nome2 = txt2.getText();
//                    txt2.setVisible(true);
//
//                    resetMap(mainMapFrame, localMapFrame);
//
//                    if(!nome2.isEmpty()) {
//                        mainMapContent = map.getMapMunicipio(nome1, nome2);
//                        localMapContent = map.getLocalMapMunicipio(nome1, nome2);
//
//                        resetMap(mainMapFrame, localMapFrame);
//                        mainMapFrame.setMapContent(mainMapContent);
//                        localMapFrame.setMapContent(localMapContent);
//
//                    }
//                } else if(!checkBox.isSelected()){
//                    resetMap(mainMapFrame, localMapFrame);
//
//                    infoMunicipio = info.getInfoMunicipio(nome1);
//                    textArea.setText(infoMunicipio);
//
//                    nRows = info.getRowsMunicipio(nome1);
//                    lblResultados.setText(nRows + " resultado(s)");
//
//                    txt2.setVisible(false);
//                    txt2.setText(null);
//                }
//                else {
//                    resetData1(txt1, txt2, textArea, textArea2, lblResultados);
//                    resetMap(mainMapFrame, localMapFrame);
//                    textArea.setText("Sem dados");
//                }
//
//            } catch (SQLException | IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else if (rdbtnEst.isSelected()) {
//            nome1 = txt1.getText();
//            try {
//                infoEstado = info.getInfoEstado(nome1);
//                textArea.setText(infoEstado);
//
//                nRows = info.getRowsEstado(nome1);
//                lblResultados.setText(nRows + " resultado(s)");
//
//                if (checkBox.isSelected()){
//                    mainMapContent = map.getMapEstado(nome1);
//                    localMapContent = map.getLocalMapEstado(nome1);
//
//                    mainMapFrame.setMapContent(mainMapContent);
//                    localMapFrame.setMapContent(localMapContent);
//                } else {
//                    resetMap(mainMapFrame, localMapFrame);
//                }
//            } catch (SQLException | IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else if (rdbtnPop.isSelected()) {
//            valor1 = Integer.parseInt(txt1.getText());
//            valor2 = Integer.parseInt(txt2.getText());
//            try {
//                infoPopulacao = info.getInfoPopulacao(valor1, valor2);
//                textArea.setText(infoPopulacao);
//
//                nRows = info.getRowsPopulacao(valor1, valor2);
//                lblResultados.setText(nRows + " resultado(s)");
//
//                if(checkBox.isSelected()){
//                    mainMapContent = map.getMapPopulacao(valor1, valor2);
//
//                    mainMapFrame.setMapContent(mainMapContent);
//                    localMapFrame.setMapContent(new MapContent());
//
//                    text = info.getSumTotalPopulation("populacao", valor1, valor2) + "\n" + info.getSumTotalArea("populacao", valor1,valor2);
//                    textArea2.setText(text);
//                } else {
//                    resetMap(mainMapFrame, localMapFrame);
//                }
//            } catch (SQLException | IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else if (rdbtnArea.isSelected()) {
//            valor1 = Integer.parseInt(txt1.getText());
//            valor2 = Integer.parseInt(txt2.getText());
//            try {
//                infoArea = info.getInfoArea(valor1, valor2);
//                textArea.setText(infoArea);
//
//                nRows = info.getRowsArea(valor1, valor2);
//                lblResultados.setText(nRows + " resultado(s)");
//
//                if(checkBox.isSelected()) {
//                    mainMapContent = map.getMapArea(valor1, valor2);
//
//                    mainMapFrame.setMapContent(mainMapContent);
//                    localMapFrame.setMapContent(new MapContent());
//
//                    text = info.getSumTotalPopulation("area", valor1, valor2) + "\n" + info.getSumTotalArea("area", valor1,valor2);
//                    textArea2.setText(text);
//                } else {
//                    resetMap(mainMapFrame, localMapFrame);
//                }
//            } catch (SQLException | IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        textArea.setCaretPosition(0);
//        lblResultados.setVisible(true);
//
//    }

    public static void configBtnEnter(JRadioButton rdbtnMun, JRadioButton rdbtnEst, JRadioButton rdbtnPop, JRadioButton rdbtnArea, JCheckBox checkBox, JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JLabel lblResultados, JMapFrame mainMapFrame, JMapFrame localMapFrame){
        if (rdbtnMun.isSelected()) {
            showInfo(1, txt1, textArea, lblResultados);
            if(checkBox.isSelected()) {
                showMap(1, txt1, txt2, textArea, textArea2, mainMapFrame, localMapFrame);
            } else {
            resetMap(mainMapFrame, localMapFrame);
            }
        } else if (rdbtnEst.isSelected()) {
            showInfo(2, txt1, textArea, lblResultados);
            if(checkBox.isSelected()) {
                showMap(2, txt1, txt2, textArea, textArea2, mainMapFrame, localMapFrame);
            } else {
                resetMap(mainMapFrame, localMapFrame);
            }
        } else if (rdbtnPop.isSelected()) {
            textArea2.setText(null);
            showInfo(3, txt1, txt2, textArea, lblResultados);
            if(checkBox.isSelected()){
                showMap(3, txt1, txt2, textArea, textArea2, mainMapFrame, localMapFrame);
            } else {
                resetMap(mainMapFrame, localMapFrame);
            }
        } else if (rdbtnArea.isSelected()) {
            textArea2.setText(null);
            showInfo(4, txt1, txt2, textArea, lblResultados);
            if(checkBox.isSelected()){
                showMap(4, txt1, txt2, textArea, textArea2, mainMapFrame, localMapFrame);
            }
            else {
                resetMap(mainMapFrame, localMapFrame);
            }
        }
        textArea.setCaretPosition(0);
        lblResultados.setVisible(true);
    }
}
