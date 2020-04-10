package org.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Side.Buy;

public class BrokerTradesTest {

  private final Trade trade1 = new Trade(LocalDateTime.parse("2017-05-10T10:00:03"),"National Planning Corporation", 1, "K", "LGHT", 400L, 140D, Buy);
  private final Trade trade2 = new Trade(LocalDateTime.parse("2017-05-10T10:00:04"),"National Planning Corporation", 1, "K", "LGHT", 500L, 140D, Buy);
  private final Trade trade3 = new Trade(LocalDateTime.parse("2017-05-10T10:00:05"),"National Planning Corporation", 2, "K", "LGHT", 600L, 140D, Buy);
  private final Trade trade4 = new Trade(LocalDateTime.parse("2017-05-10T10:00:06"),"National Planning Corporation", 3, "K", "LGHT", 700L, 140D, Buy);
  private final Trade trade5 = new Trade(LocalDateTime.parse("2017-05-10T10:00:08"),"National Planning Corporation", 4, "K", "LGHT", 800L, 140D, Buy);


  @Test
  void shouldRemoveDuplicateIds() {
    //given
    BrokerTrades brokerTrades = new BrokerTrades(Arrays.asList(trade3, trade2, trade1));

    //when
    brokerTrades.rejectRepeatIds();

    //then
    assertThat(brokerTrades.getAccepted()).containsExactly(trade1, trade3);
    assertThat(brokerTrades.getRejected()).containsExactly(trade2);
  }

  @Test
  void shouldRemoveRedundantTradesBasedOnTime() {
    //given
    BrokerTrades brokerTrades = new BrokerTrades(Arrays.asList(trade3, trade5, trade1, trade4));

    //when
    brokerTrades.rejectBasedOnTime();

    //then
    assertThat(brokerTrades.getAccepted()).containsExactly(trade1, trade3, trade4);
    assertThat(brokerTrades.getRejected()).containsExactly(trade5);
  }

  @Test
  void shouldRemoveAllRedundantTrades() {
    //given
    BrokerTrades brokerTrades = new BrokerTrades(Arrays.asList(trade3, trade2, trade5, trade1, trade4));

    //when
    brokerTrades.rejectRepeatIds();
    brokerTrades.rejectBasedOnTime();

    //then
    assertThat(brokerTrades.getAccepted()).containsExactly(trade1, trade3, trade4);
    assertThat(brokerTrades.getRejected()).containsExactly(trade2, trade5);
  }

}
