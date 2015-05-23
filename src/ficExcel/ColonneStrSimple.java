package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ColonneStrSimple extends Colonne {
	private String[] cellule;
	
	ColonneStrSimple(String nom, int numCol, HSSFSheet sheet, int nbLignes){
		super(nom, numCol, sheet, nbLignes);
	}
	
	protected void creerColonne(HSSFSheet sheet, int nbLignes, int numCol) {
		cellule = new String[nbLignes];
		HSSFRow row;

		for (int i = 0; i < cellule.length; i++) {
			row = sheet.getRow(i + 1);
			cellule[i] = row.getCell(numCol).toString();
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
