package org.example;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.EnumUtils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
class Trade implements Comparable<Trade> {
  private final LocalDateTime time;
  private final String broker;
  private final Integer sequenceId;
  private final String type;
  private final String symbol;
  private final Long quantity;
  private final Double price;
  private final Side side;

  Trade(CSVRecord record) {
    this.time = LocalDateTime.parse(record.get("Time stamp"), ofPattern("dd/M/yyyy HH:mm:ss"));
    this.broker = record.get("broker");
    this.sequenceId = Integer.valueOf(record.get("sequence id"));
    this.type = record.get("type");
    this.symbol = record.get("Symbol");
    this.quantity = Long.valueOf(record.get("Quantity"));
    this.price = Double.valueOf(record.get("Price"));
    this.side = EnumUtils.getEnum(Side.class, record.get("Side"));
  }

  boolean isValid() {
    return nonNull(time) && isNotBlank(broker) && nonNull(sequenceId) && isNotBlank(type) && isNotBlank(symbol) && nonNull(quantity) && nonNull(price) && nonNull(side);
  }

  @Override
  public int compareTo(Trade o) {
    return this.time.compareTo(o.getTime());
  }
}

enum Side {
  Buy, Sell
}