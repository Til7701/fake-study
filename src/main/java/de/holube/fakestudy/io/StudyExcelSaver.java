package de.holube.fakestudy.io;


import de.holube.fakestudy.Study;
import de.holube.fakestudy.category.Category;
import de.holube.fakestudy.category.NumCategory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class StudyExcelSaver {

    private Study study;
    private String folderName;
    private String fileName;

    public StudyExcelSaver(Study study, String folderName, String fileName) {
        this.study = study;
        this.folderName = folderName;
        this.fileName = fileName;
    }

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
                    //cell.setCellValue(study.getCategories().get(study.getEntryOrder().get(j)).getStringResults()[i - 1]);
                    cell.setCellStyle(style);
                }
            }


            File file = new File("C:\\Users\\Tilman\\Desktop\\tmp\\fakeStudyExport\\" + fileName + ".xlsx");

            FileOutputStream outputStream;
            outputStream = new FileOutputStream(file.getPath());
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
