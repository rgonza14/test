package ar.edu.utn.frba.ddsi.notificaciones.models.validations;

import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidadorDestinatarioTest {

  @Test
  void debeAceptarEmailValido() {
    assertDoesNotThrow(() -> ValidadorDestinatario.validar("test@mail.com", MedioNotificacion.EMAIL));
  }

  @Test
  void debeAceptarTelefonoValidoParaSms() {
    assertDoesNotThrow(() -> ValidadorDestinatario.validar("+54 11 5555-5555", MedioNotificacion.SMS));
  }

  @Test
  void debeAceptarTelefonoValidoParaWhatsapp() {
    assertDoesNotThrow(() -> ValidadorDestinatario.validar("+54 11 5555-5555", MedioNotificacion.WHATSAPP));
  }

  @Test
  void debeRechazarEmailInvalido() {
    assertThrows(IllegalArgumentException.class,
        () -> ValidadorDestinatario.validar("correo-invalido", MedioNotificacion.EMAIL));
  }

  @Test
  void debeRechazarTelefonoInvalido() {
    assertThrows(IllegalArgumentException.class,
        () -> ValidadorDestinatario.validar("abc123", MedioNotificacion.SMS));
  }
}
