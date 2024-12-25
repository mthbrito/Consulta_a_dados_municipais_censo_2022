package org.example;

import org.example.service.MapService;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Teste {

    public static void main(String[] args) throws SQLException, IOException {

        String mun = "Monteiro";
        String es = "PB";

        MapService s = new MapService();
        MapContent map = s.getMapMunicipio(mun);
        ReferencedEnvelope bounds = map.layers().getFirst().getBounds();

        MapContent map2 = s.getLocalMapMunicipio(mun);
        ReferencedEnvelope bounds2 = map2.layers().getFirst().getBounds();
//        System.out.println(map.layers());

        MapContent map3 = s.getLocalMapEstado("PI");
//        System.out.println(map3.layers());
//        System.out.println(map3.layers().get(1).getBounds());

        JFrame frame = new JFrame();
        frame.setTitle("Consulta a dados municipais (Censo 2022)");
        frame.setBounds(100, 100, 900, 610);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JMapFrame mainMap = new JMapFrame();
        JMapFrame localMap = new JMapFrame();

        JMapFrame mf3 = new JMapFrame();


        JPanel panel = new JPanel(null);
        panel.setBounds(10, 11, 415, 240);
        frame.getContentPane().add(panel);


        JMapPane mapPane = mainMap.getMapPane();

        mapPane.setMapContent(map);
        mapPane.setBounds(27, 250, 380, 300);
        frame.getContentPane().add(mapPane);

        JMapPane mp3 = mf3.getMapPane();

        mf3.setMapContent(map3);
        mp3.setBounds(27, 27, 220, 220);
        frame.getContentPane().add(mp3);



        JMapPane mapPane2 = localMap.getMapPane();
//        s.setMapNewBounds(map2, mapPane2);
        mapPane2.setMapContent(map2);
        mapPane2.setBounds(420, 250, 380, 300);
        mapPane2.setLayout(null);
        frame.getContentPane().add(mapPane2);
        mapPane.setVisible(true);
        mapPane2.setVisible(true);

        frame.setVisible(true);

//        System.out.println(new InfoService().getSumTotalPopulation(1000000, 20000000));
//        System.out.println(new InfoService().getSumTotalArea(0, 1000));

    }

    public static void setMapNewBounds(MapContent map, JMapPane mapPane) {
        ReferencedEnvelope bounds = map.layers().getFirst().getBounds();
        ReferencedEnvelope newBounds = setNewBounds(bounds);
        mapPane.setDisplayArea(newBounds);
    }
    public static ReferencedEnvelope setNewBounds(ReferencedEnvelope bounds){
        return new ReferencedEnvelope(
                bounds.getMinX() - (bounds.getWidth() * 0.05),
                bounds.getMaxX() + (bounds.getWidth() * 0.05),
                bounds.getMinY() - (bounds.getHeight() * 0.05),
                bounds.getMaxY() + (bounds.getHeight() * 0.05),
                bounds.getCoordinateReferenceSystem()
        );
    }
}
