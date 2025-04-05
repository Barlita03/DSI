package domain.ventas;

public class Efectivo extends MetodoDePago {
  @Override
  public int precioFinal(int precio) {
    return precio;
  }
}
