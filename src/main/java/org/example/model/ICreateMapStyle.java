package org.example.model;

import org.geotools.api.style.Style;

import java.awt.*;

public interface ICreateMapStyle {

    Style createMapStyle(Color fillColor, double fillOpacity, Color strokeColor, double strokeWidth);

}
