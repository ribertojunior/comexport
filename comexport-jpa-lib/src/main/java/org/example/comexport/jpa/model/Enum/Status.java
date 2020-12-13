package org.example.comexport.jpa.model.Enum;

public enum Status {
  AGUARDANDO_ENTREGA("aguardando entrega"),
  AGUARDANDO_RETIRADA_PARCEIRO("aguardando retirada parceiro"),
  ENTREGUE("entregue");

  private final String status;

  Status(String status) {
    this.status = status;
  }

  public String valor() {
    return String.valueOf(this.ordinal());
  }

  @Override
  public String toString() {
    return status;
  }
}
