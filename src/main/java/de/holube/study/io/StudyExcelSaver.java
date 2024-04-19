package de.holube.study.io;

import de.holube.study.Study;
import de.holube.study.category.Category;
import de.holube.study.category.NumberCategory;
import de.holube.study.exception.ExcelSaveException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudyExcelSaver {

    private Study study;
    private String folderName;
    private String fileName;

    public void save() throws ExcelSaveException {
        try (Workbook workbook = new XSSFWorkbook()) {
            CellStyle style = workbook.createCellStyle();

            Sheet sheet = workbook.createSheet("Sheet1");

            List<String> entryOrder = new ArrayList<>(study.getCategories().keySet());
            entryOrder.sort(String::compareTo);

            // Header
            Row header = sheet.createRow(0);
            for (int i = 0; i < study.getCategories().size(); i++) {
                Cell headerCell = header.createCell(i);
                headerCell.setCellValue(study.getCategories().get(entryOrder.get(i)).getName());
                headerCell.setCellStyle(style);
            }

            // Data
            for (int i = 1; i < study.getAmountSubjects() + 1; i++) {
                Row row = sheet.createRow(i);

                for (int j = 0; j < entryOrder.size(); j++) {
                    Cell cell = row.createCell(j);
                    Category<?> category = study.getCategories().get(entryOrder.get(j));
                    if (category instanceof NumberCategory numberCategory) {
                        cell.setCellValue(numberCategory.getResults()[i - 1]);
                    } else {
                        cell.setCellValue(category.getStringResults()[i - 1]);
                    }
                    cell.setCellStyle(style);
                }
            }

            File file = new File(folderName + fileName + ".xlsx");
            FileOutputStream outputStream;
            outputStream = new FileOutputStream(file.getPath());
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new ExcelSaveException("could not save excel sheet", e);
        }
    }

}
