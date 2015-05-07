package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneIntSimple extends Colonne {
	int [] cellule = new int[nbLignes];
	public ColonneIntSimple(HSSFWorkbook wb, String NomCol){
		
		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row ;
		
		for(int i=0; i<cellule.length; i++){
				row = sheet.getRow(i+1);
				cellule[i] = (int)row.getCell(14).getNumericCellValue();
			}
		}
	
	/*public boolean celluleEstVide(int numLigne){
		
	}*/
	
	public void afficheListe(){
		for(int i=0; i<cellule.length; i++){
			System.out.println(cellule[i]);
		}
	}
}
