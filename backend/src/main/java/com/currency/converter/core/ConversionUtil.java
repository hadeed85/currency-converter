package com.currency.converter.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ConversionUtil {

  private static final Locale MONEY_LOCALE = Locale.US;

  public static BigDecimal convertCurrency(BigDecimal amount, BigDecimal conversionRate) {
    if (amount == null || conversionRate == null) {
      throw new IllegalArgumentException("Amount and conversionRate must not be null");
    }
    return amount.multiply(conversionRate).setScale(2, RoundingMode.HALF_UP);
  }

  public static String formatMoney(BigDecimal amount, String currency) {
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(MONEY_LOCALE);
    numberFormat.setCurrency(Currency.getInstance(currency));
    return numberFormat.format(amount);
  }
}
