package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.prendas.Prenda;
import org.qmp.sugeridores.Sugeridor;

public class Usuario {
  private Sugeridor sugeridor;
  private List<Prenda> prendas;
  private int edad;

  // --- Constructor ---

  public Usuario(int edad, Sugeridor sugeridor, List<Prenda> prendas) {
    this.prendas = new ArrayList<Prenda>(prendas);
    this.sugeridor = sugeridor;
    this.edad = edad;
  }

  public Usuario(int edad, Sugeridor sugeridor, Prenda... prendas) {
    this.prendas = new ArrayList<Prenda>(List.of(prendas));
    this.sugeridor = sugeridor;
    this.edad = edad;
  }

  public Usuario(int edad, Sugeridor sugeridor) {
    this.prendas = new ArrayList<>();
    this.sugeridor = sugeridor;
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

  public List<Atuendo> generarSugerencias() {
    return this.sugeridor.generarSugerencias(this);
  }

  public void adquirirPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void desecharPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }

  // --- Setters ---

  public void setSugeridor(Sugeridor sugeridor) {
    this.sugeridor = sugeridor;
  }
}
