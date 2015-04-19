package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneStrListe extends Colonne {
	
	Liste [] liste = new Liste[nbLignes];
	public ColonneStrListe(HSSFWorkbook wb){
		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row ;
		String chaine = new String();
		
		for(int i=0; i<nbLignes; i++){
				row = sheet.getRow(i+1);
				chaine = row.getCell(18).toString();
				liste[i] = creerListe(chaine, i);
			}
		}
	
	// Convertit la chaine de caractères (String) en liste de chaines
	// Cette méthode ne considère que les caractère séparateurs suivants : ,;SPACE
	// Si la chaine est vide, on retourne une liste avec une chaine vide
	// Entree : String chaine
	// Sortie : ArrayList<String>
	//
	private Liste creerListe(String chaine, int ligne) {
		Liste l = new Liste();
		String charsExclus = " ,;\n";
		String chaineTemporaire = new String ();
		char c;
		boolean chEnCours =false;
		if(chaine.isEmpty()){
			;
		} else {
			for(int i=0; i<chaine.length(); i++){
				c = chaine.charAt(i);
				if(charsExclus.indexOf(c)==-1) {
					if(!chEnCours){
						chEnCours=true;
					}
					chaineTemporaire = chaineTemporaire.concat(Character.toString(c));
					if (i==chaine.length()-1){
						l.ajouteTete(chaineTemporaire);
					}
				} else {
					if(chEnCours){
						chEnCours=false;
						l.ajouteTete(chaineTemporaire);
						chaineTemporaire = new String();
					}
				}
			}
		}
		return l;
	}
	
	
	public void afficheListe(){
		for(Liste l : liste) {
			System.out.println(l.toString());
		}
	}
}
