package org.example.model.createMap;

import org.geotools.map.Layer;
import org.geotools.map.MapContent;

import java.util.List;

public interface ICreateMapContent {

    MapContent createMapContent(Layer layer);
    MapContent createMapContent(List<Layer> layers);

}
