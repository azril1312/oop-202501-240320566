# Arsitektur Export Laporan ke Excel

## Diagram Alur Sistem

```
┌─────────────────────────────────────────────────────────────┐
│                   APLIKASI AGRIPOS (UI Layer)               │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │              ReportView.java                          │   │
│  │  ┌──────────────────────────────────────────────┐   │   │
│  │  │ - TableView<TransactionReport>               │   │   │
│  │  │ - Filter (Tanggal, Metode Pembayaran)      │   │   │
│  │  │ - Summary Panel                             │   │   │
│  │  │ - Detail View                               │   │   │
│  │  │ - [Export Laporan Button] ◄─────────┐      │   │   │
│  │  └──────────────────────────────────────────────┘   │   │
│  └────────────────────┬─────────────────────────────────┘   │
│                       │                                       │
│                       │ handleExportExcel()                  │
│                       ▼                                       │
│  ┌────────────────────────────────────────┐                 │
│  │    FileChooser Dialog                  │                 │
│  │  - Select Folder                       │                 │
│  │  - Enter Filename                      │                 │
│  │  - Filter: *.xlsx                      │                 │
│  └────────────────────┬───────────────────┘                 │
│                       │                                       │
└───────────────────────┼───────────────────────────────────────┘
                        │ File Path
                        ▼
┌─────────────────────────────────────────────────────────────┐
│           SERVICE LAYER                                      │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  ExcelExportService.java                             │   │
│  │                                                       │   │
│  │  + exportTransactionsToExcel(                         │   │
│  │       List<Transaction> transactions,                │   │
│  │       String filePath                                │   │
│  │    ) : boolean                                        │   │
│  │                                                       │   │
│  │  + exportSummaryToExcel(                             │   │
│  │       List<Transaction> transactions,                │   │
│  │       String filePath                                │   │
│  │    ) : boolean                                        │   │
│  │                                                       │   │
│  │  - createHeaderStyle()                               │   │
│  │  - createCurrencyStyle()                             │   │
│  │  - createTitleStyle()                                │   │
│  │  - ... [Other style methods]                         │   │
│  └──────────────────────────────────────────────────────┘   │
│                       │                                       │
└───────────────────────┼───────────────────────────────────────┘
                        │ Uses Apache POI
                        ▼
┌─────────────────────────────────────────────────────────────┐
│        APACHE POI LIBRARY (3rd Party)                        │
│                                                               │
│  - XSSFWorkbook (Excel 2007+ .xlsx format)                  │
│  - Sheet, Row, Cell objects                                 │
│  - CellStyle & Font styling                                 │
│  - DataFormat (Currency formatting)                         │
│                                                               │
│  Dependencies:                                               │
│  - org.apache.poi:poi:5.2.5                                 │
│  - org.apache.poi:poi-ooxml:5.2.5                           │
└─────────────────────────────────────────────────────────────┘
                        │
                        ▼
┌─────────────────────────────────────────────────────────────┐
│          FILE SYSTEM (Local Disk)                            │
│                                                               │
│  📄 Laporan_Penjualan_2026-01-21.xlsx                       │
│     ├─ Sheet: "Laporan Penjualan"                           │
│     │  ├─ Header Section                                    │
│     │  ├─ Data Table (Transactions)                         │
│     │  └─ Summary Section                                   │
│     └─ Metadata                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## Class Diagram

```
┌─────────────────────────────┐
│      ReportView             │
├─────────────────────────────┤
│ - ts: TransactionService    │
│ - excelExportService: Exc..  │
│ - table: TableView<..>      │
│ - filterBox: HBox           │
│ - summaryBox: VBox          │
├─────────────────────────────┤
│ + ReportView(Stage, User)   │
│ - handleExportExcel(..):void│
│ - loadRealTransactions(..):void
│ - applyFilter(..):void      │
│ - createSummaryPanel(..):VBox
└──────────────┬──────────────┘
               │ uses
               ▼
