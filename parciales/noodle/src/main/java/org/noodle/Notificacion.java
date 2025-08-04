package org.noodle;

import org.noodle.tareas.NotificarAlumnosPorMail;

public class Notificacion {
  private final String subject;
  private final String body;

  public Notificacion (String subject, String body) {
    this.subject = subject;
    this.body = body;
  }

  public String getSubject() {
    return subject;
  }

  public String getBody() {
    return body;
  }
}
