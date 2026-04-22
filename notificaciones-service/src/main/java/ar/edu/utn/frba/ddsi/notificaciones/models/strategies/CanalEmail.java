package ar.edu.utn.frba.ddsi.notificaciones.models.strategies;

import ar.edu.utn.frba.ddsi.notificaciones.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;

public class CanalEmail implements CanalNotificacion {
  @Override
  public boolean esCompatibleCon(MedioNotificacion medio) {
    return MedioNotificacion.EMAIL == medio;
  }

  @Override
  public void enviar(Notificacion notificacion) {
    if (!esCompatibleCon(notificacion.getMedio())) {
      throw new IllegalArgumentException("El canal email no es compatible con el medio indicado");
    }
    notificacion.marcarComoCompletada();
  }
}
