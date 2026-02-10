package com.currency.converter.core;

import com.currency.converter.client.api.SingleRate;
import com.currency.converter.core.model.Rate;
import java.math.BigDecimal;

public class Mapper {

  static Rate toRate(SingleRate singleRate, Integer amount) {

    Rate rate = new Rate();

    BigDecimal conversionRate = new BigDecimal(singleRate.quote());

    BigDecimal monetaryValue =
        ConversionUtil.convertCurrency(BigDecimal.valueOf(amount), conversionRate);

    rate.setMonetaryValue(ConversionUtil.formatMoney(monetaryValue, singleRate.quote_currency()));
    rate.setTargetCurrency(singleRate.quote_currency());
    rate.setSourceCurrency(singleRate.base_currency());

    return rate;
  }
}
