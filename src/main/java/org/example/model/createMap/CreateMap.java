package org.example.model.createMap;

import org.example.model.City;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.style.Fill;
import org.geotools.api.style.Stroke;
import org.geotools.api.style.Style;
import org.geotools.data.collection.CollectionFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.StyleBuilder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CreateMap implements IGetGeometryData, IProcessGeometryData, ICreateMapLayer, ICreateMapStyle, ICreateMapContent{

    @Override
    public String getGeometryData(City search) {
        return search.getGeometry();
    }

    @Override
    public List<String> getGeometryData(List<City> search) {
        List<String> geometries = new ArrayList<>();
        for(City city : search) {
            geometries.add(city.getGeometry());
        }
        return geometries;
    }

    @Override
    public MultiPolygon processGeometryData(String geometry) throws IOException {
        return new GeometryJSON().readMultiPolygon(geometry);
    }

    @Override
    public GeometryCollection processGeometryData(List<String> geometries) throws IOException {
        List<Geometry> geometriesList = new ArrayList<>();
        for(String geometry: geometries) {
            geometriesList.add(new GeometryJSON().readMultiPolygon(geometry));
        }
        Geometry[] geometryArray = geometriesList.toArray(new Geometry[0]);
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createGeometryCollection(geometryArray);
    }

    @Override
    public Layer createMapLayer(MultiPolygon multiPolygons, Style style) {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("MultiPolygonLayer");
        builder.add("geometry", MultiPolygon.class);
        SimpleFeatureType featureType = builder.buildFeatureType();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
        featureBuilder.add(multiPolygons);
        SimpleFeature feature = featureBuilder.buildFeature(null);
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(feature);
        return new FeatureLayer(new CollectionFeatureSource(collection), style);
    }

    @Override
    public Layer createMapLayer(GeometryCollection geometryCollection, Style style) {
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setName("GeometryCollectionLayer");
        builder.add("geometry", GeometryCollection.class);
        SimpleFeatureType featureType = builder.buildFeatureType();
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
        featureBuilder.add(geometryCollection);
        SimpleFeature feature = featureBuilder.buildFeature(null);
        DefaultFeatureCollection collection = new DefaultFeatureCollection();
        collection.add(feature);
        return new FeatureLayer(new CollectionFeatureSource(collection), style);
    }

    @Override
    public Style createMapStyle(Color fillColor, double fillOpacity, Color strokeColor, double strokeWidth) {
        StyleBuilder styleBuilder = new StyleBuilder();
        Fill fill = styleBuilder.createFill(fillColor, fillOpacity);
        Stroke stroke = styleBuilder.createStroke(strokeColor, strokeWidth);
        return styleBuilder.createStyle(styleBuilder.createPolygonSymbolizer(stroke, fill));
    }

    @Override
    public MapContent createMapContent(Layer layer) {
        MapContent mapContent = new MapContent();
        mapContent.addLayer(layer);
        adjustZoomLayer(mapContent, layer);
        return mapContent;
    }

    @Override
    public MapContent createMapContent(List<Layer> layers) {
        MapContent mapContent = new MapContent();
        for(Layer layer: layers) {
            mapContent.addLayer(layer);
        }
        adjustZoomLayer(mapContent, layers.getFirst());
        return mapContent;
    }

    private ReferencedEnvelope setNewBounds(ReferencedEnvelope bounds){
        return new ReferencedEnvelope(
                bounds.getMinX() - (bounds.getWidth() * 0.05),
                bounds.getMaxX() + (bounds.getWidth() * 0.05),
                bounds.getMinY() - (bounds.getHeight() * 0.05),
                bounds.getMaxY() + (bounds.getHeight() * 0.05),
                bounds.getCoordinateReferenceSystem()
        );
    }

    private void adjustZoomLayer (MapContent map, Layer layer) {
        ReferencedEnvelope bounds = layer.getBounds();
        ReferencedEnvelope newBounds = setNewBounds(bounds);
        map.getViewport().setBounds(newBounds);
    }
}
