package org.noodle.tareas;

import org.noodle.Notificacion;
import org.noodle.Usuario;
import org.noodle.grupos.Grupo;

public class NotificarAlumnosPorMail implements Tarea {
  private final String subject;
  private final String body;

  public NotificarAlumnosPorMail(String subject, String body) {
    this.subject = subject;
    this.body = body;
  }

  @Override
  public void serRealizada(Grupo grupo, Usuario usuario) {
    Notificacion notificacion = new Notificacion(subject, body);

    if(usuario != null) {
      usuario.recibirMail(notificacion);
    } else {
      grupo.getIntegrantes().forEach(u -> u.recibirMail(notificacion));
    }
  }
}
