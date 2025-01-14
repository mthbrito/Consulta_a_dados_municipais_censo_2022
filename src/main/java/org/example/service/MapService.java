package org.example.service;

import org.example.model.City;
import org.example.model.createMap.CreateMap;
import org.example.repository.Repository;
import org.geotools.api.style.Style;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiPolygon;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MapService extends CreateMap {

    private final Repository r = new Repository();

    public Layer getMapLayerMunicipio(String municipio) throws SQLException, IOException {
        List<City> search = r.findDataMunicipio(municipio);
        Style style = createMapStyle(Color.RED, 0.7, Color.BLACK, 0.8);
        if (search.size() == 1) {
            String geometryData = getGeometryData(search.getFirst());
            MultiPolygon multiPolygons = processGeometryData(geometryData);
            return createMapLayer(multiPolygons, style);
        } else {
            List<String> geometryData = getGeometryData(search);
            GeometryCollection geometryCollection = processGeometryData(geometryData);
            return createMapLayer(geometryCollection, style);
        }
    }

    public Layer getMapLayerMunicipio(String municipio, String estado) throws SQLException, IOException {
        City search = r.findDataMunicipioEstado(municipio, estado);
        if (search.getMunicipio() != null) {
            Style style = createMapStyle(Color.RED, 0.7, Color.BLACK, 0.8);
            String geometryData = getGeometryData(search);
            MultiPolygon multiPolygons = processGeometryData(geometryData);
            return createMapLayer(multiPolygons, style);
        } else {
            return defaultLayer();
        }
    }

    public Layer getMapLayerEstado(String estado) throws SQLException, IOException {
        List<City> search = r.findDataEstado(estado);
        List<String> geometryData = getGeometryData(search);
        GeometryCollection geometryCollection = processGeometryData(geometryData);
        Style style = createMapStyle(Color.GREEN, 0.7, Color.BLACK, 0.2);
        return createMapLayer(geometryCollection, style);
    }

    public Layer getMapLayerPopulacao(int infLim, int supLim) throws SQLException, IOException {
        List<City> search = r.findDataPopulacao(infLim, supLim);
        List<String> geometryData = getGeometryData(search);
        GeometryCollection geometryCollection = processGeometryData(geometryData);
        Style style = createMapStyle(Color.RED, 0.7, Color.BLACK, 0.8);
        return createMapLayer(geometryCollection, style);
    }

    public Layer getMapLayerArea(int infLim, int supLim) throws SQLException, IOException {
        List<City> search = r.findDataArea(infLim, supLim);
        List<String> geometryData = getGeometryData(search);
        GeometryCollection geometryCollection = processGeometryData(geometryData);
        Style style = createMapStyle(Color.YELLOW, 0.7, Color.BLACK, 0.8);
        return createMapLayer(geometryCollection, style);
    }

    public Layer getMapLayerOnlyEstado(String estado) throws SQLException, IOException {
        List<String> geometryData = r.findGeometryEstado(estado);
        GeometryCollection geometryCollection = processGeometryData(geometryData);
        Style style = createMapStyle(Color.GREEN, 0.7, Color.BLACK, 0.2);
        return createMapLayer(geometryCollection, style);
    }

    public Layer getMapLayerEstados() throws SQLException, IOException {
        List<String> geometryData = r.findGeometryEstados();
        GeometryCollection geometryCollection = processGeometryData(geometryData);
        Style style = createMapStyle(Color.WHITE, 0.7, Color.BLACK, 0.2);
        return createMapLayer(geometryCollection, style);
    }

    public Layer defaultLayer() {
        return new Layer() {
            @Override
            public ReferencedEnvelope getBounds() {
                return new ReferencedEnvelope();
            }
        };
    }

    public MapContent getMapMunicipio(String municipio) throws SQLException, IOException {
        Layer layerMunicipio = getMapLayerMunicipio(municipio);
        return createMapContent(layerMunicipio);
    }

    public MapContent getMapMunicipio(String municipio, String estado) throws SQLException, IOException {
        Layer layerMunicipio = getMapLayerMunicipio(municipio, estado);
        if (layerMunicipio != null) {
            return createMapContent(layerMunicipio);
        } else {
            return new MapContent();
        }
    }

    public MapContent getLocalMapMunicipio(String municipio) throws SQLException, IOException {
        String estado = !r.findDataMunicipio(municipio).isEmpty() ? r.findDataMunicipio(municipio).getFirst().getEstado() : "";
        Layer layerMunicipio = getMapLayerMunicipio(municipio);
        Layer layerEstado = getMapLayerEstado(estado);
        List<Layer> layers = Arrays.asList(layerEstado, layerMunicipio);
        return createMapContent(layers);
    }

    public MapContent getLocalMapMunicipio(String municipio, String estado) throws SQLException, IOException {
        Layer layerMunicipio = getMapLayerMunicipio(municipio, estado);
        Layer layerEstado = getMapLayerEstado(estado);
        List<Layer> layers = Arrays.asList(layerEstado, layerMunicipio);
        return createMapContent(layers);
    }

    public MapContent getMapEstado(String estado) throws SQLException, IOException {
        Layer layerEstado = getMapLayerEstado(estado);
        return createMapContent(layerEstado);
    }

    public MapContent getLocalMapEstado(String estado) throws SQLException, IOException {
        if (!r.findDataEstado(estado).isEmpty()) {
            Layer layerEstado = getMapLayerOnlyEstado(estado);
            Layer layerEstados = getMapLayerEstados();
            List<Layer> layers = Arrays.asList(layerEstados, layerEstado);
            return createMapContent(layers);
        } else {
            return new MapContent();
        }
    }

    public MapContent getMapPopulacao(int infLim, int supLim) throws SQLException, IOException {
        if (!r.findDataPopulacao(infLim, supLim).isEmpty()) {
            Layer layerPopulacao = getMapLayerPopulacao(infLim, supLim);
            Layer layerEstados = getMapLayerEstados();
            List<Layer> layers = Arrays.asList(layerEstados, layerPopulacao);
            return createMapContent(layers);
        } else {
            return new MapContent();
        }
    }

    public MapContent getMapArea(int infLim, int supLim) throws SQLException, IOException {
        if (!r.findDataArea(infLim, supLim).isEmpty()) {
            Layer layerArea = getMapLayerArea(infLim, supLim);
            Layer layerEstados = getMapLayerEstados();
            List<Layer> layers = Arrays.asList(layerEstados, layerArea);
            return createMapContent(layers);
        } else {
            return new MapContent();
        }
    }
}
