package org.qmp.usuarios;

import java.util.ArrayList;
import java.util.List;
import org.qmp.AsesorDeImagen;
import org.qmp.Guardarropa;
import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Prenda;
import org.qmp.propuestas.Propuesta;
import org.qmp.sugeridores.Sugeridor;

public class Usuario {
  private Sugeridor sugeridor;
  private AsesorDeImagen asesor;
  private final List<Guardarropa> guardarropas = new ArrayList<>();
  private final String email;
  private final int edad;
  private final List<Atuendo> sugerenciasDiarias = new ArrayList<>();

  // --- Constructor ---

  public Usuario(int edad, String email, Sugeridor sugeridor, AsesorDeImagen asesor) {
    this.edad = edad;
    this.email = email;
    this.sugeridor = sugeridor;
    this.asesor = asesor;

    GestorDeUsuarios.agregarUsuario(this);
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

  public List<Propuesta> getPropuestasPendientes() {
    return guardarropas.stream().flatMap(g -> g.getPropuestasPendientes().stream()).toList();
  }

  public List<Propuesta> getPropuestasProcesadas() {
    return guardarropas.stream().flatMap(g -> g.getPropuestasProcesadas().stream()).toList();
  }

  public List<Guardarropa> getGuardarropas() {
    return new ArrayList<>(guardarropas);
  }

  public List<Atuendo> getSugerenciasDiarias() {
    return new ArrayList<>(sugerenciasDiarias);
  }

  public String getEmail() {
    return email;
  }

  // --- Setters ---

  public void setSugeridor(Sugeridor sugeridor) {
    this.sugeridor = sugeridor;
  }

  // --- Metodos ---

  public void recibirNotificacion() {
    sugerenciasDiarias();
  }

  public void sugerenciasDiarias() {
    sugerenciasDiarias.clear();
    sugerenciasDiarias.addAll(sugeridor.generarSugerencias(this));
  }

  public List<Atuendo> generarSugerencias(String ubicacion) {
    return asesor.sugerirAtuendos(ubicacion, this);
  }

  public List<Atuendo> todasLasCombinaciones() {
    return new ArrayList<>(sugeridor.generarSugerencias(this));
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    guardarropas.add(guardarropa);
  }

  public void eliminarGuardarropa(Guardarropa guardarropa) {
    guardarropas.remove(guardarropa);
  }
}
