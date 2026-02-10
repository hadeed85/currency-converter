package com.currency.converter.client.api;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record SingleRate(String base_currency, String quote_currency, String quote, String date) {}
