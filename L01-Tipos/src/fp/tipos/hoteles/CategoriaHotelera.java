package fp.tipos.hoteles;

public enum CategoriaHotelera {
    UNA(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), OTROS(0);

    private final int estrellas;

    CategoriaHotelera(int estrellas) {
        this.estrellas = estrellas;
    }

    public int estrellas() {
        return estrellas;
    }
}