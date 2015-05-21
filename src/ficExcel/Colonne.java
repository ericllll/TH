package ficExcel;

public abstract class Colonne {
	String onglet;
	String nom;
	TypeDonnee tp;
	int nbLignes = 65;

	public abstract void afficheListe();

}
