package org.example;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.List;

import io.vavr.control.Try;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.csv.CSVFormat.EXCEL;

class CsvFileParser {
  private final File file;

  public CsvFileParser(String path) {
    this.file = new File(path);
  }

  List<CSVRecord> parse() {
     return Try.of(() -> CSVParser.parse(file, UTF_8, EXCEL.withFirstRecordAsHeader()).getRecords())
         .getOrElseThrow(() -> new RuntimeException("Csv file not found"));
  }
}
