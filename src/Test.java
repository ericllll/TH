import ficExcel.*;

public class Test {

	public static void main(String[] args) {
		
		
		//tt[1][2].concat(Character.toString('s'));
		//System.out.println(tt[1][2]);
		
		
		readXLS essai = new readXLS("YIS CT Build R02D02_P.xls");
		System.out.println("fichier ouvert");
		//ColonneStrListe col = new ColonneStrListe(essai.wb);
		ColonneStrSimple col = new ColonneStrSimple(essai.wb, "");
		System.out.println("liste créée");
		col.afficheListe();
		System.out.println(col.getElement(3));
		
		/* test colonneStrListe */
		//System.out.println(col.cellEstVide(6));
		//System.out.println(col.contientElement(64, "N810"));
		//System.out.println(col.neContientQueElement(37, "N805"));
		//System.out.println(col.neContientPasElement(64, "N806"));
		
		
		
	}
}
