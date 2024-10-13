package com.ezcatcat;

/**
 * @Author: EzCatcat
 * @Date: 2024/10/13 16:48
 */

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class NextDateCalculatorTest {
    private NextDateCalculator frame;

    @BeforeEach
    public void setUp() {
        frame = new NextDateCalculator();
        frame.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        frame.dispose(); // 关闭窗口
    }

    @ParameterizedTest
    @CsvSource({
            "2000,1,1,2000-01-02",
            "2000,1,31,2000-02-01",
            "2000,4,30,2000-05-01",
            "2000,2,28,2000-02-29",
            "2000,2,29,2000-03-01",
            "2001,2,28,2001-03-01",
            "1799,1,1,'输入错误：日期必须在 1800-01-01 到 2050-12-31 之间。'",
            "2000,0,1,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "2000,13,1,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "2000,1,0,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "2000,1,32,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "3000,1,1,'输入错误：日期必须在 1800-01-01 到 2050-12-31 之间。'",
            "a,1,1,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "2000,a,1,'输入错误：请确保年份、月份和日期是有效的数字。'",
            "2000,1,a,'输入错误：请确保年份、月份和日期是有效的数字。'"
    })

    public void testCalculateNextDay(String year, String month, String day, String expectedNextDate) {
        // 设置输入
        frame.yearField.setText(String.valueOf(year));
        frame.monthField.setText(String.valueOf(month));
        frame.dayField.setText(String.valueOf(day));

        // 触发计算
        frame.calculateNextDay();

        // 获取输出
        String actualOutput = frame.outputField.getText();

        // 检查输出是否符合预期
        assertEquals(expectedNextDate, actualOutput);
    }

}
