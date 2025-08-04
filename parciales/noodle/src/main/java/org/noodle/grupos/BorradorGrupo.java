package org.noodle.grupos;

import org.noodle.Usuario;
import org.noodle.operaciones.CierreDeGrupo;
import org.noodle.servicios.GuitabSDK;
import org.noodle.tareas.CrearRepoGuitab;
import org.noodle.tareas.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BorradorGrupo {
  private GuitabSDK guitab;
  private CierreDeGrupo solicitudDeCierre;
  private final String nombre;
  private final Usuario docente;
  private final int idealIntegrantes;
  private final List<Usuario> integrantes = new ArrayList<>();
  private final Map<String, List<Tarea>> tareas = new HashMap<>();

  private BorradorGrupo(String nombre, Usuario docente, int idealIntegrantes, GuitabSDK guitab) {
    this.nombre = nombre;
    this.docente = docente;
    this.idealIntegrantes = idealIntegrantes;
    this.guitab = guitab;

    tareas.put("cerrarGrupo", List.of(new CrearRepoGuitab()));
  }

  public int cantidadDeIntegrantes() {
    return integrantes.size();
  }

  public Optional<Grupo> agregarIntegrante(Usuario usuario) {
    integrantes.add(usuario);

    if (cantidadDeIntegrantes() == idealIntegrantes) {
      return Optional.ofNullable(cerrarGrupo());
    }

    return Optional.empty();
  }

  public void removerIntegrante(Usuario usuario) {
    integrantes.remove(usuario);
  }

  public Grupo cerrarGrupo() {
    return new Grupo(this);
  }

  public String getNombre() {
    return nombre;
  }

  public GuitabSDK getGuitab() {
    return guitab;
  }

  public List<Usuario> getIntegrantes() {
    return new ArrayList<>(integrantes);
  }

  public Usuario getDocente() {
    return docente;
  }

  public void solicitarCerrarGrupo() {
    solicitudDeCierre = new CierreDeGrupo(this);
  }
}
