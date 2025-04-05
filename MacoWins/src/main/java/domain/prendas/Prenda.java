package domain.prendas;

public class Prenda {
  // Atributos
  private final int precioBase;
  private final Tipo tipo;
  private Estado estado;

  // Inicializador
  public Prenda(int precioBase, Tipo tipo, Estado estado) {
    this.precioBase = precioBase;
    this.tipo = tipo;
    this.estado = estado;
  }

  // Getters y setters
  public Tipo getTipo() {
    return this.tipo;
  }

  public void setEstado(Estado nuevoEstado) {
    this.estado = nuevoEstado;
  }

  // Metodos necesarios
  public int precioDeVenta() {
    return this.estado.precio(this.precioBase);
  }
}
