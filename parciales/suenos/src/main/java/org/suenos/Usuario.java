package org.suenos;

import org.suenos.personalidades.Personalidad;
import org.suenos.sueños.Sueño;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
  private final List<Sueño> sueños = new ArrayList<>();
  private Personalidad personalidad;
  private int felicidonios;
  private int cantidadDeHijos;
  private int buenaPlata;
  private int salario;

  public void cumplirUnSueño() {
    personalidad.cumplirUnSueño(this);
  }

  public void sumarFelicidonios(int cuantos) {
    felicidonios += cuantos;
  }

  public Sueño sueñoDeMasValor() {
    return sueños.sort(s -> s.getFelicidad(this)).get(0);
  }

  public List<Sueño> getSueños() {
    return sueños.stream().filter(Sueño::estaPendiente).toList();
  }

  public void agregarSueño(Sueño sueño) {
    sueños.add(sueño);
  }

  public void removerSueño(Sueño sueño) {
    sueños.remove(sueño);
  }

  public int getCantidadDeHijos() {
    return cantidadDeHijos;
  }

  public int getBuenaPlata() {
    return buenaPlata;
  }

  public int getSalario() {
    return salario;
  }

  public void multiplicarBuenaPlata(int cuanto) {
    buenaPlata = buenaPlata * cuanto;
  }

  public void agregarHijos(int cuantos) {
    cantidadDeHijos += cuantos;
  }
}