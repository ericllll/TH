import ficExcel.*;

public class Test {

	public static void main(String[] args) {
		String str = "abcde";
		str.substring(0, 0);
		System.out.println(str + str.length());
		
		/*
		readXLS essai = new readXLS("YIS CT Build R02D02_P.xls");
		System.out.println("fichier ouvert");
		Colonne col = new ColonneStrSimple(essai.wb, "ROUTE_FLANK_SWITCHES_R");
		col.afficheListe();*/
	}
}
