package listes;

//import java.util.NoSuchElementException;

public class Liste {
	/**.
	 * Tete de la liste
	 */
	private Cellule tete;
	
	/**.
	 * Nombre d'éléments dans la liste
	 */
	private int nbElements;
	
	/**.
	 * Constructeur
	 */
	public Liste(){
		tete = null;
		nbElements = 0;
	}
	
	public Liste ajouteTete(String val){
		nbElements++;
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
	
	public boolean estVide(){
		if (this.tete==null){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contient(String ch){
		boolean ret = false;
		Cellule cell = tete;
		while(cell != null && !ret){
			ret = cell.equals(ch);
			cell = cell.suivante;
		}
		return ret;
	}
	
	public boolean neContientQue(String ch){
		boolean ret = false;
		if(this.nbElements==1 && this.contient(ch)){
			ret = true;
		}
		return ret;
	}
	
	public int getNbElements(){
		return nbElements;
	}
}
