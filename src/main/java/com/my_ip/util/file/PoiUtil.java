package com.my_ip.util.file;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PoiUtil {

    public static Workbook getWorkbook(String filePath){
        try {
            return WorkbookFactory.create(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Workbook getWorkbook(InputStream is) {
        try {
            return WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