┌─────────────────────────────┐
│   ExcelExportService        │
├─────────────────────────────┤
│ - excelExportService: Excel │
├─────────────────────────────┤
│ + exportTransactionsToExcel │
│   (List, String): boolean   │
│ + exportSummaryToExcel      │
│   (List, String): boolean   │
│ - createHeaderStyle(): Style│
│ - createTitleStyle(): Style │
│ - createCurrencyStyle(): ... │
│ - createBoldStyle(): Style  │
│ - createBorderStyle(): Style│
│ - createTotalStyle(): Style │
└──────────────┬──────────────┘
               │ uses
               ▼
┌─────────────────────────────┐
│    Apache POI Library       │
├─────────────────────────────┤
│ - XSSFWorkbook              │
│ - Sheet                     │
│ - Row                       │
│ - Cell                      │
│ - CellStyle                 │
│ - Font                      │
│ - DataFormat                │
└─────────────────────────────┘
```

---

## Data Flow Sequence

```
User clicks "Export Laporan" Button
    │
    ▼
ReportView.handleExportExcel(TableView)
    │
    ├─ Check: is table empty?
    │  ├─ YES: Show warning alert → Return
    │  └─ NO: Continue
    │
    ▼
FileChooser.showSaveDialog(window)
    │
    ├─ User selects folder & filename
    │  ├─ User clicks CANCEL: Return (no export)
    │  └─ User clicks SAVE: Get selectedFile
    │
    ▼
Extract transactions from table items
    │
    ├─ For each TransactionReport in table:
    │  └─ Get underlying Transaction object
    │
    ▼
ExcelExportService.exportTransactionsToExcel(
    transactions, 
    filePath
)
    │
    ├─ Create XSSFWorkbook
    │  │
    │  ├─ Create Sheet "Laporan Penjualan"
    │  │
    │  ├─ Create Title Section
    │  │  ├─ Row 0: "LAPORAN PENJUALAN DETAIL"
    │  │  └─ Row 1: "Tanggal Export: ..."
    │  │
    │  ├─ Create Header Row (Row 3)
    │  │  ├─ Col A: "No"
    │  │  ├─ Col B: "Tanggal"
    │  │  ├─ Col C: "Metode Pembayaran"
    │  │  ├─ Col D: "Detail Produk"
    │  │  └─ Col E: "Jumlah (Rp)"
    │  │  (Apply headerStyle to all)
    │  │
    │  ├─ Add Data Rows (Starting Row 4)
    │  │  └─ For each transaction:
    │  │     ├─ Add No
    │  │     ├─ Add Tanggal
    │  │     ├─ Add Metode Pembayaran
    │  │     ├─ Add Detail (item names & qty)
    │  │     └─ Add Jumlah (with currencyStyle)
    │  │
    │  ├─ Add Summary Section
    │  │  ├─ "RINGKASAN" header
    │  │  ├─ "Total Penjualan" row (with totalStyle)
    │  │  └─ "Jumlah Transaksi" row
    │  │
    │  └─ Write Workbook to FileOutputStream
    │
    ▼
return true/false (success status)
    │
    ▼
Show Success Alert
    │
    ├─ Display: "File saved successfully!"
    │           "Filename: ..."
    │           "Location: ..."
    │
    └─ Desktop.getDesktop().open(selectedFile)
       (Automatically open file in Excel/Calc)
