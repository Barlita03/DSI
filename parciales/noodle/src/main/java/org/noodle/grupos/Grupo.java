package org.noodle.grupos;

import org.noodle.Usuario;
import org.noodle.asignaciones.Asignacion;
import org.noodle.operaciones.altaYbajaUsuarios.AgregarUsuario;
import org.noodle.operaciones.Operacion;
import org.noodle.operaciones.altaYbajaUsuarios.RemoverUsuario;
import org.noodle.servicios.GuitabSDK;
import org.noodle.tareas.DarPermisosGuitab;
import org.noodle.tareas.NotificarAlumnosPorMail;
import org.noodle.tareas.QuitarPermisosGuitab;
import org.noodle.tareas.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grupo {
  private String nombre;
  private GuitabSDK guitab;
  private Usuario docente;
  private final List<Usuario> integrantes;
  private final List<Asignacion> asignaciones = new ArrayList<>();
  private final List<Operacion> operacionesPendientes = new ArrayList<>();
  private final HashMap<String, List<Tarea>> tareas = new HashMap<>();

  public Grupo(BorradorGrupo borrador) {
    this.nombre = borrador.getNombre();
    this.guitab = borrador.getGuitab();
    this.docente = borrador.getDocente();
    this.integrantes = borrador.getIntegrantes();

    tareas.put("nuevoIntegrante", List.of(
        new NotificarAlumnosPorMail("Nuevo integrante", "Su grupo ahora posee un nuevo integrante"),
        new DarPermisosGuitab()
    ));
    tareas.put("abandonoIntegrante", List.of(
        new NotificarAlumnosPorMail("Abandono integrante", "Un integrante ah abandonado su grupo"),
        new QuitarPermisosGuitab()
    ));
    tareas.put("nuevasEntregas", List.of(
        new NotificarAlumnosPorMail("Nuevas entregas disponibles", "Posee nuevas entregas disponibles")
    ));

    GestorDeGrupos.getInstancia().agregarGrupo(this);
  }

  public void notificar(String topic, Usuario usuario) {
    tareas.get(topic).forEach(t -> t.serRealizada(this, usuario));
  }

  public void agregarIntegrante(Usuario usuario) {
    integrantes.add(usuario);
    notificar("nuevoIntegrante", usuario);
  }

  public void removerIntegrante(Usuario usuario) {
    integrantes.add(usuario);
    notificar("abandonoIntegrante", usuario);
  }

  public void solicitarUnirse(Usuario usuario) {
    agregarOperacionPendiente(new AgregarUsuario(this, usuario));
  }

  public void solicitarAbandonar(Usuario usuario) {
    agregarOperacionPendiente(new RemoverUsuario(this, usuario));
  }

  public void agregarAsignacion(Asignacion asignacion) {
    asignaciones.add(asignacion);
  }

  public void removerAsignacion(Asignacion asignacion) {
    asignaciones.remove(asignacion);
  }

  public void agregarTarea(String topic, Tarea tarea) {
    List<Tarea> tareasPorTopic = tareas.getOrDefault(topic, new ArrayList<>());

    tareasPorTopic.add(tarea);

    if(tareasPorTopic.size() == 1) {
      tareas.put(topic, tareasPorTopic);
    }
  }

  public void quitarTarea(String topic, Tarea tarea) {
    List<Tarea> tareasPorTopic = tareas.getOrDefault(topic, null);

    if(tareasPorTopic != null) {
      tareasPorTopic.remove(tarea);

      if(tareasPorTopic.isEmpty()) {
        tareas.remove(topic);
      }
    }
  }

  public void agregarOperacionPendiente(Operacion operacion) {
    operacionesPendientes.add(operacion);
  }

  public void removerOperacionPendiente(Operacion operacion) {
    operacionesPendientes.remove(operacion);
  }

  public GuitabSDK getGuitab() {
    return guitab;
  }

  public String getNombre() {
    return nombre;
  }

  public List<Usuario> getIntegrantes() {
    return new ArrayList<>(integrantes);
  }
}
