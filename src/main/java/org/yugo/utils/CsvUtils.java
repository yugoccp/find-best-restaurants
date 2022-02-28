package org.yugo.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvUtils {

    public static <T> List<T> parseCsvData(List<String[]> csvData, BiFunction<Map<String, Integer>, String[], T> csvRowParser) {
        var data = new ArrayList<T>();
        var csvHeader = csvData.remove(0);
        
        var csvHeaderMap = new HashMap<String, Integer>();
        for (int i=0; i<csvHeader.length; ++i) {
            csvHeaderMap.put(csvHeader[i], i);
        }

        for (String[] row : csvData) {
            data.add(csvRowParser.apply(csvHeaderMap, row));
        }

        return data;
    }

    public static List<String[]> getCsvData(String filePath) {
        List<String[]> csvData = Collections.emptyList();
        var file = Thread.currentThread().getContextClassLoader().getResource(filePath).getFile();
        try (FileReader filereader = new FileReader(file)) {
            try (CSVReader csvReader = new CSVReader(filereader)) {
                csvData = csvReader.readAll();
            }
        
        } catch (CsvException | IOException e) {
            System.err.println(e.getMessage());
        }
        return csvData;
    }
    
}
