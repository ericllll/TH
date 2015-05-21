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

	LireCT(String nomFichier) {
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
	
	private int nbLignesSheet(String nomSheet){
		HSSFSheet sheet = wb.getSheet(nomSheet);
		HSSFRow row ;
		int i = 1;
		int nb = 0;
		String ch;
		do{
			row = sheet.getRow(i);
			ch = row.getCell(0).toString();
			if(!ch.isEmpty()){
				nb++;
			}
			i++;
		} while(!ch.isEmpty());
		return nb;
	}
}
