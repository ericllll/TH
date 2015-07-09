package ficExcel;

/**.
 * un objet DefCol contient le nom de la colonne (premiere ligne)
 * et le type de donnée à lire (int, string ou liste de string)
 * @author Eric
 *
 */
public class DefCol {
	String name;
	Types type;
	
	DefCol(String name, Types type){
		this.name=name;
		this.type=type;
	}
	
	public String toString(){
		return name;
	}

}
