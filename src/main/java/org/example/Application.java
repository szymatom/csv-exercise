package org.example;

public class Application {
  public static void main(String[] args) {
    TradeClassificationEngine engine = new TradeClassificationEngine(args[0]);
    engine.classify();
  }
}
