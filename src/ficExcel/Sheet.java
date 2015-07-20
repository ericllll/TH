package ficExcel;

import java.util.ArrayList;

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
	/**.
	 * Attributs de la classe
	 */
	private String nom; // nom de l'onglet
	private int nbLignes; // nombre de lignes dans l'onglet
	Colonne [] col; // colonnes (nom, numero)
	
	/**.
	 * 
	 * @param wb : fichier excel
	 * @param nomOnglet : nom de l'ongle
	 * @param listeColonnes : liste des colonnes à lire
	 */
	Sheet(HSSFWorkbook wb, String nomOnglet, ArrayList<DefCol> listeColonnes){
		// test
		System.out.println(nomOnglet);
		System.out.println(listeColonnes.size());
		for(int i=0; i<listeColonnes.size(); i++){
			System.out.println("nom : " + listeColonnes.get(i).name + " et de type : " + listeColonnes.get(i).type);
		}
		// fin test
		HSSFSheet sheet;
		col = new Colonne[listeColonnes.size()];
		sheet = wb.getSheet(nomOnglet);
		nbLignes = nbLignesSheet(sheet);
		// test
		System.out.println("nombre de lignes  : " + nbLignes);
		// fin de test
		this.nom = nomOnglet;
		for(int i=0; i<listeColonnes.size(); i++){
			switch (listeColonnes.get(i).type){
			case ENTIER:
				col[i] = new ColonneIntSimple(nomOnglet, numeroColonne(listeColonnes.get(i).name, sheet), sheet, nbLignes);
				//col[i].afficheListe();
				break;
			case CHAINE:
				col[i] = new ColonneStrSimple(nomOnglet, numeroColonne(listeColonnes.get(i).name, sheet), sheet, nbLignes);
				//col[i].afficheListe();
				break;
			case LISTE_CHAINES:
				col[i] = new ColonneStrListe(nomOnglet, numeroColonne(listeColonnes.get(i).name, sheet), sheet, nbLignes);
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
	 * A completer pour traiter le cas d'un ouglet vide et la levée d'une exception
	 * 
	 * @param name
	 * @return
	 */
	private int numeroColonne(String name, HSSFSheet sheet){
		if (name.isEmpty()){
			; // code erreur : pas de nom de chaine à rechercher
			return -1;
		}
		HSSFRow row = sheet.getRow(0);
		int nbCol = row.getLastCellNum();
		int i=0;
		int ret = -1;
		String ch = " ";
		
		do {
			ch=row.getCell(i).toString();
			if(ch.equals(name)){
				ret = i;
			}
			i++;
		} while (i<nbCol && ret==-1) ;
		/*while(!(ch=row.getCell(i).toString()).isEmpty() && ret<0){
			System.out.println("numero colonne : " + i);
			if (ch.equals(name)){
				ret=i;
			}
			i++;
		}*/
		if (ret==-1){
			if(i==0){
				; // code erreur : aucune colonne trouvée - onglet vide (normalement, exception avant)
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
	private int nbLignesSheet(HSSFSheet sheet){
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
