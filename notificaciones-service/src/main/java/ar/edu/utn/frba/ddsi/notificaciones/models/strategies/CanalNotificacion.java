package ar.edu.utn.frba.ddsi.notificaciones.models.strategies;

import ar.edu.utn.frba.ddsi.notificaciones.models.entities.Notificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;

public interface CanalNotificacion {
  boolean esCompatibleCon(MedioNotificacion medio);
  void enviar(Notificacion notificacion);
}
