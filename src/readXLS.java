import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class readXLS {
	FileOutputStream fichier;
	HSSFWorkbook wb;
	ArrayList<String> listeOnglets = new ArrayList<String>();
	
	readXLS (String nomFichier) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(nomFichier));
			wb = new HSSFWorkbook(fs);
			// nombre d'onglets
			int nbOnglets = wb.getNumberOfSheets();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void monterListeOnglets(){
		
	}
	
	//
	// Retourne la chaine de caract�res contenue (numColonne, numLigne)
	// dans l'onglet "onglet"
	//
	public String lireXY(String onglet, int numColonne, int numLigne){
		String cellule = null;
		HSSFSheet sheet = wb.getSheet(onglet);
		HSSFRow row = sheet.getRow(numLigne);
		HSSFCell cell = row.getCell(numColonne);
		cellule = cell.toString();
		
		int i = (int) cell.getNumericCellValue();
		System.out.println(i);
		
		return cellule.trim();
	}
	
	public int lireXY(String onglet, int numColonne, int numLigne){
		String cellule = null;
		HSSFSheet sheet = wb.getSheet(onglet);
		HSSFRow row = sheet.getRow(numLigne);
		HSSFCell cell = row.getCell(numColonne);
		cellule = cell.toString();
		
		int i = (int) cell.getNumericCellValue();
		System.out.println(i);
		
		return i;
	}
}
