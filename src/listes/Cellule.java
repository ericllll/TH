package listes;

public class Cellule {
	/**.
	 * Contenu de la cellule
	 */
	String contenu;
	/**.
	 * Cellule suivante
	 */
	Cellule suivante;
	
	/**.
	 * Constructeur
	 * @param c : valeur de la cellule
	 * @param n : Cellule suivante
	 */
	Cellule(String c, Cellule n){
		contenu = c;
		suivante = n;
	}
	/**.
	 * 
	 */
	public String toString() {
		return ""+contenu;
	}
	/**.
	 * 
	 * @param ch
	 * @return
	 */
	public boolean equals(String ch){
		return contenu.equals(ch);
	}
}
