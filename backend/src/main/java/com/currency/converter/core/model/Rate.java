package com.currency.converter.core.model;

public class Rate {

  private String sourceCurrency;
  private String targetCurrency;
  private String monetaryValue;

  public Rate() {}

  public String getTargetCurrency() {
    return targetCurrency;
  }

  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  public String getMonetaryValue() {
    return monetaryValue;
  }

  public void setMonetaryValue(String monetaryValue) {
    this.monetaryValue = monetaryValue;
  }

  public String getSourceCurrency() {
    return sourceCurrency;
  }

  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }

  @Override
  public String toString() {
    return "Rate{"
        + "sourceCurrency='"
        + sourceCurrency
        + '\''
        + ", targetCurrency='"
        + targetCurrency
        + '\''
        + ", monetaryValue='"
        + monetaryValue
        + '\''
        + '}';
  }
}
