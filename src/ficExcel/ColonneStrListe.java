package ficExcel;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneStrListe extends Colonne {
	
	String [] cellule = new String[nbLignes];
	public ColonneStrListe(HSSFWorkbook wb, String NomCol){
		ArrayList<String> liste;
		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row ;
		String chaine = new String();
		
		for(int i=0; i<cellule.length; i++){
				row = sheet.getRow(i+1);
				chaine = row.getCell(18).toString();
				
			}
		}
	
	// Convertit la chaine de caractères (String) en liste de chaines
	// Cette méthode ne considère que les caractère séparateurs suivants : ,;SPACE
	// Si la chaine est vide, on retourne une liste avec une chaine vide
	// Entree : String chaine
	// Sortie : ArrayList<String>
	//
	private void creerListe(String chaine) {
		ArrayList<String> liste = new ArrayList<String>();
		int longueur = chaine.length();
		int index = 0;
		//char [] charExclu = {' ', ',', ';'};
		String charsExclus = " ,;"; 
		char c;
		String str = new String();
		boolean chEnCours =false;
		if(chaine.isEmpty()){
			liste.add("");
		} else {
			for(index=0; index<longueur; index++){
				c = chaine.charAt(index);
				if(charsExclus.indexOf(c)==-1) {
					if(!chEnCours){
						chEnCours=true;
					}
					str.concat(Character.toString(c));
				} else {
					if(chEnCours){
						liste.add(str);
						chEnCours=false;
						String 
					}
				}
			}
			
		}
	}
	
	
	public void afficheListe(){
		for(int i=0; i<cellule.length; i++){
			System.out.println(cellule[i]);
		}
	}
}
