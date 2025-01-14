package org.example.repository;

import org.example.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class FindData {

    public List<City> findString(Connection con, String sql, String search) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, search);
        return findData(st);
    }

    public City findString(Connection con, String sql, String search1, String search2) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, search1);
        st.setString(2, search2);
        ResultSet rs = st.executeQuery();
        City mun = new City();
        if (rs.next()) {
            mun.setMunicipio(rs.getString("municipio"));
            mun.setEstado(rs.getString("estado"));
            mun.setPopulacao(rs.getInt("populacao"));
            mun.setArea(rs.getDouble("area"));
            mun.setGeometry(rs.getString("geometry"));
        }
        return mun;
    }

    public List<City> findInt(Connection con, String sql, int infLim, int supLim) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, infLim);
        st.setInt(2, supLim);
        return findData(st);
    }

    private List<City> findData(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<City> search = new ArrayList<>();
        while (rs.next()) {
            City mun = new City();
            mun.setMunicipio(rs.getString("municipio"));
            mun.setEstado(rs.getString("estado"));
            mun.setPopulacao(rs.getInt("populacao"));
            mun.setArea(rs.getDouble("area"));
            mun.setGeometry(rs.getString("geometry"));
            search.add(mun);
        }
        return search;
    }

    public List<String> findGeometryData(Connection con, String sql, String busca) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, busca);
        return findGeometry(st);
    }

    public List<String> findGeometryData(Connection con, String sql) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        return findGeometry(st);
    }

    public List<String> findGeometry(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<String> search = new ArrayList<>();
        while (rs.next()) {
            search.add(rs.getString("geometry"));
        }
        return search;
    }

    public int countRowsString(Connection con, String sql, String search) throws SQLException {
        int num = 0;
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, search);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            num = rs.getInt("countRows");
        }
        return num;
    }

    public int countRowsInt(Connection con, String sql, int infLim, int supLim) throws SQLException {
        int num = 0;
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, infLim);
        st.setInt(2, supLim);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            num = rs.getInt("countRows");
        }
        return num;
    }

    public int sumRowsInt(Connection con, String sql, int infLim, int supLim) throws SQLException {
        int num = 0;
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, infLim);
        st.setInt(2, supLim);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            num = rs.getInt("sumRows");
        }
        return num;
    }

    public int sumRowsInt(Connection con, String sql) throws SQLException {
        int num = 0;
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            num = rs.getInt("sumRows");
        }
        return num;
    }
}
