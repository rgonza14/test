package ar.edu.utn.frba.ddsi.notificaciones.models.strategies;

import ar.edu.utn.frba.ddsi.notificaciones.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;

public class CanalWhatsapp implements CanalNotificacion {
  @Override
  public boolean esCompatibleCon(MedioNotificacion medio) {
    return MedioNotificacion.WHATSAPP == medio;
  }

  @Override
  public void enviar(Notificacion notificacion) {
    if (!esCompatibleCon(notificacion.getMedio())) {
      throw new IllegalArgumentException("El canal whatsapp no es compatible con el medio indicado");
    }
    notificacion.marcarComoCompletada();
  }
}
