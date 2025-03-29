package domain.prendas;

public class Liquidacion extends Estado {
    @Override
    public int precio(int precioBase) {
        return precioBase / 2;
    }
}
