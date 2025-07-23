package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.operaciones.Propuesta;
import org.qmp.prendas.Prenda;

public class Guardarropa {
  private final String criterio;
  private final List<Prenda> prendas = new ArrayList<>();
  private final List<Propuesta> propuestasPendientes = new ArrayList<>();
  private final List<Propuesta> propuestasProcesadas = new ArrayList<>();

  // --- Constructor ---

  public Guardarropa(String criterio, List<Usuario> usuarios) {
    this.criterio = criterio;
    usuarios.forEach(u -> u.agregarGuardarropa(this));
  }

  // --- Getters ---

  public List<Prenda> getPrendasSuperiores() {
    return new ArrayList<>(this.prendas.stream().filter(Prenda::esSuperior).toList());
  }

  public List<Prenda> getPrendasInferiores() {
    return new ArrayList<>(this.prendas.stream().filter(Prenda::esInferior).toList());
  }

  public List<Prenda> getCalzados() {
    return new ArrayList<>(this.prendas.stream().filter(Prenda::esCalzado).toList());
  }

  public List<Prenda> getAccesorios() {
    return new ArrayList<>(this.prendas.stream().filter(Prenda::esAccesorio).toList());
  }

  public List<Prenda> getPrendas() {
    return new ArrayList<>(prendas);
  }

  public String getCriterio() {
    return criterio;
  }

  public List<Propuesta> getPropuestasPendientes() {
    return new ArrayList<>(propuestasPendientes);
  }

  public List<Propuesta> getPropuestasProcesadas() {
    return new ArrayList<>(propuestasProcesadas);
  }

  // --- Metodos ---

  public void agregarUsuario(Usuario usuario) {
    usuario.agregarGuardarropa(this);
  }

  public void quitarUsuario(Usuario usuario) {
    usuario.eliminarGuardarropa(this);
  }

  public void agregarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }

  public void agregarPropuesta(Propuesta propuesta) {
    propuestasPendientes.add(propuesta);
  }

  public void quitarPropuesta(Propuesta propuesta) {
    propuestasPendientes.remove(propuesta);
  }

  public void registrarPropuestaProcesada(Propuesta propuesta) {
    propuestasProcesadas.add(propuesta);
  }

  public void quitarPropuestaProcesada(Propuesta propuesta) {
    propuestasProcesadas.remove(propuesta);
  }

  public void limpiarListaPrendas() {
    prendas.clear();
  }

  public void limpiarListaPropuestasPendientes() {
    propuestasPendientes.clear();
  }

  public void limpiarListaPropuestasProcesadas() {
    propuestasProcesadas.clear();
  }
}
