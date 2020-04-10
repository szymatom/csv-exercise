package org.example;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidTradesTest {

  @Test
  void shouldCreateTrades() {
    //given
    List<CSVRecord> records = new CsvFileParser("src/test/resources/trades.csv").parse();

    //when
    ValidTrades validTrades = new ValidTrades(Stream.of(records.get(0),
        records.get(11),
        records.get(10),
        records.get(86),
        records.get(87)
    ).map(Trade::new));

    //then
    assertThat(validTrades.getTrades().get("Fidelity")).extracting("time")
        .containsExactly(LocalDateTime.parse("2017-05-10T10:00"), LocalDateTime.parse("2017-05-10T10:00:41"),LocalDateTime.parse("2017-05-10T10:00:40"));
  }

  @Test
  void shouldCreateAllTrades() {
    //given
    List<CSVRecord> records = new CsvFileParser("src/test/resources/trades.csv").parse();

    //when
    ValidTrades validTrades = new ValidTrades(records.stream().map(Trade::new));

    //then
    assertThat(validTrades.getTrades().get("Ameriprise Financial")).hasSize(48);
    assertThat(validTrades.getTrades().get("Waddell & Reed")).hasSize(2);
    assertThat(validTrades.getTrades().get("Fidelity")).hasSize(51);
    assertThat(validTrades.getTrades().get("Edward Jones")).hasSize(49);
    assertThat(validTrades.getTrades().get("National Planning Corporation")).hasSize(50);
    assertThat(validTrades.getTrades().get("Charles Schwab")).hasSize(51);
    assertThat(validTrades.getTrades().get("Raymond James Financial")).hasSize(50);
    assertThat(validTrades.getTrades().get("AXA Advisors")).hasSize(50);
    assertThat(validTrades.getTrades().get("Wells Fargo Advisors")).hasSize(100);
    assertThat(validTrades.getTrades().get("Transamerica Financial")).hasSize(1);
    assertThat(validTrades.getTrades().get("TD Ameritrade")).hasSize(49);
    assertThat(validTrades.getTrades().get("LPL Financial")).hasSize(50);
  }
}
