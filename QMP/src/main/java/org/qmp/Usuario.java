package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.operaciones.Sugerencia;
import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Prenda;
import org.qmp.sugeridores.Sugeridor;

public class Usuario {
  private Sugeridor sugeridor;
  private final List<Guardarropa> guardarropas = new ArrayList<>();
  private final int edad;

  // --- Constructor ---

  public Usuario(int edad, Sugeridor sugeridor) {
    this.sugeridor = sugeridor;
    this.edad = edad;
  }

  // --- Getters ----

  public List<Prenda> getPrendas() {
    return guardarropas.stream().flatMap(g -> g.getPrendas().stream()).toList();
  }

  public List<Prenda> getPrendasSuperiores() {
    return guardarropas.stream().flatMap(g -> g.getPrendasSuperiores().stream()).toList();
  }

  public List<Prenda> getPrendasInferiores() {
    return guardarropas.stream().flatMap(g -> g.getPrendasInferiores().stream()).toList();
  }

  public List<Prenda> getCalzados() {
    return guardarropas.stream().flatMap(g -> g.getCalzados().stream()).toList();
  }

  public List<Prenda> getAccesorios() {
    return guardarropas.stream().flatMap(g -> g.getAccesorios().stream()).toList();
  }

  public int getEdad() {
    return this.edad;
  }

  public List<Sugerencia> getSugerenciasPendientes() {
    return guardarropas.stream().flatMap(g -> g.getSugerenciasPendientes().stream()).toList();
  }

  public List<Sugerencia> getSugerenciasProcesadas() {
    return guardarropas.stream().flatMap(g -> g.getSugerenciasProcesadas().stream()).toList();
  }

  // --- Setters ---

  public void setSugeridor(Sugeridor sugeridor) {
    this.sugeridor = sugeridor;
  }

  // --- Metodos ---

  public List<Atuendo> generarSugerencias() {
    return this.sugeridor.generarSugerencias(this);
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    guardarropas.add(guardarropa);
  }

  public void eliminarGuardarropa(Guardarropa guardarropa) {
    guardarropas.remove(guardarropa);
  }
}
