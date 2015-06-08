package ficExcel;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Sheet {
	private String nom;
	private int nbLignes;
	Colonne [] col;
	
	Sheet(HSSFWorkbook wb, String nomOnglet){
		HSSFSheet sheet = wb.getSheet(nomOnglet);
		nbLignes = nbLignesSheet(sheet);
		this.nom = nomOnglet;
	}
	public String toString(){
		return nom;
	}
	public int nbLignes(){
		return nbLignes;
	}
	
	private int nbLignesSheet(HSSFSheet sheet){
		HSSFRow row ;
		int i = 1;
		int nb = 0;
		String ch;
		do{
			row = sheet.getRow(i);
			ch = row.getCell(0).toString();
			if(!ch.isEmpty()){
				nb++;
			}
			i++;
		} while(!ch.isEmpty());
		return nb;
	}
}
