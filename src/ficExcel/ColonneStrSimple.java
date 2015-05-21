package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneStrSimple extends ColonneStr {
	private String[] cellule = new String[nbLignes];

	public ColonneStrSimple(HSSFWorkbook wb, String NomCol) {

		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row;

		for (int i = 0; i < cellule.length; i++) {
			row = sheet.getRow(i + 1);
			cellule[i] = row.getCell(14).toString();
		}
	}

	public void afficheListe() {
		for (int i = 0; i < cellule.length; i++) {
			System.out.println(cellule[i]);
		}
	}

	public boolean cellEstVide(int numLigne) {
		boolean ret = false;
		if (cellule[numLigne].length() == 0) {
			ret = true;
		}
		return ret;
	}

	// a corriger : la méthode ne doit pas renvoyer true lorsqu'on demande si
	// une cellule vide contient ""
	public boolean cellContient(int numLigne, String ch) {
		return cellule[numLigne].equals(ch);
	}

	/**
	 * .
	 * 
	 * @param numLigne
	 * @return
	 */
	public String getElement(int numLigne) {
		if (cellule[numLigne].length() == 0) {
			;// retour erreur Demande de retourner le contenu d'une cellule vide
		}
		return cellule[numLigne];
	}

}
