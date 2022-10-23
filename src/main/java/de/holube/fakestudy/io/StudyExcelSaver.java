package de.holube.fakestudy.io;

import de.holube.fakestudy.study.Study;
import de.holube.fakestudy.study.category.Category;
import de.holube.fakestudy.study.category.NumCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class StudyExcelSaver {

    private Study study;
    private String folderName;
    private String fileName;

    public void save() {
        try (Workbook workbook = new XSSFWorkbook()) {

            CellStyle style = workbook.createCellStyle();

            Sheet sheet = workbook.createSheet("Sheet1");


            // Header
            Row header = sheet.createRow(0);
            for (int i = 0; i < study.getCategories().size(); i++) {
                Cell headerCell = header.createCell(i);
                headerCell.setCellValue(study.getCategories().get(study.getEntryOrder().get(i)).getName());
                headerCell.setCellStyle(style);
            }

            // Data
            for (int i = 1; i < study.getAmountSubjects() + 1; i++) {
                Row row = sheet.createRow(i);

                for (int j = 0; j < study.getEntryOrder().size(); j++) {
                    Cell cell = row.createCell(j);
                    Category category = study.getCategories().get(study.getEntryOrder().get(j));
                    if (category instanceof NumCategory) {
                        NumCategory numCategory = (NumCategory) category;
                        cell.setCellValue(numCategory.getDoubleResults()[i - 1]);
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
            LOG.error("Error while saving Study to Excel File.", e);
        }
    }

}
