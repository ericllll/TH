public class Test {

	public static void main(String[] args) {
		readXLS essai = new readXLS("VeriGraf_R02_03.xls");
		
		String cellule;
		cellule = essai.lireXY("DEFINITION_ATTRIBUT", 2, 8);
		System.out.println("-" + cellule + "-");
		
		//String[] tab = new String[20];
		//tab = cellule.split(arg0)
	}

}
