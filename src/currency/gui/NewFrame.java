package currency.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class NewFrame {

    public static void getFrame(String[][] rates) {

        JFrame frame = new JFrame();
        frame.setTitle("Курс рубля");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Код валюты", "Цена"};
        JTable table = new JTable(rates, columnNames);

        JTableHeader header = table.getTableHeader();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        header.setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(table.getRowHeight() + 18);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        frame.pack(); // установить размер в котором помесятся все компоненты
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
