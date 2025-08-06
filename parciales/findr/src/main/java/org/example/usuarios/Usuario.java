package org.example.usuarios;

import org.example.filtros.Filtro;
import org.example.filtros.Tipo;
import org.example.generos.Genero;
import org.example.servicios.Localizador;
import org.example.servicios.Notificador;
import org.example.servicios.Ubicacion;
import org.example.servicios.validadorDeFoto.ValidadorDeFoto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Usuario {
  private String nombre;
  private int edad;
  private int altura;
  private int peso;
  private String foto;
  private Genero genero;
  private ValidadorDeFoto validadorDeFoto;
  private ValidacionPendiente validacionPendiente = null;
  private Notificador notificador;
  private Localizador localizador;
  private EstadoUsuario estado;
  private final List<Filtro> filtros = new ArrayList<>();
  private final List<Tipo> tipos = new ArrayList<>();
  private Ubicacion ubicacion;

  public Usuario (Builder builder) {
    // TODO
  }

  public String getNombre() {
    return nombre;
  }

  public int getEdad() {
    return edad;
  }

  public int getAltura() {
    return altura;
  }

  public int getPeso() {
    return peso;
  }

  public void setFoto(String url) {
    foto = url;
  }

  public void ponerFoto(String url) {
    double porcentajeDeAceptacion = validadorDeFoto.validar(url);

    if(porcentajeDeAceptacion > 70) {
      aceptarFoto(url);
    } else if(porcentajeDeAceptacion < 30) {
      rechazarFoto();
    } else {
      validacionPendiente = new ValidacionPendiente(this, url);
    }
  }

  public void aceptarFoto(String url) {
    setFoto(url);
    recibirNotificacion("Su foto fue aceptada");
  }

  public void rechazarFoto() {
    recibirNotificacion("Su foto fue rechazada");
  }

  public void recibirNotificacion(String mensaje) {
    notificador.enviarNotificacion(this, mensaje);
  }

  public double distanciaCon(Usuario usuario) {
    return localizador.localizar(usuario).distanciaA(getUbicacion());
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public void actualizarUbicacion() {
    ubicacion = localizador.localizar(this);
  }

  public void cambiarAConectado() {
    estado = EstadoUsuario.CONECTADO;
  }

  public void cambiarAInactivo() {
    estado = EstadoUsuario.INACTIVO;
  }

  // Agregar en todos los lugares donde sea necesario para cumplir con el punto 10
  public void actualizarInformacion() {
    actualizarUbicacion();
    cambiarAConectado();
  }

  public boolean estaConectado() {
    return estado.equals(EstadoUsuario.CONECTADO);
  }

  public static class Builder {
    private String nombre;
    private int edad;
    private int altura;
    private int peso;
    private Genero genero;
    private ValidadorDeFoto validadorDeFoto;
    private Notificador notificador;
    private Localizador localizador;

    public Builder nombre(String nombre) {
      this.nombre = nombre;
      return this;
    }

    // TODO

    public Usuario build() {
      return new Usuario(this);
    }
  }
}
