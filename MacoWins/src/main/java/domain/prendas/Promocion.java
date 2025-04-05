package domain.prendas;

public class Promocion extends Estado {
  private int descuento;

  public Promocion(int descuento) {
    this.descuento = descuento;
  }

  @Override
  public int precio(int precioBase) {
    return precioBase - descuento;
  }
}
