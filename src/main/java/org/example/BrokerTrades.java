package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import lombok.Getter;

@Getter
public class BrokerTrades {
  private final List<Trade> accepted;
  private final List<Trade> rejected;

  BrokerTrades() {
    this.accepted = new ArrayList<>();
    this.rejected = new ArrayList<>();
  }

  BrokerTrades(List<Trade> accepted) {
    this();
    this.accepted.addAll(accepted);
    Collections.sort(this.accepted);
  }

  void rejectRepeatIds() {
    Set<Integer> ids = new HashSet<>();
    ListIterator<Trade> iter = accepted.listIterator();
    while (iter.hasNext()) {
      Trade trade = iter.next();
      if (ids.contains(trade.getSequenceId())) {
        rejected.add(trade);
        iter.remove();
      } else {
        ids.add(trade.getSequenceId());
      }
    }
  }

  void rejectBasedOnTime() {
    for (int i = 0; i < accepted.size() - 1; i++) {
      Trade current = accepted.get(i);
      int counter = 0;
      if (rejected.contains(current)) {
        continue;
      }
      for (int j = i + 1; j < accepted.size(); j++) {
        Trade next = accepted.get(j);
        if (rejected.contains(next) || next.getTime().isAfter(current.getTime().plusMinutes(1))) {
          break;
        }
        if (counter++ >= 2) {
          rejected.add(next);
        }
      }
    }
    Collections.sort(rejected);
    accepted.removeAll(rejected);
  }
}
