package org.example;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class TradeClassificationEngine {
  @Getter
  private final Map<String, BrokerTrades> tradesByBroker;

  TradeClassificationEngine(String path) {
    tradesByBroker = new HashMap<>();
    new ValidTrades(new CsvFileParser(path).parse().stream().map(Trade::new)).getTrades()
        .forEach((key, value) -> tradesByBroker.put(key, new BrokerTrades(value)));
  }

  void classify() {
    tradesByBroker.forEach((key, value) -> {
      value.rejectRepeatIds();
      value.rejectBasedOnTime();
    });
  }
}
