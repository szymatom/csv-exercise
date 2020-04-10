package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TradeClassificationEngineTest {

  @Test
  void shouldRemoveDuplicateIds() {
    //given
    TradeClassificationEngine engine = new TradeClassificationEngine("src/test/resources/trades.csv");

    //when
    engine.classify();

    //then
    assertThat(engine.getTradesByBroker().get("AXA Advisors").getAccepted()).hasSize(49);
    assertThat(engine.getTradesByBroker().get("AXA Advisors").getRejected()).hasSize(1);
  }


}
