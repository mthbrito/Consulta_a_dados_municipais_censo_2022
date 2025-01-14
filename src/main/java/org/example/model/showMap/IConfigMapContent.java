package org.example.model.showMap;

import org.geotools.map.MapContent;

import java.util.List;

public interface IConfigMapContent {

    List<MapContent> configMapContent(int option, String search);

    List<MapContent> configMapContent(String search1, String search2);

    MapContent configMapContent(int option, int infLim, int supLim);

}
