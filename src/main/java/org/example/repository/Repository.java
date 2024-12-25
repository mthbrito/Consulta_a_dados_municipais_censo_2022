package org.example.repository;

import org.example.model.City;

import java.sql.*;
import java.util.List;

public class Repository extends FindData {

    private Connection con;

    public Repository() {
        try {
            String url = "jdbc:postgresql://localhost:5432/censo2022";
            String user = "postgres";
            String password = "0000";
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<City> findDataMunicipio(String municipio) throws SQLException {
        String sql = "select municipio, estado, populacao, area, ST_AsGeoJSON(geom) as geometry from municipios_censo2022 where municipio = ? order by estado asc";
        return findString(con, sql, municipio);
    }

    public List<City> findDataEstado(String estado) throws SQLException {
        String sql = "select municipio, estado, populacao, area, ST_AsGeoJSON(geom) as geometry from municipios_censo2022 where estado = ? order by municipio";
        return findString(con, sql, estado);
    }

    public List<City> findDataPopulacao(int infLim, int supLim) throws SQLException {
        String sql = "select municipio, estado, populacao, area, ST_AsGeoJSON(geom) as geometry from municipios_censo2022 where populacao > ? and populacao < ? order by municipio, estado asc";
        return findInt(con, sql, infLim, supLim);
    }

    public List<City> findDataArea(int infLim, int supLim) throws SQLException {
        String sql = "select municipio, estado, populacao, area, ST_AsGeoJSON(geom) as geometry from municipios_censo2022 where area > ? and area < ? order by municipio, estado asc";
        return findInt(con, sql, infLim, supLim);
    }

    public City findDataMunicipioEstado(String municipio, String estado) throws SQLException {
        String sql = "select municipio, estado, populacao, area, ST_AsGeoJSON(geom) as geometry from municipios_censo2022 where municipio = ? and estado = ?";
        return findString(con, sql, municipio, estado);
    }

    public List<String> findGeometryEstado(String estado) throws SQLException {
        String sql = "select ST_AsGeoJSON(geometry) as geometry from br_uf where sigla_uf = ?";
        return findGeometryData(con, sql, estado);
    }

    public List<String> findGeometryEstados() throws SQLException {
        String sql = "select ST_AsGeoJSON(geometry) as geometry from br_uf";
        return findGeometryData(con, sql);
    }

    public int countRowsMunicipio(String municipio) throws SQLException {
        String sql = "select count(municipio) as countRows from municipios_censo2022 where municipio = ?";
        return countRowsString(con, sql, municipio);
    }

    public int countRowsEstado(String estado) throws SQLException {
        String sql = "select count(municipio) as countRows from municipios_censo2022 where estado = ?";
        return countRowsString(con, sql, estado);
    }

    public int countRowsPopulacao(int infLim, int supLim) throws SQLException {
        String sql = "select count(municipio) as countRows from municipios_censo2022 where populacao > ? and populacao < ?";
        return countRowsInt(con, sql, infLim, supLim);
    }

    public int countRowsArea(int infLim, int supLim) throws SQLException {
        String sql = "select count(municipio) as countRows from municipios_censo2022 where area > ? and area < ?";
        return countRowsInt(con, sql, infLim, supLim);
    }

    public int sumTotalPopulation(String search, int infLim, int supLim) throws SQLException {
        String sql = "";
        if(search.equals("populacao")) {
            sql = "select sum(populacao) as sumRows from municipios_censo2022 where populacao > ? and populacao < ?";
            return sumRowsInt(con, sql, infLim, supLim);
        } else if (search.equals("area")) {
            sql = "select sum(populacao) as sumRows from municipios_censo2022 where area > ? and area < ?";
            return sumRowsInt(con, sql, infLim, supLim);
        }
        return 0;
    }

    public int sumBrazilPopulation() throws SQLException {
        String sql = "select sum(populacao) as sumRows from municipios_censo2022";
        return sumRowsInt(con, sql);
    }

    public int sumTotalArea(String search, int infLim, int supLim) throws SQLException {
        String sql = "";
        if(search.equals("populacao")) {
            sql = "select sum(area) as sumRows from municipios_censo2022 where populacao > ? and populacao < ?";
            return sumRowsInt(con, sql, infLim, supLim);
        } else if (search.equals("area")) {
            sql = "select sum(area) as sumRows from municipios_censo2022 where area > ? and area < ?";
            return sumRowsInt(con, sql, infLim, supLim);
        }
        return 0;
    }

    public int sumBrazilArea() throws SQLException {
        String sql = "select sum(area) as sumRows from municipios_censo2022";
        return sumRowsInt(con, sql);
    }

}
