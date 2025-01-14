package org.example.model.showMap;

import org.geotools.map.MapContent;
import org.geotools.swing.JMapFrame;

public interface IConfigMapFrame {

    void configMapFrame(JMapFrame mainMapFrame, JMapFrame localMapFrame, MapContent mainMapContent, MapContent localMapContent);

    void configMapFrame(JMapFrame mainMapFrame, JMapFrame localMapFrame, MapContent mainMapContent);

}
