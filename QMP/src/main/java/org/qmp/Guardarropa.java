package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.operaciones.Sugerencia;
import org.qmp.prendas.Prenda;

public class Guardarropa {
  private final String criterio;
  private final List<Prenda> prendas = new ArrayList<>();
  private final List<Sugerencia> sugerenciasPendientes = new ArrayList<>();
  private final List<Sugerencia> sugerenciasProcesadas = new ArrayList<>();

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

  public List<Sugerencia> getSugerenciasPendientes() {
    return new ArrayList<>(sugerenciasPendientes);
  }

  public List<Sugerencia> getSugerenciasProcesadas() {
    return new ArrayList<>(sugerenciasProcesadas);
  }

  // --- Metodos ---

  public void agregarUsuario(Usuario usuario) {
    usuario.agregarGuardarropa(this);
  }

  public void agregarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }

  public void agregarSugerencia(Sugerencia sugerencia) {
    sugerenciasPendientes.add(sugerencia);
  }

  public void quitarSugerencia(Sugerencia sugerencia) {
    sugerenciasPendientes.remove(sugerencia);
  }

  public void registrarSugerenciaProcesada(Sugerencia sugerencia) {
    sugerenciasProcesadas.add(sugerencia);
  }
}
