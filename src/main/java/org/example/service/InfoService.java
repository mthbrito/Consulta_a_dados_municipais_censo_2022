package org.example.service;

import org.example.model.City;
import org.example.repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class InfoService extends Repository {

    public String getInfoMunicipio(String municipio) throws SQLException {
        List<City> search = findDataMunicipio(municipio);
        return getInfo(search);
    }

    public String getInfoEstado(String estado) throws SQLException {
        List<City> search = findDataEstado(estado);
        return getInfo(search);
    }

    public String getInfoPopulacao(int infLim, int supLim) throws SQLException {
        List<City> search = findDataPopulacao(infLim, supLim);
        return getInfo(search);
    }

    public String getInfoArea(int infLim, int supLim) throws SQLException {
        List<City> search = findDataArea(infLim, supLim);
        return getInfo(search);
    }

    public String getInfo(List<City> search){
        StringBuilder sb = new StringBuilder();
        for (City c : search) {
            sb.append(c.toString()) ;
        }
        return String.valueOf(sb);
    }

    public int getRowsMunicipio(String municipio) throws SQLException {
        return countRowsMunicipio(municipio);
    }

    public int getRowsEstado(String estado) throws SQLException {
        return countRowsEstado(estado);
    }

    public int getRowsPopulacao(int infLim, int supLim) throws SQLException {
        return countRowsPopulacao(infLim, supLim);
    }

    public int getRowsArea(int infLim, int supLim) throws SQLException {
        return countRowsArea(infLim, supLim);
    }

    public String getSumTotalPopulation(String search, int infLim, int supLim) throws SQLException {
        int total = sumTotalPopulation(search, infLim, supLim);
        return "População total: " + total + " (" + String.format("%.2f", (((double)total/(double)sumBrazilPopulation())*100)) + "%) ";
    }

    public String getSumTotalArea(String search, int infLim, int supLim) throws SQLException {
        int total = sumTotalArea(search, infLim, supLim);
        return "Área total: " + total + " (" + String.format("%.2f", (((double)total/(double)sumBrazilArea())*100)) + "%) ";
    }
}
