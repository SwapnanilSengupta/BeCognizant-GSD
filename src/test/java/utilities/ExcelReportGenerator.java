package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReportGenerator {
	
	XSSFWorkbook workbook = new XSSFWorkbook();
	
	public void generateExcelReport(List<String> languages, List<String> indiaSupport, List<String> firstCountrySupport, List<String> secondCountrySupport) throws IOException {
	   
			
			XSSFSheet languagesSheet = workbook.createSheet("Languages");
			XSSFRow  langHeaderRow = languagesSheet.createRow(0);
			langHeaderRow.createCell(0).setCellValue("Language");
			
			for(int i=0; i<languages.size(); i++) {
				XSSFRow dataRow = languagesSheet.createRow(i+1);
				XSSFCell cell=dataRow.createCell(0);
				cell.setCellValue(languages.get(i));
			}
			
		
			XSSFSheet indiaSupportSheet = workbook.createSheet("India Support");
			//Sheet indiaSupportSheet = workbook.createSheet("India Support");
			XSSFRow supportHeaderRow = indiaSupportSheet.createRow(0);
			supportHeaderRow.createCell(0).setCellValue("Support Available for India");
			
			for(int i=0; i<indiaSupport.size(); i++) {
				XSSFRow dataRow = indiaSupportSheet.createRow(i+1);
				XSSFCell cell=dataRow.createCell(0);
				cell.setCellValue(indiaSupport.get(i));
			}
			
			
			
			XSSFSheet firstCountrySupportSheet = workbook.createSheet("First Country Support");
			XSSFRow support1HeaderRow = firstCountrySupportSheet.createRow(0);
			support1HeaderRow.createCell(0).setCellValue("Support Available for First Country");
			for(int i=0; i<firstCountrySupport.size(); i++) {
				XSSFRow dataRow = firstCountrySupportSheet.createRow(i+1);
				XSSFCell cell=dataRow.createCell(0);
				cell.setCellValue(firstCountrySupport.get(i));
			}
			
			XSSFSheet secondCountrySupportSheet = workbook.createSheet("Second Country Support");
			XSSFRow support2HeaderRow = secondCountrySupportSheet.createRow(0);
			support2HeaderRow.createCell(0).setCellValue("Support Available for Second Country");
			for(int i=0; i<secondCountrySupport.size(); i++) {
				XSSFRow dataRow = secondCountrySupportSheet.createRow(i+1);
				XSSFCell cell=dataRow.createCell(0);
				cell.setCellValue(secondCountrySupport.get(i));
				
			}
			
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\2310300\\eclipse-workspace\\Cognizant_CAS_GSD\\Report.xlsx");
				workbook.write(fileOut);
			
			try {
				workbook.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
}