package org.example;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.Side.Buy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeTest {

  @Test
  void shouldCreateTrade() {
    //given
    List<CSVRecord> records = new CsvFileParser("src/test/resources/trades.csv").parse();

    //when
    Trade trade  = new Trade(records.get(3));

    //then
    assertEquals(trade, new Trade(LocalDateTime.parse("2017-05-10T10:00:03"),"National Planning Corporation", 1, "K", "LGHT", 400L, 140D,
        Buy));
  }
}
