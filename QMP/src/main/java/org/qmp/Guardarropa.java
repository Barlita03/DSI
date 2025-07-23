package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.prendas.Prenda;
import org.qmp.propuestas.EstadoPropuesta;
import org.qmp.propuestas.Propuesta;

public class Guardarropa {
  private final String criterio;
  private final List<Prenda> prendas = new ArrayList<>();
  private final List<Propuesta> propuestas = new ArrayList<>();

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
    return new ArrayList<>(
        propuestas.stream().filter(p -> p.getEstado().equals(EstadoPropuesta.PENDIENTE)).toList());
  }

  public List<Propuesta> getPropuestasProcesadas() {
    return new ArrayList<>(
        propuestas.stream()
            .filter(
                p ->
                    p.getEstado().equals(EstadoPropuesta.ACEPTADA)
                        || p.getEstado().equals(EstadoPropuesta.RECHAZADA))
            .toList());
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
    propuestas.add(propuesta);
  }

  public void limpiarListaPrendas() {
    prendas.clear();
  }

  public void limpiarListaPropuestas() {
    propuestas.clear();
  }
}
