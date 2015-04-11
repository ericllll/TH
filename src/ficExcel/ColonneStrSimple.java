package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneStrSimple extends Colonne {
	String [] cellule = new String[nbLignes];
	public ColonneStrSimple(HSSFWorkbook wb, String NomCol){
		
		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row ;
		
		for(int i=0; i<cellule.length; i++){
				row = sheet.getRow(i+1);
				cellule[i] = row.getCell(14).toString();
			}
		}
	public void afficheListe(){
		for(int i=0; i<cellule.length; i++){
			System.out.println(cellule[i]);
		}
	}
		
}
