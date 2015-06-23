package ficExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	String [] sheets = {"Shunting", "Signal"};
	
	// fin pour test

	public LireCT(String nomFichier) {
		DefCol[] listeColonnes = new DefCol[1];
		listeColonnes[0] = new DefCol("ESP", Types.CHAINE);
		ouvertureFichier(nomFichier);

		Sheet Onglet = new Sheet(wb, sheets[0], listeColonnes);
		
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
