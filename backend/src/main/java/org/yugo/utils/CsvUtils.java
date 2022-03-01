package org.yugo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        if (csvData.size() == 0) {
            return data;
        }

        // Take CSV header row and header column map to match attributes indexes on parse
        var csvHeader = csvData.remove(0);
        var csvHeaderMap = new HashMap<String, Integer>();
        for (int i=0; i<csvHeader.length; ++i) {
            csvHeaderMap.put(csvHeader[i], i);
        }

        // Parse each row with the given csvRowParser function
        for (String[] row : csvData) {
            data.add(csvRowParser.apply(csvHeaderMap, row));
        }

        return data;
    }

    public static List<String[]> getCsvData(String filePath) {
        List<String[]> csvData = Collections.emptyList();
    
        var inputStreamReader = new InputStreamReader(CsvUtils.class.getClassLoader().getResourceAsStream(filePath));
        var bufferedReader = new BufferedReader(inputStreamReader);

        try (CSVReader csvReader = new CSVReader(bufferedReader)) {
            csvData = csvReader.readAll();
        }
        catch (CsvException | IOException e) {
            System.err.println(e.getMessage());
        }
        return csvData;
    }
    
}
