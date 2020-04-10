package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

import static java.util.Collections.singletonList;

public class ValidTrades {
  @Getter
  private final Map<String, List<Trade>> trades;

  ValidTrades(Stream<Trade> trades) {
    this.trades = trades.filter(Trade::isValid).collect(Collectors.toMap(Trade::getBroker,
        trade -> new ArrayList<>(singletonList(trade)),
        (existing, incoming) -> {
          existing.addAll(incoming);
          return existing;
        }));
  }
}
