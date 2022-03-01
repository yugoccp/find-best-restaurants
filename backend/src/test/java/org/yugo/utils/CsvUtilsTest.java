package org.yugo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CsvUtilsTest {
    
    @Test
    public void When_ValiFilePath_Should_LoadData() {
        var data = CsvUtils.getCsvData("data/cuisines.csv");

        Assertions.assertNotNull(data, "Should not be null");
        Assertions.assertTrue(data.size() > 0, "Should not be empty");
    }
}
