package domain.ventas;

public class Tarjeta extends MetodoDePago {
    private int cantidadDeCuotas;
    private int coeficiente;

    public Tarjeta(int cantidadDeCuotas, int coeficiente) {
        this.cantidadDeCuotas = cantidadDeCuotas;
        this.coeficiente = coeficiente;
    }

    @Override
    public int precioFinal(int precio) {
        return (int) (precio + cantidadDeCuotas * coeficiente + precio * 0.01);
    }
}
