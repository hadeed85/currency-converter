package com.currency.converter.service;

import com.currency.converter.client.SwopApiClient;
import com.currency.converter.client.api.SingleRate;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.CompletableFuture;

@Singleton
public class CurrencyService {
  @Inject private final SwopApiClient swopApiClient;

  public CurrencyService(SwopApiClient swopApiClient) {
    this.swopApiClient = swopApiClient;
  }

  public CompletableFuture<SingleRate> getSingleRate(String base, String quote) {
    return swopApiClient.getSingleRate(base, quote);
  }
}
