package ficExcel;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ColonneStrListe extends Colonne {
	
	String [][] liste = new String[nbLignes][nbLignes];
	public ColonneStrListe(HSSFWorkbook wb){
		HSSFSheet sheet = wb.getSheet("Shunting");
		HSSFRow row ;
		String chaine = new String();
		
		for(int i=0; i<nbLignes; i++){
				row = sheet.getRow(i+1);
				chaine = row.getCell(18).toString();
				//System.out.println("test1");
				creerListe(chaine, i);
			}
		}
	
	// Convertit la chaine de caractères (String) en liste de chaines
	// Cette méthode ne considère que les caractère séparateurs suivants : ,;SPACE
	// Si la chaine est vide, on retourne une liste avec une chaine vide
	// Entree : String chaine
	// Sortie : ArrayList<String>
	//
	private void creerListe(String chaine, int ligne) {
		int index = 0;
		String charsExclus = " ,;"; 
		char c;
		boolean chEnCours =false;
		System.out.println("test2");
		if(chaine.isEmpty()){
			System.out.println("test3");
			;
		} else {
			for(int i=0; i<chaine.length(); i++){
				c = chaine.charAt(i);
				if(charsExclus.indexOf(c)==-1) {
					if(!chEnCours){
						chEnCours=true;
						System.out.println("test boucle 1");
					}
					System.out.println("test boucle 2");
					liste[ligne][index].concat(Character.toString(c));
					System.out.println("test boucle 3");
				} else {
					if(chEnCours){
						chEnCours=false;
						index++;
					}
				}
			}
		}
	}
	
	
	public void afficheListe(){
		int j;
		for(int i=0; i<nbLignes; i++){
			j=0;
			while(!liste[i][j].isEmpty() && j<nbLignes){
				System.out.print("__" + liste[i][j]);
				j++;
			}
			System.out.println("");
		}
	}
}
