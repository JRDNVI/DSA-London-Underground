package com.example.assign2_dsa2_dbp_jc;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileReader;
import java.util.List;

public class Parser {
    public static List parseStations() throws Exception {
        HeaderColumnNameMappingStrategy<Station> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Station.class);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/com/example/assign2_dsa2_dbp_jc/data/stations.csv"))
                .withCSVParser(parser)
                .build();

        CsvToBean<Station> csvToBean = new CsvToBeanBuilder<Station>(reader)
                .withType(Station.class)
                .withMappingStrategy(strategy)
                .build();

        List<Station> stations = csvToBean.parse();
        return stations;
    }

    public static List parseZone1Stations() throws Exception {
        HeaderColumnNameMappingStrategy<Zone1Station> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Zone1Station.class);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/com/example/assign2_dsa2_dbp_jc/data/xycoords.csv"))
                .withCSVParser(parser)
                .build();

        CsvToBean<Zone1Station> csvToBean = new CsvToBeanBuilder<Zone1Station>(reader)
                .withType(Zone1Station.class)
                .withMappingStrategy(strategy)
                .build();

        List<Zone1Station> stations = csvToBean.parse();
        return stations;
    }

    public List parseRoutes() throws Exception {
        HeaderColumnNameMappingStrategy<Route> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Route.class);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/com/example/assign2_dsa2_dbp_jc/data/routes.csv"))
                .withCSVParser(parser)
                .build();

        CsvToBean<Route> csvToBean = new CsvToBeanBuilder<Route>(reader)
                .withType(Route.class)
                .withMappingStrategy(strategy)
                .build();

        List<Route> routes = csvToBean.parse();
        return routes;
    }

    public static List parseLineDefinitions() throws Exception {
        HeaderColumnNameMappingStrategy<LineDefinition> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(LineDefinition.class);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/com/example/assign2_dsa2_dbp_jc/data/linedefinitions.csv"))
                .withCSVParser(parser)
                .build();

        CsvToBean<LineDefinition> csvToBean = new CsvToBeanBuilder<LineDefinition>(reader)
                .withType(LineDefinition.class)
                .withMappingStrategy(strategy)
                .build();

        List<LineDefinition> lineDefinitions = csvToBean.parse();
        return lineDefinitions;
    }
}
