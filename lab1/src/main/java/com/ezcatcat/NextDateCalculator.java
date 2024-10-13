package com.ezcatcat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: EzCatcat
 * @Date: 2024/10/13 16:48
 */
public class NextDateCalculator extends JFrame {
    public JTextField yearField;
    public JTextField monthField;
    public JTextField dayField;
    public JTextField outputField;

    public NextDateCalculator() {
        setTitle("NEXTDAY");
        setSize(400, 250); // 增加窗口高度
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件之间的间距

        // 今天的日期
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("今天的日期:"), gbc);

        // 年、月、日输入面板
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridBagLayout());
        GridBagConstraints dateGbc = new GridBagConstraints();
        dateGbc.fill = GridBagConstraints.HORIZONTAL;
        dateGbc.insets = new Insets(2, 5, 2, 5); // 调整组件之间的间距

        // 年
        dateGbc.gridx = 0;
        dateGbc.gridy = 0;
        datePanel.add(new JLabel("年:"), dateGbc);

        dateGbc.gridx = 1;
        yearField = new JTextField(5);
        datePanel.add(yearField, dateGbc);

        // 月
        dateGbc.gridx = 2;
        datePanel.add(new JLabel("月:"), dateGbc);

        dateGbc.gridx = 3;
        monthField = new JTextField(5);
        datePanel.add(monthField, dateGbc);

        // 日
        dateGbc.gridx = 4;
        datePanel.add(new JLabel("日:"), dateGbc);

        dateGbc.gridx = 5;
        dayField = new JTextField(5);
        datePanel.add(dayField, dateGbc);

        // 将日期面板加入到主窗口中
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6; // 使日期面板跨6列
        add(datePanel, gbc);

        // 明天的日期标签
        gbc.gridx = 0;
        gbc.gridy = 2; // 更新 y 位置
        gbc.gridwidth = 6; // 跨6列
        add(new JLabel("明天的日期:"), gbc);

        // 输出字段
        gbc.gridy = 3; // 更新 y 位置
        outputField = new JTextField(20);
        outputField.setEditable(false); // 输出框只读
        add(outputField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2)); // 1行2列
        JButton calculateButton = new JButton("计算");
        JButton clearButton = new JButton("清除输入");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 4; // 更新 y 位置
        gbc.gridwidth = 6; // 使按钮面板跨6列
        add(buttonPanel, gbc);

        // 确定和取消按钮
        JPanel confirmCancelPanel = new JPanel();
        confirmCancelPanel.setLayout(new GridLayout(1, 2)); // 1行2列
        JButton confirmButton = new JButton("确定");
        JButton cancelButton = new JButton("取消");
        confirmCancelPanel.add(confirmButton);
        confirmCancelPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 5; // 更新 y 位置
        gbc.gridwidth = 6; // 使确定和取消按钮跨6列
        add(confirmCancelPanel, gbc);

        // 按钮事件处理
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateNextDay();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        confirmButton.addActionListener(e -> System.exit(0)); // 关闭窗口
        cancelButton.addActionListener(e -> System.exit(0)); // 关闭窗口
    }

    private void calculateNextDay() {
        try {
            int year = Integer.parseInt(yearField.getText().trim());
            int month = Integer.parseInt(monthField.getText().trim());
            int day = Integer.parseInt(dayField.getText().trim());

            LocalDate date = LocalDate.of(year, month, day);

            // 检查日期是否在有效范围内
            if (date.isBefore(LocalDate.of(1800, 1, 1)) || date.isAfter(LocalDate.of(2050, 12, 31))) {
                JOptionPane.showMessageDialog(this, "输入错误：日期必须在 1800-01-01 到 2050-12-31 之间。");
                return;
            }

            // 计算下一天
            LocalDate nextDay = date.plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            outputField.setText(nextDay.format(formatter));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "输入错误：请确保年份、月份和日期是有效的数字。");
        }
    }

    private void clearFields() {
        yearField.setText("");
        monthField.setText("");
        dayField.setText("");
        outputField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NextDateCalculator frame = new NextDateCalculator();
            frame.setVisible(true);
        });
    }
}

