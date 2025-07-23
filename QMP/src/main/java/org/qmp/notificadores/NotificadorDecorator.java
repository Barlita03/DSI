package org.qmp.notificadores;

public abstract class NotificadorDecorator implements Notificador {
  private final Notificador wrappee;

  public NotificadorDecorator(Notificador notificador) {
    this.wrappee = notificador;
  }

  @Override
  public void notificar(String mensaje) {
    wrappee.notificar(mensaje);
  }
}
