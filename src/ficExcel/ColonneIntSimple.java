package ficExcel;
/**.
 * package ficExcel;
 * Classe à corriger car pour l'instant, un cellule vide renvoie la valeur 0
 */

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/*..
 * 
 */
public class ColonneIntSimple extends Colonne {
	private int [] cellule;
	
	ColonneIntSimple(String nom, int numCol, HSSFSheet sheet, int nbLignes){
		super(nom, numCol, sheet, nbLignes);
	}
	
	protected void creerColonne(HSSFSheet sheet, int nbLignes, int numCol) {
		cellule = new int[nbLignes];
		HSSFRow row ;
		
		for(int i=0; i<cellule.length; i++){
				row = sheet.getRow(i+1);
				if (row.getCell(numCol).toString().trim().isEmpty()){
					cellule[i]=0;
				} else {
					cellule[i] = (int)row.getCell(numCol).getNumericCellValue();
				}
			}
		}
	
	/*public boolean celluleEstVide(int numLigne){
		
	}*/
	public int getLineContainsElement(String element){
		return 0;
	}
	
	public void afficheListe(){
		for(int i=0; i<cellule.length; i++){
			System.out.println(cellule[i]);
		}
	}
}
