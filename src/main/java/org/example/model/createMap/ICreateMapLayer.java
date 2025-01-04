package org.example.model.createMap;

import org.geotools.api.style.Style;
import org.geotools.map.Layer;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiPolygon;

public interface ICreateMapLayer {

    Layer createMapLayer(MultiPolygon multiPolygons, Style style);
    Layer createMapLayer(GeometryCollection geometryCollection, Style style);

}
