package org.example.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilExcel {
    //Open Stream
    //Open workbook
    //Open Sheet
    //Find Cell from Row,Data and itearte it using for loop
    //Close Stream

    static Workbook book;
    static Sheet sheet;

    public static String file_name="src/test/resources/Book1.xlsx";

    public static Object[][] getTestData(String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(file_name);

        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (EncryptedDocumentException e) {
            throw new RuntimeException(e);
        }

        sheet = book.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        //To iterate rows and column, do for loop
        for(int i =0; i<sheet.getLastRowNum();i++)
        {
            for(int j=0; j<sheet.getRow(0).getLastCellNum();j++)
            {
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;

    }




    @DataProvider
    public Object[][] getData() throws IOException {
        return getTestData("Sheet1");

    }

}
