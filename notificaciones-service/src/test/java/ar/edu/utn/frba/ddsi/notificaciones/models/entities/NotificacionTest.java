package ar.edu.utn.frba.ddsi.notificaciones.models.entities;

import ar.edu.utn.frba.ddsi.notificaciones.models.enums.EstadoNotificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.strategies.CanalEmail;
import ar.edu.utn.frba.ddsi.notificaciones.models.strategies.CanalSms;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificacionTest {

  @Test
  void debeCrearUnaNotificacionEnEstadoPendiente() {
    Notificacion notificacion = new Notificacion("test@mail.com", "Hola", MedioNotificacion.EMAIL);

    assertEquals(EstadoNotificacion.PENDIENTE, notificacion.getEstado());
  }

  @Test
  void debeMarcarComoCompletadaAlEnviarPorUnCanalCompatible() {
    Notificacion notificacion = new Notificacion("test@mail.com", "Hola", MedioNotificacion.EMAIL);

    notificacion.enviarPor(new CanalEmail());

    assertEquals(EstadoNotificacion.COMPLETADA, notificacion.getEstado());
    assertTrue(notificacion.estaCompletada());
  }

  @Test
  void noDebePermitirDestinatarioVacio() {
    assertThrows(IllegalArgumentException.class,
        () -> new Notificacion("   ", "Hola", MedioNotificacion.EMAIL));
  }

  @Test
  void noDebePermitirMensajeVacio() {
    assertThrows(IllegalArgumentException.class,
        () -> new Notificacion("test@mail.com", "   ", MedioNotificacion.EMAIL));
  }

  @Test
  void noDebePermitirMedioNulo() {
    assertThrows(NullPointerException.class,
        () -> new Notificacion("test@mail.com", "Hola", null));
  }

  @Test
  void noDebePermitirEstadoNuloEnRehidratacion() {
    assertThrows(NullPointerException.class,
        () -> new Notificacion(UUID.randomUUID(), "test@mail.com", "Hola", MedioNotificacion.EMAIL, null));
  }

  @Test
  void noDebePermitirCanalNulo() {
    Notificacion notificacion = new Notificacion("test@mail.com", "Hola", MedioNotificacion.EMAIL);

    assertThrows(NullPointerException.class,
        () -> notificacion.enviarPor(null));
  }

  @Test
  void noDebePermitirCanalIncompatible() {
    Notificacion notificacion = new Notificacion("test@mail.com", "Hola", MedioNotificacion.EMAIL);

    assertThrows(IllegalArgumentException.class,
        () -> notificacion.enviarPor(new CanalSms()));
  }

  @Test
  void noDebePermitirEmailInvalido() {
    assertThrows(IllegalArgumentException.class,
        () -> new Notificacion("correo-invalido", "Hola", MedioNotificacion.EMAIL));
  }

  @Test
  void noDebePermitirTelefonoInvalido() {
    assertThrows(IllegalArgumentException.class,
        () -> new Notificacion("abc123", "Hola", MedioNotificacion.SMS));
  }

  @Test
  void debeRehidratarConValoresNormalizados() {
    UUID id = UUID.randomUUID();

    Notificacion notificacion = new Notificacion(
        id,
        "  +54 11 5555-5555  ",
        "  Mensaje  ",
        MedioNotificacion.WHATSAPP,
        EstadoNotificacion.PENDIENTE
    );

    assertEquals(id, notificacion.getId());
    assertEquals("+54 11 5555-5555", notificacion.getDestinatario());
    assertEquals("Mensaje", notificacion.getMensaje());
  }
}
