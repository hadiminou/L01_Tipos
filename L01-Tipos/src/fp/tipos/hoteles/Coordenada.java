package fp.tipos.hoteles;

public record Coordenada(double latitud, double longitud) {
    public double getDistancia(Coordenada c) {
        double R = 6371.0;
        double lat1 = Math.toRadians(latitud);
        double lon1 = Math.toRadians(longitud);
        double lat2 = Math.toRadians(c.latitud());
        double lon2 = Math.toRadians(c.longitud());

        double Δlat = lat2 - lat1;
        double Δlong = lon2 - lon1;

        double a = Math.pow(Math.sin(Δlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(Δlong / 2), 2);

        double c1 = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c1;
    }
    
    public Double getDistancia2(Coordenada c) {
    	Double diferenciaLatitud = this.latitud - c.latitud();
    	Double diferenciaLongitud = this.longitud - c.longitud();
    	return Math.sqrt((diferenciaLatitud * diferenciaLatitud) +(
    	diferenciaLongitud * diferenciaLongitud));
    	}
    
    public Double getDistanciaHarversine(Coordenada c) {
    	Double r_Tierra = 6371.0;
    	Double lat1Rad = Math.toRadians(this.latitud);
    	Double lon1Rad = Math.toRadians(this.longitud);
    	Double lat2Rad = Math.toRadians(c.latitud());
    	Double lon2Rad = Math.toRadians(c.longitud());
    	Double lat_radianes = lat2Rad - lat1Rad;
    	Double lon_radianes = lon2Rad - lon1Rad;
    	Double a = Math.pow(Math.sin(lat_radianes / 2), 2) +
    	Math.cos(lat1Rad) * Math.cos(lat2Rad) *
    	Math.pow(Math.sin(lon_radianes / 2), 2);
    	Double c1 = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    	// Calcular la distancia utilizando la fórmula de Haversine
    	return r_Tierra * c1;
    	}


    @Override
    public String toString() {
        return "Coordenada [latitud=" + latitud + ", longitud=" + longitud + "]";
    }
}
