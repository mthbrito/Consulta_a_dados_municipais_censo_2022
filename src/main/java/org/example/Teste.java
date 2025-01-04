package org.example;

import org.example.repository.Repository;

import java.io.IOException;

import java.sql.SQLException;



public class Teste {

    public static void main(String[] args) throws SQLException, IOException {

//        JFrame frame = new JFrame();
//        frame.setBounds(0, 0, 560, 700);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Consulta a dados municipais (Censo 2022)");
//        frame.getContentPane().setLayout(null);
//        frame.setVisible(true);
//
//        String mun = "Sorocaba";
//        String es = "SP";
//
//        MapContent map = new MapService().getMapMunicipio(mun, es);
//        JMapFrame mainFrame = new JMapFrame(map);
//        JMapPane mainPane = mainFrame.getMapPane();
//        mainPane.setBounds(17, 17, 200, 200);
//        mainPane.setVisible(true);
//        frame.add(mainPane);
//
//        MapContent map2 = new MapService().getLocalMapMunicipio(mun, es);
//        JMapFrame mainFrame2 = new JMapFrame(map2);
//        JMapPane mainPane2 = mainFrame2.getMapPane();
//        mainPane2.setBounds(17, 234, 400, 400);
//        mainPane2.setVisible(true);
//        frame.add(mainPane2);

        Repository r =  new Repository();
        System.out.println(r.findDataMunicipioEstado("Recife", "VB"));



    }
}
