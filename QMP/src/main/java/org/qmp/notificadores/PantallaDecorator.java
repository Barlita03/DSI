package org.qmp.notificadores;

import org.qmp.apis.NotificationService;

public class PantallaDecorator extends NotificadorDecorator {
  private final NotificationService notificationService;

  public PantallaDecorator(NotificationService notificationService, Notificador notificador) {
    super(notificador);
    this.notificationService = notificationService;
  }

  @Override
  public void notificar(String mensaje) {
    notificationService.notify(mensaje);
    super.notificar(mensaje);
  }
}
