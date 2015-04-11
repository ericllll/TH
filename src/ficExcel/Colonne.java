package ficExcel;


public abstract class Colonne {
	String nom;
	int nbLignes = 65;
	//ArrayList<String> colonne = new ArrayList<String>();
	
	public abstract void afficheListe();
}
