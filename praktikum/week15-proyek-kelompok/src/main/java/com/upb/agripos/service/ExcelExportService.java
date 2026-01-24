package com.upb.agripos.service;

import com.upb.agripos.model.Transaction;
import com.upb.agripos.model.CartItem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExcelExportService {
    
    /**
     * Export transactions to Excel file
     * @param transactions List of transactions to export
     * @param filePath Path where the Excel file will be saved
     * @return true if successful, false otherwise
     */
    public boolean exportTransactionsToExcel(List<Transaction> transactions, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            
            // Create sheet
            Sheet sheet = workbook.createSheet("Laporan Penjualan");
            
            // Set column widths
            sheet.setColumnWidth(0, 8 * 256);      // No
            sheet.setColumnWidth(1, 15 * 256);     // Tanggal
            sheet.setColumnWidth(2, 15 * 256);     // Metode Pembayaran
            sheet.setColumnWidth(3, 30 * 256);     // Detail Produk
            sheet.setColumnWidth(4, 15 * 256);     // Jumlah
            
            // Create styles
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle borderStyle = createBorderStyle(workbook);
            
            // Create title
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("LAPORAN PENJUALAN DETAIL");
            titleCell.setCellStyle(createTitleStyle(workbook));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            
            // Create subtitle with export date
            Row subtitleRow = sheet.createRow(1);
            Cell subtitleCell = subtitleRow.createCell(0);
            subtitleCell.setCellValue("Tanggal Export: " + LocalDateTime.now());
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            
            // Create header row
            Row headerRow = sheet.createRow(3);
            String[] headers = {"No", "Tanggal", "Metode Pembayaran", "Detail Produk", "Jumlah (Rp)"};
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Add data rows
            int rowNum = 4;
            int itemNo = 1;
            double totalAmount = 0;
            int totalTransactions = 0;
            
            for (Transaction transaction : transactions) {
                Row row = sheet.createRow(rowNum);
                
                // No
                Cell cellNo = row.createCell(0);
                cellNo.setCellValue(itemNo++);
                cellNo.setCellStyle(borderStyle);
                
                // Tanggal
                Cell cellDate = row.createCell(1);
                cellDate.setCellValue(transaction.getFormattedDate());
                cellDate.setCellStyle(borderStyle);
                
                // Metode Pembayaran
                Cell cellPayment = row.createCell(2);
                cellPayment.setCellValue(transaction.getPaymentMethod());
                cellPayment.setCellStyle(borderStyle);
                
                // Detail Produk
                Cell cellDetail = row.createCell(3);
                StringBuilder details = new StringBuilder();
                int detailNo = 1;
                for (CartItem item : transaction.getItems()) {
                    if (details.length() > 0) details.append("; ");
                    details.append(detailNo++).append(". ")
                           .append(item.getProduct().getName())
                           .append(" x").append(item.getQty());
                }
                cellDetail.setCellValue(details.toString());
                cellDetail.setCellStyle(borderStyle);
                
                // Jumlah
                Cell cellAmount = row.createCell(4);
                cellAmount.setCellValue(transaction.getTotal());
                cellAmount.setCellStyle(currencyStyle);
                
                totalAmount += transaction.getTotal();
                totalTransactions++;
                rowNum++;
            }
            
            // Add summary section
            rowNum++;
            Row summaryHeaderRow = sheet.createRow(rowNum);
            Cell summaryHeaderCell = summaryHeaderRow.createCell(0);
            summaryHeaderCell.setCellValue("RINGKASAN");
            summaryHeaderCell.setCellStyle(createTitleStyle(workbook));
            
            rowNum++;
            Row totalRow = sheet.createRow(rowNum);
            Cell totalLabelCell = totalRow.createCell(3);
            totalLabelCell.setCellValue("Total Penjualan:");
            totalLabelCell.setCellStyle(createBoldStyle(workbook));
            
            Cell totalValueCell = totalRow.createCell(4);
            totalValueCell.setCellValue(totalAmount);
            totalValueCell.setCellStyle(currencyStyle);
            
            rowNum++;
            Row countRow = sheet.createRow(rowNum);
            Cell countLabelCell = countRow.createCell(3);
            countLabelCell.setCellValue("Jumlah Transaksi:");
            countLabelCell.setCellStyle(createBoldStyle(workbook));
            
            Cell countValueCell = countRow.createCell(4);
            countValueCell.setCellValue(totalTransactions);
            countValueCell.setCellStyle(borderStyle);
            
            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Export summary report to Excel
     * @param transactions List of transactions
     * @param filePath Path where the Excel file will be saved
     * @return true if successful
     */
    public boolean exportSummaryToExcel(List<Transaction> transactions, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            
            Sheet sheet = workbook.createSheet("Ringkasan Penjualan");
            sheet.setColumnWidth(0, 25 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle borderStyle = createBorderStyle(workbook);
            
            // Title
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("RINGKASAN LAPORAN PENJUALAN");
            titleCell.setCellStyle(createTitleStyle(workbook));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
            
            // Calculate summary
            double totalCash = 0;
            double totalEWallet = 0;
            double totalAmount = 0;
            
            for (Transaction trans : transactions) {
                totalAmount += trans.getTotal();
                if ("Tunai".equals(trans.getPaymentMethod())) {
                    totalCash += trans.getTotal();
                } else {
                    totalEWallet += trans.getTotal();
                }
            }
            
            // Summary rows
            int rowNum = 2;
            
            // Header for summary
            Row headerRow = sheet.createRow(rowNum);
            headerRow.createCell(0).setCellValue("Metode Pembayaran");
            headerRow.createCell(1).setCellValue("Total (Rp)");
            headerRow.getCell(0).setCellStyle(headerStyle);
            headerRow.getCell(1).setCellStyle(headerStyle);
            
            rowNum++;
            
            // Tunai
            Row tunaiRow = sheet.createRow(rowNum);
            tunaiRow.createCell(0).setCellValue("Tunai");
            Cell tunaiValueCell = tunaiRow.createCell(1);
            tunaiValueCell.setCellValue(totalCash);
            tunaiValueCell.setCellStyle(currencyStyle);
            tunaiRow.getCell(0).setCellStyle(borderStyle);
            
            rowNum++;
            
            // E-Wallet
            Row ewalletRow = sheet.createRow(rowNum);
            ewalletRow.createCell(0).setCellValue("E-Wallet");
            Cell ewalletValueCell = ewalletRow.createCell(1);
            ewalletValueCell.setCellValue(totalEWallet);
            ewalletValueCell.setCellStyle(currencyStyle);
            ewalletRow.getCell(0).setCellStyle(borderStyle);
            
            rowNum += 2;
            
            // Grand Total
            Row totalRow = sheet.createRow(rowNum);
            Cell totalLabelCell = totalRow.createCell(0);
            totalLabelCell.setCellValue("TOTAL PENJUALAN");
            totalLabelCell.setCellStyle(createBoldStyle(workbook));
            
            Cell totalValueCell = totalRow.createCell(1);
            totalValueCell.setCellValue(totalAmount);
            totalValueCell.setCellStyle(createTotalStyle(workbook));
            
            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ===================== STYLE HELPERS =====================
    
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    
    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
    
    private CellStyle createBoldStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    
    private CellStyle createBorderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    
    private CellStyle createCurrencyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    
    private CellStyle createTotalStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        style.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
}
