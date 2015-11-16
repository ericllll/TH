package ficExcel;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public abstract class Colonne {
	String nom;
	int numero;
	
	Colonne(String nom, int numCol, HSSFSheet sheet, int nbLignes){
		this.nom = nom;
		this.numero = numCol;
		creerColonne(sheet, nbLignes, numCol);
	}
	protected abstract void creerColonne(HSSFSheet sheet, int nbLignes, int numCol);
	
	public abstract void afficheListe();
	
	public String toString(){
		return nom;
	}
	
	public abstract int getLineContainsElement(String element);

}
