package org.example;

import org.example.service.MapService;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) throws SQLException, IOException {

//        Repository r = new Repository();
//        System.out.println(r.findDataMunicipioEstado("Recife", "VB"));

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 580, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        JMapFrame mapFrame = new JMapFrame();
        JMapPane mapPane = mapFrame.getMapPane();
        mapPane.setBounds(10, 13, 540, 460);
        mapPane.setLayout(null);
        frame.add(mapPane);
        mapPane.setMapContent(new MapService().getLocalMapMunicipio("Aracaju"));


        JProgressBar bar = new JProgressBar();
        bar.setBounds(10, 520, 100, 30);
        frame.getContentPane().add(bar);
        bar.setVisible(true);

    }
}
