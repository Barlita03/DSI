package org.qmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qmp.alertas.GestorDeAlertas;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.apis.MailSender;
import org.qmp.apis.NotificationService;
import org.qmp.notificadores.EmailerDecorator;
import org.qmp.notificadores.Final;
import org.qmp.notificadores.PantallaDecorator;
import org.qmp.prendas.AsesorDeImagen;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.serviciosmeteorologicos.accuweather.ServicioMeteorologicoAccuWeather;
import org.qmp.sugeridores.SugeridorBasico;
import org.qmp.usuarios.Usuario;

public class AlertasTest {
  ServicioMeteorologico servicioMeteorologico =
      new ServicioMeteorologicoAccuWeather(
          new AccuWeatherApi(), Duration.ofHours(3), "Buenos Aires");
  Usuario usuario1 =
      new Usuario(21, "email", new SugeridorBasico(), new AsesorDeImagen(servicioMeteorologico));
  Usuario usuario2 =
      new Usuario(21, "email", new SugeridorBasico(), new AsesorDeImagen(servicioMeteorologico));

  GestorDeAlertas gestorDeAlertas = new GestorDeAlertas(servicioMeteorologico);

  @BeforeEach
  void setup() {
    gestorDeAlertas.vaciarLista();
  }

  @Test
  void unUsuarioPuedeConocerLasUltimasAlertasMeteorologicas() {
    gestorDeAlertas.actualizarAlertas();

    assertEquals(2, gestorDeAlertas.getAlertas().size());
  }

  @Test
  void anteUnaAlertaDeTormentaLaAppMeNotificaLlegarmeUnParaguas() {
    NotificationService notificationService = mock(NotificationService.class);

    usuario1.setNotificador(new PantallaDecorator(notificationService, new Final()));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(notificationService).notify("Recorda llevar un paraguas");
  }

  @Test
  void anteUnaAlertaDeGranizoLaAppMeNotificaLlegarmeUnParaguas() {
    NotificationService notificationService = mock(NotificationService.class);

    usuario1.setNotificador(new PantallaDecorator(notificationService, new Final()));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(notificationService).notify("Evita salir con el auto");
  }

  @Test
  void cuandoSeLanzanLasAlertasLeLleganATodosLosUsuarios() {
    NotificationService notificationService1 = mock(NotificationService.class);
    NotificationService notificationService2 = mock(NotificationService.class);

    usuario1.setNotificador(new PantallaDecorator(notificationService1, new Final()));
    usuario2.setNotificador(new PantallaDecorator(notificationService2, new Final()));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(notificationService1, atLeastOnce()).notify(eq("Evita salir con el auto"));
    verify(notificationService2, atLeastOnce()).notify(eq("Evita salir con el auto"));
  }

  @Test
  void cuandoSeLanzanLasAlertasQuieroRecibirUnaNotificacionEnPantalla() {
    NotificationService notificationService1 = mock(NotificationService.class);

    usuario1.setNotificador(new PantallaDecorator(notificationService1, new Final()));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(notificationService1).notify("Evita salir con el auto");
  }

  @Test
  void cuandoSeLanzanLasAlertasQuieroRecibirUnMail() {
    MailSender mailSender = mock(MailSender.class);

    usuario1.setNotificador(new EmailerDecorator(usuario1.getEmail(), mailSender, new Final()));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(mailSender).send(usuario1.getEmail(), "Evita salir con el auto");
  }

  @Test
  void comoUsuarioPuedoUsarVariosMetodosDeNotificacion() {
    MailSender mailSender = mock(MailSender.class);
    NotificationService notificationService = mock(NotificationService.class);

    usuario1.setNotificador(
        new EmailerDecorator(
            usuario1.getEmail(),
            mailSender,
            new PantallaDecorator(notificationService, new Final())));

    gestorDeAlertas.actualizarAlertas();
    gestorDeAlertas.lanzarAlertas();

    verify(mailSender).send(usuario1.getEmail(), "Evita salir con el auto");
    verify(notificationService).notify("Recorda llevar un paraguas");
  }
}
