package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**.
 * 
 * @author Eric
 *
 * Reste à corriger car ne lit pas correctement certaines colonnes
 */
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
			cellule[i] = row.getCell(numCol).toString().trim();
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
	/**.
	 * Retourne le numéro de ligne de la première cellule égale à element
	 * Retourne -1 si la chaine n'est pas trouvée
	 * @param element
	 * @return
	 */
	public int getLineContainsElement(String element){
		int i=0;
		for(String str : cellule){
			if(str.equals(element)){
				return i;
			}
			i++;
		}
		return -1; // cellule non trouvée
	}
}
