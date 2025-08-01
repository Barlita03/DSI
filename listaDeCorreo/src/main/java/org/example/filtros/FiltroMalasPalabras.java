package org.example.filtros;

import java.util.ArrayList;
import java.util.List;

public class FiltroMalasPalabras implements Filtro {
  private List<String> palabras = new ArrayList<>();

  public FiltroMalasPalabras(List<String> palabras) {
    this.palabras.addAll(palabras);
  }

  public void agregarPalabra(String palabra) {
    palabras.add(palabra);
  }

  public void quitarPalabra(String palabra) {
    palabras.remove(palabra);
  }

  @Override
  public boolean cumple(String texto) {
    return palabras.stream().anyMatch(texto::contains);
  }
}
