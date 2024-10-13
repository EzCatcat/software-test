package com.ezcatcat;

/**
 * @Author: EzCatcat
 * @Date: 2024/10/13 16:48
 */

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.swing.*;

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

    //    @Test
//    public void testCalculateNextDay() {
//        frame.yearField.setText("2024");
//        frame.monthField.setText("10");
//        frame.dayField.setText("13");
//
//        // 点击计算按钮
//        JButton calculateButton = (JButton) ((JPanel) frame.getContentPane().getComponent(4)).getComponent(0);
//        calculateButton.doClick(); // 模拟按钮点击
//
//        // 验证输出
//        assertEquals("2024-10-14", frame.outputField.getText());
//    }
    @ParameterizedTest
    @CsvSource({
            "2000,1,1,2000-01-02",
            "2000,1,31,2000-02-01",
            "2000,4,30,2000-05-01",
            "2000,2,28,2000-02-29",
            "2000,2,29,2000-03-01",
            "2001,2,28,2001-03-01",
            "1799,1,1,",
            "2000,0,1,",
            "2000,13,1,",
            "2000,1,0,",
            "2000,1,32,",
            "3000,1,1,",
            "a,1,1,",
            "2000,a,1,",
            "2000,1,a,"
    })

    public void testCalculateNextDay(int year, int month, int day, String expected) {
        frame.yearField.setText(String.valueOf(year));
        frame.monthField.setText(String.valueOf(month));
        frame.dayField.setText(String.valueOf(day));

        // 点击计算按钮
        JButton calculateButton = (JButton) ((JPanel) frame.getContentPane().getComponent(4)).getComponent(0);
        calculateButton.doClick(); // 模拟按钮点击

        // 验证输出
        assertEquals(expected, frame.outputField.getText());
    }

}
