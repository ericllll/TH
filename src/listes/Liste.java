package listes;

//import java.util.NoSuchElementException;

public class Liste {
	/**.
	 * Tete de la liste
	 */
	private Cellule tete;
	
	/**.
	 * Constructeur
	 */
	public Liste(){
		tete = null;
	}
	
	public Liste ajouteTete(String val){
		tete = new Cellule(val, tete);
		return this;
	}
	
	/*public Liste supprimeTete() {
		if(tete == null) {
			thow new NoSuchElementException();
		}
		tete = tete.suivante;
		return this;
	}*/
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Cellule cell = tete;
		while(cell != null) {
			sb.append(cell+" "); // cell sans l'attribut retourne le resultat de toString
			cell = cell.suivante;
		}
		sb.append("]");
		return sb.toString();
	}
}
