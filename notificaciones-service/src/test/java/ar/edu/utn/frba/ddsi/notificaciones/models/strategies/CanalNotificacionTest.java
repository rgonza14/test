package ar.edu.utn.frba.ddsi.notificaciones.models.strategies;

import ar.edu.utn.frba.ddsi.notificaciones.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanalNotificacionTest {

  @Test
  void canalEmailDebeSerCompatibleSoloConEmail() {
    CanalEmail canal = new CanalEmail();

    assertTrue(canal.esCompatibleCon(MedioNotificacion.EMAIL));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.SMS));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.WHATSAPP));
  }

  @Test
  void canalSmsDebeSerCompatibleSoloConSms() {
    CanalSms canal = new CanalSms();

    assertTrue(canal.esCompatibleCon(MedioNotificacion.SMS));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.EMAIL));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.WHATSAPP));
  }

  @Test
  void canalWhatsappDebeSerCompatibleSoloConWhatsapp() {
    CanalWhatsapp canal = new CanalWhatsapp();

    assertTrue(canal.esCompatibleCon(MedioNotificacion.WHATSAPP));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.EMAIL));
    assertFalse(canal.esCompatibleCon(MedioNotificacion.SMS));
  }

  @Test
  void canalEmailDebeCompletarLaNotificacionAlEnviar() {
    Notificacion notificacion = new Notificacion("test@mail.com", "Hola", MedioNotificacion.EMAIL);

    new CanalEmail().enviar(notificacion);

    assertTrue(notificacion.estaCompletada());
  }
}
