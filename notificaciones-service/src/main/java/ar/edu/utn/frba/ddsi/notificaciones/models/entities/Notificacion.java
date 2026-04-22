package ar.edu.utn.frba.ddsi.notificaciones.models.entities;

import ar.edu.utn.frba.ddsi.notificaciones.models.enums.EstadoNotificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.enums.MedioNotificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.strategies.CanalNotificacion;
import ar.edu.utn.frba.ddsi.notificaciones.models.validations.ValidadorDestinatario;

import java.util.Objects;
import java.util.UUID;

public class Notificacion {
  private final UUID id;
  private final String destinatario;
  private final String mensaje;
  private final MedioNotificacion medio;
  private EstadoNotificacion estado;

  public Notificacion(String destinatario, String mensaje, MedioNotificacion medio) {
    this(UUID.randomUUID(), destinatario, mensaje, medio, EstadoNotificacion.PENDIENTE);
  }

  public Notificacion(UUID id, String destinatario, String mensaje, MedioNotificacion medio, EstadoNotificacion estado) {
    this.id = Objects.requireNonNull(id, "El id es obligatorio");
    this.medio = Objects.requireNonNull(medio, "El medio es obligatorio");
    this.estado = Objects.requireNonNull(estado, "El estado es obligatorio");
    this.destinatario = validarYNormalizarDestinatario(destinatario);
    this.mensaje = validarYNormalizarMensaje(mensaje);

    ValidadorDestinatario.validar(this.destinatario, this.medio);
  }

  public void enviarPor(CanalNotificacion canal) {
    Objects.requireNonNull(canal, "El canal es obligatorio");

    if (!canal.esCompatibleCon(this.medio)) {
      throw new IllegalArgumentException("El canal no es compatible con el medio de la notificación");
    }

    canal.enviar(this);
  }

  public void marcarComoCompletada() {
    this.estado = EstadoNotificacion.COMPLETADA;
  }

  public boolean estaCompletada() {
    return this.estado == EstadoNotificacion.COMPLETADA;
  }

  public UUID getId() {
    return id;
  }

  public String getDestinatario() {
    return destinatario;
  }

  public String getMensaje() {
    return mensaje;
  }

  public MedioNotificacion getMedio() {
    return medio;
  }

  public EstadoNotificacion getEstado() {
    return estado;
  }

  private String validarYNormalizarDestinatario(String destinatario) {
    if (destinatario == null || destinatario.trim().isEmpty()) {
      throw new IllegalArgumentException("El destinatario es obligatorio");
    }

    return destinatario.trim();
  }

  private String validarYNormalizarMensaje(String mensaje) {
    if (mensaje == null || mensaje.trim().isEmpty()) {
      throw new IllegalArgumentException("El mensaje es obligatorio");
    }

    return mensaje.trim();
  }
}
