package com.hta.tuitionmanagement.utils.excel;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.hta.tuitionmanagement.constants.FieldType;

import static com.hta.tuitionmanagement.utils.DataUtils.readFileResource;
import static com.hta.tuitionmanagement.utils.DataUtils.readInputStreamResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExcelReader {

    final static SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    // final static SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");

    private ExcelReader() {
    }

    public static Workbook readExcel(final MultipartFile file) throws IOException {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file.getInputStream());
        } catch (InvalidFormatException e) {
            log.error((e.getMessage()), e);
        }
        return workbook;
    }

    public static Workbook readExcelFromResource(final String fullFilePath) throws InvalidFormatException, Exception {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(readInputStreamResource(fullFilePath));
        } catch (InvalidFormatException e) {
            log.error((e.getMessage()), e);
        }
        return workbook;
    }

    public static Workbook readExcel(final String fullFilePath) throws IOException, InvalidFormatException {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(fullFilePath));
        } catch (InvalidFormatException e) {
            log.error((e.getMessage()), e);
        }
        return workbook;
    }

    public static Map<String, List<ExcelFieldDTO[]>> getExcelRowValues(final Sheet sheet, String jsonPath, int begin) {
        Map<String, List<ExcelFieldDTO[]>> excelMap = new HashMap<>();
        Map<String, ExcelFieldDTO[]> excelSectionHeaders = getExcelHeaderSections(jsonPath);
        int totalRows = sheet.getLastRowNum();
        excelSectionHeaders.forEach((section, excelFields) -> {
            List<ExcelFieldDTO[]> excelFieldList = new ArrayList<>();
            for (int i = begin; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                ExcelFieldDTO[] excelFieldArr = new ExcelFieldDTO[excelFields.length];
                int k = 0;
                for (ExcelFieldDTO ehc : excelFields) {
                    int cellIndex = ehc.getExcelIndex();
                    String cellType = ehc.getExcelColType();
                    Cell cell = row.getCell(cellIndex);
                    ExcelFieldDTO excelField = new ExcelFieldDTO();
                    excelField.setExcelColType(ehc.getExcelColType());
                    excelField.setExcelHeader(ehc.getExcelHeader());
                    excelField.setExcelIndex(ehc.getExcelIndex());
                    excelField.setPojoAttribute(ehc.getPojoAttribute());
                    if (cell == null) {
                        excelField.setExcelValue("");
                        continue;
                    }
                    if (FieldType.STRING.getValue().equalsIgnoreCase(cellType)) {
                        try {
                            excelField.setExcelValue(cell.getStringCellValue());
                        } catch (Exception ex) {
                            excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
                        }
                    } else if (FieldType.DOUBLE.getValue().equalsIgnoreCase(cellType)
                            || FieldType.INTEGER.getValue().equalsIgnoreCase(cellType)) {
                        try {
                            excelField.setExcelValue(String.valueOf(cell.getNumericCellValue()));
                        } catch (Exception ex) {
                            excelField.setExcelValue(cell.getStringCellValue());
                        }
                    } else if (DateUtil.isCellDateFormatted(cell)) {
                        // excelField.setExcelValue(String.valueOf(dateformat.format(cell.getDateCellValue())));
                        excelField.setExcelValue(String
                                .valueOf(dateformat.format(cell.getDateCellValue(), new StringBuffer(""), new FieldPosition(0))));
                    }
                    excelFieldArr[k++] = excelField;
                }
                excelFieldList.add(excelFieldArr);
            }
            excelMap.put(section, excelFieldList);
        });
        return excelMap;
    }

    private static Map<String, ExcelFieldDTO[]> getExcelHeaderSections(String jsonPath) {
        List<Map<String, List<ExcelFieldDTO>>> jsonConfigMap = getExcelHeaderFieldSections(jsonPath);
        Map<String, ExcelFieldDTO[]> jsonMap = new HashMap<>();
        jsonConfigMap.forEach(jps -> {
            jps.forEach((section, values) -> {
                ExcelFieldDTO[] excelFields = new ExcelFieldDTO[values.size()];
                jsonMap.put(section, values.toArray(excelFields));
            });
        });
        return jsonMap;
    }

    private static List<Map<String, List<ExcelFieldDTO>>> getExcelHeaderFieldSections(String jsonPath) {
        List<Map<String, List<ExcelFieldDTO>>> jsonMap = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonPath = jsonPath == null ? "excel.json" : jsonPath;

            byte[] resource = readFileResource(jsonPath);

            String jsonConfig = new String(resource);

            jsonMap = objectMapper.readValue(jsonConfig, new TypeReference<List<Map<String, List<ExcelFieldDTO>>>>() {
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return jsonMap;
    }

}
