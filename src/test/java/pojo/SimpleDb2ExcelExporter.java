package test.java.pojo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

/**
 * A simple Java program that exports data from database to Excel file.
 * @author Nam Ha Minh
 * (C) Copyright codejava.net
 */
public class SimpleDb2ExcelExporter {

    public static void main(String[] args) {
        new SimpleDb2ExcelExporter().export();
    }

    public void export() {
        String jdbcURL = "jdbc:mysql://localhost:3306/eai_cai_prod_d3584eed_gcp_db_cai";
        String username = "cai-prod-devv2-gcp-user-rw";
        String password = "6S29#HKkkAt*";

        String excelFilePath = "Reviews-export.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT CONTRACT_ID,FILE_NAME,LEGAL_ENTITY_NAME_1,LEGAL_ENTITY_NAME_2,EFFECTIVE_DATE,EXPIRATION_DATE,CONTRACT_TYPE,PRICE_INCREASE,CYBER_SECURITY,PAYMENT_TERMS,\n" +
                    "PARENT_AGREEMENT_NAME,AUTO_RENEWAL,PRICING, VOLUME_REBATES,TERMINATION_CLAUSE,TERMINATION_FOR_CAUSE,TERMINATION_FOR_CONVENIENCE,LIMITATION_OF_LIABILITY,INDEMNIFICATION,ASSIGNMENT,AUDIT,GOVERNING_LAW,\n" +
                    "INTELLECTUAL_PROPERTY_RIGHTS,DISPUTE_RESOLUTION FROM eai_cai_prod_d3584eed_gcp_db_cai.CAI_CONTRACT_EXPLORER_V where CONTRACT_TYPE!='NA'";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");

            writeHeaderLine(sheet);

            writeDataLines(result, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();

            statement.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Course Name");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Student Name");

        //headerCell = headerRow.createCell(2);
        //headerCell.setCellValue("Timestamp");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Rating");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Comment");
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;

        while (result.next()) {
            String courseName = result.getString("CONTRACT_ID");
            String studentName = result.getString("FILE_NAME");
            float rating = result.getFloat("LEGAL_ENTITY_NAME_1");
            //Timestamp timestamp = result.getTimestamp("timestamp");
            String comment = result.getString("LEGAL_ENTITY_NAME_2");

            Row row = sheet.createRow(rowCount++);

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(courseName);

            cell = row.createCell(columnCount++);
            cell.setCellValue(studentName);

            cell = row.createCell(columnCount++);

            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);

            //cell.setCellValue(timestamp);

            cell = row.createCell(columnCount++);
            cell.setCellValue(rating);

            cell = row.createCell(columnCount);
            cell.setCellValue(comment);

        }
    }

}
