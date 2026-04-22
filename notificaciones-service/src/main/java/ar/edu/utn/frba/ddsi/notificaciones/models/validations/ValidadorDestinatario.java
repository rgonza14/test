package ar.edu.utn.frba.ddsi.notificaciones.models.validations;

import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;

import java.util.regex.Pattern;

public final class ValidadorDestinatario {
  private static final Pattern EMAIL_PATTERN =
      Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

  private static final Pattern PHONE_PATTERN =
      Pattern.compile("^\\+?[0-9 ()-]{8,20}$");

  private ValidadorDestinatario() {
  }

  public static void validar(String destinatario, MedioNotificacion medio) {
    switch (medio) {
      case EMAIL -> validarEmail(destinatario);
      case SMS, WHATSAPP -> validarTelefono(destinatario);
    }
  }

  private static void validarEmail(String destinatario) {
    if (!EMAIL_PATTERN.matcher(destinatario).matches()) {
      throw new IllegalArgumentException("El destinatario debe ser un email válido");
    }
  }

  private static void validarTelefono(String destinatario) {
    if (!PHONE_PATTERN.matcher(destinatario).matches()) {
      throw new IllegalArgumentException("El destinatario debe ser un teléfono válido");
    }
  }
}
