<<<<<<<< HEAD:QMP/src/main/java/org/qmp/materiales/Color.java
package org.qmp.materiales;
========
package org.qmp.prendas;
>>>>>>>> 6c90c8a63b7f3d9d53675a6e983d368efe8e88f2:QMP/src/main/java/org/qmp/prendas/Color.java

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
