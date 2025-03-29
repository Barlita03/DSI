package domain;

import domain.ventas.Venta;
import java.util.List;

public class MacoWins {
    private List<Venta> ventas;

    public MacoWins(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public int gananciasDeUnDia(String fecha) {
        return this.ventas.stream().filter( v -> v.getFecha().equals(fecha) ).mapToInt(v -> v.ganancia() ).sum();
    }
}
