package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**.
 * 
 * @author Eric
 *
 */
public class Sheet {
	private String nom; // nom de l'ongle
	private int nbLignes; // nombre de lignes dans l'onglet
	Colonne [] col;
	HSSFSheet sheet;
	
	/**.
	 * 
	 * @param wb : fichier excel
	 * @param nomOnglet : nom de l'ongle
	 * @param listeColonnes : liste des colonnes à lire
	 */
	Sheet(HSSFWorkbook wb, String nomOnglet, DefCol [] listeColonnes){
		col = new Colonne[listeColonnes.length];
		sheet = wb.getSheet(nomOnglet);
		nbLignes = nbLignesSheet();
		this.nom = nomOnglet;
		for(int i=0; i<listeColonnes.length; i++){
			switch (listeColonnes[i].type){
			case ENTIER:
				col[i] = new ColonneIntSimple(nomOnglet, numeroColonne(listeColonnes[i].name), sheet, nbLignes);
				//col[i].afficheListe();
				break;
			case CHAINE:
				col[i] = new ColonneStrSimple(nomOnglet, numeroColonne(listeColonnes[i].name), sheet, nbLignes);
				//col[i].afficheListe();
				break;
			case LISTE_CHAINES:
				col[i] = new ColonneStrListe(nomOnglet, numeroColonne(listeColonnes[i].name), sheet, nbLignes);
				//col[i].afficheListe();
				break;
			default:
				; // code erreur dans le définition de la colonne - type inexistant
			}
		}
	}
	
	/**.
	 * Retourne le nom de la colonne
	 */
	public String toString(){
		return nom;
	}
	
	/**.
	 * Retourne le nombre de lignes contenues dans l'onglet
	 * @return
	 */
	public int nbLignes(){
		return nbLignes;
	}
	
	/**.
	 * Retourne le numéro de la colonne qui porte le nom "name" (à partir de 0)
	 * 
	 * @param name
	 * @return
	 */
	private int numeroColonne(String name){
		if (name.isEmpty()){
			; // code erreur : pas de nom de chaine à rechercher
			return -1;
		}
		HSSFRow row = sheet.getRow(0);
		int i=0;
		int ret = -1;
		String ch;
		while(!(ch=row.getCell(i).toString()).isEmpty()&& ret<0){
			if (ch.equals(name)){
				ret=i;
			}
			i++;
		}
		if (ret==-1){
			if(i==0){
				; // code erreur : aucune colonne trouvée - onglet vide
				ret = -2;
			} else {
				; // code erreur : la colonne recherchée n'existe pas
				ret = -3;
			}
		}
		return ret;
	}
	
	/**.
	 *  Compte le nombre de lignes dans l'onglet
	 * Reste a faire : retourner un code d'erreur
	 * 
	 * @return
	 */
	private int nbLignesSheet(){
		HSSFRow row ;
		HSSFCell cell;
		int i = 1;
		int nb = 0;
		String ch;
		do{
			row = sheet.getRow(i);
			//ch = row.getCell(0).toString();
			if(row != null){
				cell = row.getCell(0);
				nb++;
				ch = cell.toString();
				if (ch.isEmpty()){
					; // code warning : erreur dans le format de l'onglet - cellule vide avant la dernière ligne ou bien ligne non vide après la dernière ligne du tableau
				}
			}
			i++;
		} while(row != null);
		return nb;
	}

}
