import ficExcel.*;

public class Test {

	public static void main(String[] args) {
		
		
		//tt[1][2].concat(Character.toString('s'));
		//System.out.println(tt[1][2]);
		
		
		readXLS essai = new readXLS("YIS CT Build R02D02_P.xls");
		System.out.println("fichier ouvert");
		//Colonne col = new ColonneStrListe(essai.wb);
		Colonne col = new ColonneStrSimple(essai.wb, "");
		System.out.println("liste créée");
		col.afficheListe();
	}
}
