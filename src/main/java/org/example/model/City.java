package org.example.model;

public class City {

    private String municipio;
    private String estado;
    private int populacao;
    private double area;
    private String geometry;

    public City() {
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "Município: " + municipio + "\n" +
                "Estado: " + estado + "\n" +
                "População: " + populacao + "\n" +
                "Área: " + area + "\n\n";
    }
}
