package org.qmp.notificadores;

import org.qmp.apis.MailSender;
import org.qmp.usuarios.Usuario;

public class Emailer extends Notificador {
  MailSender api;

  public Emailer(String mensaje, MailSender mailSender) {
    super(mensaje);
    api = mailSender;
  }

  @Override
  public void ejecutarNotificacion(Usuario usuario) {
    api.send(usuario.getEmail(), mensaje);
  }
}
