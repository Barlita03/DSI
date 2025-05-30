package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.prendas.Prenda;
import org.qmp.sugeridores.Sugeridor;

public class Usuario {
  List<Prenda> prendas = new ArrayList<>();
  int edad;

  // --- Constructor ---

  public Usuario(int edad, List<Prenda> prendas) {
    this.prendas = new ArrayList<Prenda>(prendas);
    this.edad = edad;
  }

  // --- Getters ----

  public List<Prenda> getPrendas() {
    return new ArrayList<Prenda>(this.prendas);
  }

  public List<Prenda> getPrendasSuperiores() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esSuperior).toList());
  }

  public List<Prenda> getPrendasInferiores() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esInferior).toList());
  }

  public List<Prenda> getCalzados() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esCalzado).toList());
  }

  public int getEdad() {
    return this.edad;
  }

  // --- Metodos ---

  public List<Atuendo> generarSugerencias(Sugeridor sugeridor) {
    return sugeridor.generarSugerencias(this);
  }

  public void adquirirPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void desecharPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }
}
