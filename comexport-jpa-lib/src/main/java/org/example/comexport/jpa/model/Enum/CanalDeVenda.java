package org.example.comexport.jpa.model.Enum;

public enum CanalDeVenda {
  E_COMMERCE("e-commerce"),
  LOJA_FISICA("loja física"),
  PARCEIROS("parceiros");

  private final String canalDeVenda;

  CanalDeVenda(String canalDeVenda) {
    this.canalDeVenda = canalDeVenda;
  }

  public String valor() {
    return String.valueOf(this.ordinal());
  }

  @Override
  public String toString() {
    return canalDeVenda;
  }
}
