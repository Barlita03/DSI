package org.example.servicios.validadorDeFoto;

public class ValidadorVerifyGPT implements ValidadorDeFoto {
  private VerifyGPT servicio;

  @Override
  public double validar(String url){
    return servicio.rateContentSafety(url);
  }
}
