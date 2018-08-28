package org.spring.springboot.Util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.spring.springboot.dao.BugdetailEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * excel 的几个jar的版本需要保持一致
 *
 * @author Administrator
 * @date 2018-8-22.
 */
public class ParseExcelTest {

    private  static final Logger  LOGGER = Logger.getLogger(ParseExcelTest.class);

        private static final String EXTENSION_XLS = "xls";
        private static final String EXTENSION_XLSX = "xlsx";

        /***
         * <pre>
         * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
         *   xls:HSSFWorkbook
         *   xlsx：XSSFWorkbook
         * @return
         * @throws IOException
         * </pre>
         */
        private static Workbook getWorkbook(MultipartFile file) throws IOException {
            Workbook workbook = null;
            InputStream is = file.getInputStream();
            String filePath = file.getOriginalFilename();
            if (filePath.endsWith(EXTENSION_XLS)) {
                workbook = new HSSFWorkbook(is);
            } else if (filePath.endsWith(EXTENSION_XLSX)) {
                workbook = new XSSFWorkbook(is);
            }
            return workbook;
        }
        /**
         * 读取excel文件内容
         * @throws FileNotFoundException
         * @throws FileFormatException
         */
        public static List<BugdetailEntity> readExcel(MultipartFile file) throws FileNotFoundException, FileFormatException {
            // 检查
            // 获取workbook对象
            Workbook workbook = null;
            try {
                workbook = getWorkbook(file);
                List  entityList = new ArrayList();
                // 读文件 一个sheet一个sheet地读取
                for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                    Sheet sheet = workbook.getSheetAt(numSheet);
                    if (sheet == null) {
                        continue;
                    }
//                    System.out.println("=======================" + sheet.getSheetName() + "=========================");

                    int firstRowIndex = sheet.getFirstRowNum();
                    int lastRowIndex = sheet.getLastRowNum();
//
//                    // 读取首行 即,表头
//                    Row firstRow = sheet.getRow(firstRowIndex);
//                    for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
//                        Cell cell = firstRow.getCell(i);
//                        String cellValue = getCellValue(cell, true);
//                        System.out.print(" " + cellValue + "\t");
//                    }
//                    System.out.println("");

                    List   entity = null;
                    // 读取数据行
                    for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                        entity = new LinkedList();
                        Row currentRow = sheet.getRow(rowIndex);// 当前行
                        int firstColumnIndex = currentRow.getFirstCellNum(); // 首列
                        int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
                        for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) {
                            Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
                            String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
                            entity.add(currentCellValue);
//                            System.out.print(currentCellValue + "\t");
                        }
                        System.out.println("");
                        entityList.add(ConvertEntity(entity));
                    }
//                    System.out.println("======================================================");
                }

                return entityList;
            } catch (Exception e) {
                LOGGER.error("解析错误:",e);
            } finally {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (IOException e) {
                        LOGGER.error("关闭workbook流错误:",e);
                    }
                }
            }

            return  null;
        }

        /**
         * 取单元格的值
         * @param cell 单元格对象
         * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
         * @return
         */
        private  static  String getCellValue(Cell cell, boolean treatAsStr) {
            if (cell == null) {
                return "";
            }
            if (treatAsStr) {
                // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
                // 加上下面这句，临时把它当做文本来读取
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
            if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            } else {
                return String.valueOf(cell.getStringCellValue());
            }
        }


    public static  BugdetailEntity ConvertEntity(List list){
        BugdetailEntity  entity = new BugdetailEntity();
        entity.setBugno(ConvertUtil.objConverString((list.get(0))));
        entity.setProname(ConvertUtil.objConverString((list.get(1))));
        entity.setDemandname(ConvertUtil.objConverString((list.get(2))));
        entity.setDealname(ConvertUtil.objConverString((list.get(3))));
        entity.setState(ConvertUtil.objConverString((list.get(4))));
        entity.setGrade(ConvertUtil.objConverString((list.get(5))));
        entity.setSeriousstate(ConvertUtil.objConverString((list.get(6))));
        entity.setComment(ConvertUtil.objConverString((list.get(9))));
        entity.setDetector(ConvertUtil.objConverString((list.get(10))));
        entity.setChecker(ConvertUtil.objConverString((list.get(11))));
        entity.setDetectiontime(ConvertUtil.objConverString((list.get(12))).replace("-",""));
        entity.setUpdatetime(ConvertUtil.objConverString((list.get(13))).replace("-","").substring(0,8));
        return entity;
    }
}
