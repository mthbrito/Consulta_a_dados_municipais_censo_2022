package org.example.model.createMap;

import org.example.model.City;

import java.util.List;

public interface IGetGeometryData {

    String getGeometryData(City search);
    List<String> getGeometryData(List<City> search);

}
