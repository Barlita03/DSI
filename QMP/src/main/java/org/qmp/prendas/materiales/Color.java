package org.qmp.prendas.materiales;

import java.util.List;

public class Color {
  private int red;
  private int green;
  private int blue;

  public Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  public List<Integer> getColor() {
    return List.of(this.red, this.green, this.blue);
  }
}
