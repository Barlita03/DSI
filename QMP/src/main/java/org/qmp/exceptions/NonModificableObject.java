package org.qmp.exceptions;

public class NonModificableObject extends RuntimeException {
  public NonModificableObject(String message) {
    super(message);
  }
}
