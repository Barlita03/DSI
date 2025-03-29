package domain.ventas;

import domain.prendas.Prenda;
import java.util.List;

public class Venta {
    private String fecha;
    private List<Prenda> prendas;
    private MetodoDePago metodoDePago;

    public Venta(String fecha, List<Prenda> prendas, MetodoDePago metodoDePago) {
        this.fecha = fecha;
        this.prendas = prendas;
        this.metodoDePago = metodoDePago;
    }

    public String getFecha() {
        return this.fecha;
    }

    private int precioTotal() {
        return this.prendas.stream().mapToInt( p -> p.precioDeVenta() ).sum();
    }

    public int ganancia() {
        return this.metodoDePago.precioFinal(this.precioTotal());
    }

    public int cantidadDePrendasVendidas() {
        return this.prendas.size();
    }
}
