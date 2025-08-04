package org.noodle;

import org.noodle.servicios.MailSender;

public class Usuario {
  private final String username;
  private final String mail;
  private final MailSender emailer;

  public Usuario(String username, String mail, MailSender emailer) {
    this.username = username;
    this.mail = mail;
    this.emailer = emailer;
  }

  public String getUsername() {
    return username;
  }

  public String getMail() {
    return mail;
  }

  public MailSender getEmailer() {
    return emailer;
  }

  public void recibirMail(Notificacion notificacion) {
    emailer.send(mail, notificacion.getSubject(), notificacion.getBody());
  }
}
