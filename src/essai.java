import ficExcel.CT;


public class essai {
//Table table;
//Database base;
	public static void main(String[] args) {
		System.out.println("Début");
		CT t = new CT("YIS CT Build R02D02_P.xls", "xml/CT.xml");
		//System.out.println(t.chercherOnglet("ESP"));
		if (t.chercherOnglet("ESMP")==null)
			System.out.println("toto");
		
	}

}
