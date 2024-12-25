package org.example.model;

import java.util.List;

public interface IGetGeometryData {

    String getGeometryData(City search);
    List<String> getGeometryData(List<City> search);

}
