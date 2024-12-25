package org.example.view;

import org.example.service.InfoService;
import org.example.service.MapService;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

import static org.example.view.ConfigView.*;

public class WindowView {
    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowView window = new WindowView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WindowView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 560, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Consulta a dados municipais (Censo 2022)");
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(10, 11, 232, 500);
        frame.getContentPane().add(panel);

        JLabel lblBuscar = new JLabel("Buscar por:");
        lblBuscar.setBounds(46, 26, 90, 14);
        panel.add(lblBuscar);

        JRadioButton rdbtnMun = new JRadioButton("Município");
        rdbtnMun.setBounds(41, 47, 85, 23);
        panel.add(rdbtnMun);

        JRadioButton rdbtnEst = new JRadioButton("Estado");
        rdbtnEst.setBounds(127, 47, 80, 23);
        panel.add(rdbtnEst);

        JRadioButton rdbtnPop = new JRadioButton("População");
        rdbtnPop.setBounds(41, 77, 85, 23);
        panel.add(rdbtnPop);

        JRadioButton rdbtnArea = new JRadioButton("Área");
        rdbtnArea.setBounds(127, 77, 80, 23);
        panel.add(rdbtnArea);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnMun);
        group.add(rdbtnEst);
        group.add(rdbtnPop);
        group.add(rdbtnArea);

        JTextField txt1 = new JTextField();
        txt1.setBounds(41, 107, 150, 30);
        txt1.setMargin(new Insets(0, 5, 0, 5));
        txt1.setVisible(false);
        panel.add(txt1);

        JTextField txt2 = new JTextField();
        txt2.setBounds(41, 144, 150, 30);
        txt2.setMargin(new Insets(0, 5, 0, 5));
        txt2.setVisible(false);
        panel.add(txt2);

        JCheckBox checkBox = new JCheckBox("");
        checkBox.setBounds(41, 181, 20, 22);
        panel.add(checkBox);

        JLabel lblMostrarMapa = new JLabel("Mostrar mapa?");
        lblMostrarMapa.setBounds(68, 181, 90, 22);
        panel.add(lblMostrarMapa);

        JButton btnEnter = new JButton("Enter");
        btnEnter.setBounds(68, 210, 95, 23);
        panel.add(btnEnter);

        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10,10,10,10));
        textArea.setEditable(false);
        panel.add(textArea);

        JTextArea textArea2 = new JTextArea();
        textArea2.setBounds(250, 254, 270, 230);
        textArea2.setMargin(new Insets(10,10,10,10));
        textArea2.setEditable(false);
        frame.getContentPane().add(textArea2);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(13, 244, 210, 230);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        JLabel lblResultados = new JLabel("0 resultado(s)");
        lblResultados.setBounds(17, 478, 120, 14);
        panel.add(lblResultados);
        lblResultados.setVisible(false);

        JMapFrame mainMapFrame = new JMapFrame();
        JMapFrame localMapFrame = new JMapFrame();

        JMapPane mainMapPane = mainMapFrame.getMapPane();
        mainMapPane.setBounds(250, 13, 270, 230);
        mainMapPane.setLayout(null);
        frame.add(mainMapPane);

        JMapPane localMapPane = localMapFrame.getMapPane();
        localMapPane.setBounds(250, 254, 270, 230);
        localMapPane.setLayout(null);
        frame.add(localMapPane);

        rdbtnMun.addItemListener(e -> configRdbtn1(txt1, txt2, textArea, textArea2, lblResultados, mainMapFrame, localMapFrame, localMapPane));

        rdbtnEst.addItemListener(e -> configRdbtn1(txt1, txt2, textArea, textArea2, lblResultados, mainMapFrame, localMapFrame, localMapPane));

        rdbtnPop.addItemListener(e -> configRdbtn2(txt1, txt2, textArea, textArea2, lblResultados, mainMapFrame, localMapFrame, localMapPane));

        rdbtnArea.addItemListener(e -> configRdbtn2(txt1, txt2, textArea, textArea2, lblResultados, mainMapFrame, localMapFrame, localMapPane));

        btnEnter.addActionListener(e -> {
            int valor1, valor2, nRows;
            String nome1, nome2, infoMunicipio, infoEstado, infoPopulacao, infoArea, text;
            MapContent mainMapContent, localMapContent;

            InfoService info = new InfoService();
            MapService map = new MapService();

            if (rdbtnMun.isSelected()) {
                nome1 = txt1.getText();
                try {
                    infoMunicipio = info.getInfoMunicipio(nome1);
                    textArea.setText(infoMunicipio);

                    nRows = info.getRowsMunicipio(nome1);
                    lblResultados.setText(nRows + " resultado(s)");

                    if (checkBox.isSelected() && nRows == 1){
                        mainMapContent = map.getMapMunicipio(nome1);
                        localMapContent = map.getLocalMapMunicipio(nome1);

                        resetMap(mainMapFrame, localMapFrame);
                        mainMapFrame.setMapContent(mainMapContent);
                        localMapFrame.setMapContent(localMapContent);

                        txt2.setVisible(false);
                        txt2.setText(null);
                    }
                    else if(checkBox.isSelected() && nRows > 1){
                        nome2 = txt2.getText();
                        txt2.setVisible(true);

                        resetMap(mainMapFrame, localMapFrame);

                        if(!nome2.isEmpty()) {
                            mainMapContent = map.getMapMunicipio(nome1, nome2);
                            localMapContent = map.getLocalMapMunicipio(nome1, nome2);

                            resetMap(mainMapFrame, localMapFrame);
                            mainMapFrame.setMapContent(mainMapContent);
                            localMapFrame.setMapContent(localMapContent);

                        }
                    } else {
                        resetData1(txt1, txt2, textArea, textArea2, lblResultados);
                        resetMap(mainMapFrame, localMapFrame);
                        textArea.setText("Sem dados");
                    }

                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (rdbtnEst.isSelected()) {
                nome1 = txt1.getText();
                try {
                    infoEstado = info.getInfoEstado(nome1);
                    textArea.setText(infoEstado);

                    nRows = info.getRowsEstado(nome1);
                    lblResultados.setText(nRows + " resultado(s)");

                    if (checkBox.isSelected()){
                        mainMapContent = map.getMapEstado(nome1);
                        localMapContent = map.getLocalMapEstado(nome1);

                        mainMapFrame.setMapContent(mainMapContent);
                        localMapFrame.setMapContent(localMapContent);
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (rdbtnPop.isSelected()) {
                valor1 = Integer.parseInt(txt1.getText());
                valor2 = Integer.parseInt(txt2.getText());
                try {
                    infoPopulacao = info.getInfoPopulacao(valor1, valor2);
                    textArea.setText(infoPopulacao);

                    nRows = info.getRowsPopulacao(valor1, valor2);
                    lblResultados.setText(nRows + " resultado(s)");

                    if(checkBox.isSelected()){
                        mainMapContent = map.getMapPopulacao(valor1, valor2);

                        mainMapFrame.setMapContent(mainMapContent);
                        localMapFrame.setMapContent(new MapContent());

                        text = info.getSumTotalPopulation("populacao", valor1, valor2) + "\n" + info.getSumTotalArea("populacao", valor1,valor2);
                        textArea2.setText(text);
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (rdbtnArea.isSelected()) {
                valor1 = Integer.parseInt(txt1.getText());
                valor2 = Integer.parseInt(txt2.getText());
                try {
                    infoArea = info.getInfoArea(valor1, valor2);
                    textArea.setText(infoArea);

                    nRows = info.getRowsArea(valor1, valor2);
                    lblResultados.setText(nRows + " resultado(s)");

                    if(checkBox.isSelected()) {
                        mainMapContent = map.getMapArea(valor1, valor2);

                        mainMapFrame.setMapContent(mainMapContent);
                        localMapFrame.setMapContent(new MapContent());

                        text = info.getSumTotalPopulation("area", valor1, valor2) + "\n" + info.getSumTotalArea("area", valor1,valor2);
                        textArea2.setText(text);
                    }
                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            textArea.setCaretPosition(0);
            lblResultados.setVisible(true);
        });
    }
}

