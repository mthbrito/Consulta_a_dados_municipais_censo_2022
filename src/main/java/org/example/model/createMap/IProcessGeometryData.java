package org.example.model.createMap;

import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiPolygon;

import java.io.IOException;
import java.util.List;

public interface IProcessGeometryData {

    MultiPolygon processGeometryData(String geometry) throws IOException;

    GeometryCollection processGeometryData(List<String> geometries) throws IOException;

}
