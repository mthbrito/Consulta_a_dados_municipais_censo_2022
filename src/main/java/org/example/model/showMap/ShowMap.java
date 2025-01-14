package org.example.model.showMap;

import org.example.model.showInfo.ShowInfo;
import org.example.service.InfoService;
import org.example.service.MapService;
import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.example.service.ViewService.resetMap;

public class ShowMap extends ShowInfo implements IConfigMapContent, IConfigMapFrame, IConfigAdditionalTextField, IConfigAdditionalTextArea, IShowMapResult {

    private static boolean verifyLayers(List<MapContent> maps) {
        return !maps.getFirst().layers().getFirst().getBounds().isEmpty() || !maps.getFirst().layers().getLast().getBounds().isEmpty();
    }

    public static void showMap(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JMapFrame mainMapFrame, JMapFrame localMapFrame) {
        new ShowMap().showMapResult(option, txt1, txt2, textArea, textArea2, mainMapFrame, localMapFrame);
    }

    @Override
    public List<MapContent> configMapContent(int option, String search) {
        MapContent mainMapContent = new MapContent(), localMapContent = new MapContent();
        switch (option) {
            case 1:
                try {
                    mainMapContent = new MapService().getMapMunicipio(search);
                    localMapContent = new MapService().getLocalMapMunicipio(search);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    mainMapContent = new MapService().getMapEstado(search);
                    localMapContent = new MapService().getLocalMapEstado(search);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return Arrays.asList(mainMapContent, localMapContent);
    }

    @Override
    public List<MapContent> configMapContent(String search1, String search2) {
        MapContent mainMapContent, localMapContent;
        try {
            mainMapContent = new MapService().getMapMunicipio(search1, search2);
            localMapContent = new MapService().getLocalMapMunicipio(search1, search2);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(mainMapContent, localMapContent);
    }

    @Override
    public MapContent configMapContent(int option, int infLim, int supLim) {
        MapContent mainMapContent = new MapContent();
        switch (option) {
            case 3:
                try {
                    mainMapContent = new MapService().getMapPopulacao(infLim, supLim);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    mainMapContent = new MapService().getMapArea(infLim, supLim);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return mainMapContent;
    }

    @Override
    public void configMapFrame(JMapFrame mainMapFrame, JMapFrame localMapFrame, MapContent mainMapContent, MapContent localMapContent) {
        resetMap(mainMapFrame, localMapFrame);
        mainMapFrame.setMapContent(mainMapContent);
        localMapFrame.setMapContent(localMapContent);
    }

    @Override
    public void configMapFrame(JMapFrame mainMapFrame, JMapFrame localMapFrame, MapContent mainMapContent) {
        resetMap(mainMapFrame, localMapFrame);
        mainMapFrame.setMapContent(mainMapContent);
    }

    @Override
    public void configAdditionalTextField(boolean visible, JTextField txt2) {
        if (visible) {
            txt2.setVisible(true);
        } else {
            txt2.setText(null);
            txt2.setVisible(false);
        }
    }

    @Override
    public void configAdditionalTextArea(int option, int value1, int value2, JTextArea textArea, JTextArea textArea2) {
        String text;
        switch (option) {
            case 3:
                try {
                    int total = new InfoService().sumTotalPopulation("populacao", value1, value2);
                    if (total == 0) {
                        textArea.setText("Sem dados");
                        textArea2.setText(null);
                    } else {
                        text = new InfoService().getSumTotalPopulation("populacao", value1, value2) + "\n" + new InfoService().getSumTotalArea("populacao", value1, value2);
                        textArea2.setText(text);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    int total = new InfoService().sumTotalArea("area", value1, value2);
                    if (total == 0) {
                        textArea.setText("Sem dados");
                        textArea2.setText(null);
                    } else {
                        text = new InfoService().getSumTotalPopulation("area", value1, value2) + "\n" + new InfoService().getSumTotalArea("area", value1, value2);
                        textArea2.setText(text);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    public void showMapResult(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JTextArea textArea2, JMapFrame mainMapFrame, JMapFrame localMapFrame) {
        String search1 = txt1.getText();
        String search2;
        int nRows;
        List<MapContent> maps;
        List<Integer> values = checkInput(Arrays.asList(txt1.getText(), txt2.getText()));
        MapContent map;

        try {
            nRows = new InfoService().countRowsMunicipio(search1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        switch (option) {
            case 1:
                if (nRows == 1) {
                    configAdditionalTextField(false, txt2);
                    maps = configMapContent(1, search1);
                    configMapFrame(mainMapFrame, localMapFrame, maps.getFirst(), maps.getLast());
                } else if (nRows > 1) {
                    configAdditionalTextField(true, txt2);
                    resetMap(mainMapFrame, localMapFrame);
                    if (!txt2.getText().isEmpty()) {
                        search2 = txt2.getText();
                        maps = configMapContent(search1, search2);
                        if (verifyLayers(maps)) {
                            configMapFrame(mainMapFrame, localMapFrame, maps.getFirst(), maps.getLast());
                        }
                    }
                } else {
                    configAdditionalTextField(false, txt2);
                    configMapFrame(mainMapFrame, localMapFrame, new MapContent(), new MapContent());
                }
                break;
            case 2:
                configAdditionalTextField(false, txt2);
                maps = configMapContent(2, search1);
                configMapFrame(mainMapFrame, localMapFrame, maps.getFirst(), maps.getLast());
                break;
            case 3:
                map = configMapContent(3, values.getFirst(), values.getLast());
                configMapFrame(mainMapFrame, localMapFrame, map);
                configAdditionalTextArea(3, values.getFirst(), values.getLast(), textArea, textArea2);
                break;
            case 4:
                map = configMapContent(4, values.getFirst(), values.getLast());
                configMapFrame(mainMapFrame, localMapFrame, map);
                configAdditionalTextArea(4, values.getFirst(), values.getLast(), textArea, textArea2);
        }
    }

}
