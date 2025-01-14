package org.example.view;

import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import javax.swing.*;
import java.awt.*;

import static org.example.service.ViewService.*;

public class WindowView {
    private JFrame frame;

    /**
     * Create the application.
     */
    public WindowView() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WindowView window = new WindowView();
                window.frame.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
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

        ImageIcon icon = new ImageIcon("src/main/java/org/example/view/images/logotipo-ibge.png");
        frame.setIconImage(icon.getImage());

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
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        panel.add(textArea);

        JTextArea textArea2 = new JTextArea();
        textArea2.setBounds(250, 254, 270, 230);
        textArea2.setMargin(new Insets(10, 10, 10, 10));
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

        btnEnter.addActionListener(e -> configBtnEnter(rdbtnMun, rdbtnEst, rdbtnPop, rdbtnArea, checkBox, txt1, txt2, textArea, textArea2, lblResultados, mainMapFrame, localMapFrame));

    }
}

