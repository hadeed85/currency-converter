package com.currency.converter.client;

import com.currency.converter.client.api.SingleRate;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import java.util.concurrent.CompletableFuture;

@Client("${api}")
@Header(name = "Authorization", value = "ApiKey ${api.key}")
public interface SwopApiClient {

  @Get("/rates/{base}/{quote}")
  @SingleResult
  CompletableFuture<SingleRate> getSingleRate(
      @PathVariable String base, @PathVariable String quote);
}