```

---

## Component Integration

```
┌──────────────────────────────────────────────────────────┐
│                    DATABASE LAYER                        │
│  (TransactionDAO → Transaction Objects)                  │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│               SERVICE LAYER                              │
│  - TransactionService (Get history)                      │
│  - ExcelExportService (Export to Excel)                  │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│                VIEW LAYER                                │
│  - ReportView (Display & Export UI)                      │
│  - FileChooser (Select export location)                  │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│            3RD PARTY LIBRARIES                           │
│  - Apache POI (Excel generation)                         │
│  - JavaFX (UI Framework)                                 │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│               FILE SYSTEM / USER                         │
│  - .xlsx file saved to disk                              │
│  - User opens file in Excel                              │
└──────────────────────────────────────────────────────────┘
```

---

## Technology Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **UI** | JavaFX 17 | User Interface |
| **UI Dialogs** | FileChooser | File selection |
| **Service** | Java 17 | Business Logic |
| **Excel Generation** | Apache POI 5.2.5 | .xlsx format generation |
| **Styling** | POI CellStyle API | Professional formatting |
| **File I/O** | Java NIO | File writing |
| **OS Integration** | Desktop API | Auto-open Excel files |

---

## Design Patterns Used

### 1. **Service Layer Pattern**
- `ExcelExportService` encapsulates export logic
- Separated from UI concerns (ReportView)
- Reusable across application

### 2. **Model-View-Controller (MVC)**
- Model: `Transaction`, `CartItem`, `Product`
- View: `ReportView`, `FileChooser`
- Controller: Logic in service layer

### 3. **Builder Pattern**
- POI's Workbook/Sheet/Cell creation uses fluent API
- Styles created with factory methods

### 4. **Strategy Pattern**
- `exportTransactionsToExcel()` - detail export strategy
- `exportSummaryToExcel()` - summary export strategy

### 5. **Delegation**
- ReportView delegates export to ExcelExportService
- ExcelExportService delegates formatting to POI

---

## File Organization

```
week15-proyek-kelompok/
├── src/
│   ├── main/
│   │   └── java/com/upb/agripos/
│   │       ├── view/
│   │       │   └── ReportView.java ◄─── Updated
│   │       └── service/
│   │           └── ExcelExportService.java ◄─── New
│   │
│   └── test/
│       └── java/com/upb/agripos/service/
│           └── ExcelExportServiceTest.java ◄─── New
│
├── pom.xml ◄─── Updated
├── QUICKSTART.md ◄─── New
├── EXCEL_EXPORT_GUIDE.md ◄─── New
├── IMPLEMENTATION_SUMMARY.md ◄─── New
└── ARCHITECTURE.md ◄─── This file
```

---

## Performance Considerations

### Time Complexity
- Creating workbook: O(1)
- Adding rows: O(n) where n = number of transactions
- Writing to file: O(n)
- **Total**: O(n)

### Space Complexity
- In-memory workbook: O(n) where n = number of transactions
- File size: ~50-100 bytes per transaction

### Optimization Tips
- For large datasets (>10k transactions), consider pagination
- Pre-filter data before export to reduce file size
- Use summary export for high-level reports

---

## Error Handling

```
Export Process Error Handling
├─ Empty table check
│  └─ Show warning: "No data to export"
│
├─ FileChooser canceled
│  └─ Silent return (user didn't select file)
│
├─ File write error
│  └─ Show error: "Failed to export report"
│  └─ Catch IOException
│
├─ File open error
│  └─ Silent (file is saved but not opened)
│  └─ Catch Exception from Desktop.open()
│
└─ Data inconsistency
   └─ Fallback to create dummy transactions from report data
```

---

## Future Enhancements

1. **Multi-sheet export**: Separate sheets for detail & summary
2. **Custom templates**: User-defined Excel layouts
3. **PDF export**: Alternative to Excel format
4. **Email integration**: Send export via email
5. **Scheduled exports**: Automatic daily/weekly exports
6. **Charts/Graphs**: Auto-generated Excel charts
7. **Pivot tables**: Summary pivot tables
8. **Encryption**: Password-protected exports

---

## Conclusion

Sistem export laporan ke Excel dirancang dengan:
- ✅ Clean architecture (separation of concerns)
- ✅ Reusable service components
- ✅ Professional styling & formatting
- ✅ User-friendly interface
- ✅ Robust error handling
- ✅ Extensible design

Sistem siap untuk production dan dapat dikembangkan sesuai kebutuhan.
