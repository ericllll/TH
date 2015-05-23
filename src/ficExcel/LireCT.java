package ficExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
/**.
 * 
 * @author eric
 *
 */
public class LireCT {
	HSSFWorkbook wb;
	
	// pour test :
	String [] Sheets = {"Shunting", "Signal"};
	
	// fin pour test

	LireCT(String nomFichier, String onglet, String colonne, TypeDonnee type) {
		ouvertureFichier(nomFichier);
		
	}
	
	private void ouvertureFichier(String nomFichier){
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					nomFichier));
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
