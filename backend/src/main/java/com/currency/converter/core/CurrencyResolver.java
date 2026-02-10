package com.currency.converter.core;

import com.currency.converter.core.model.Rate;
import com.currency.converter.service.CurrencyService;
import graphql.schema.DataFetcher;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton
public class CurrencyResolver {

  private final CurrencyService service;

  public CurrencyResolver(CurrencyService service) {
    this.service = service;
  }

  public DataFetcher<CompletableFuture<Rate>> getRate() {
    return dfe -> {
      String base = dfe.getArgument("sourceCurrency");
      String quote = dfe.getArgument("targetCurrency");
      Integer monetaryValue = dfe.getArgument("monetaryValue");

      return service
          .getSingleRate(base, quote)
          .thenApply(rate -> Mapper.toRate(rate, monetaryValue));
    };
  }
}
