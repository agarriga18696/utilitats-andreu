package utilitats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileOutputStream;

/**
 * Classe d'utilitat per treballar amb fitxers de text i binaris.
 * 
 * @author Andreu
 * @version 1.5
 */

public class Fitxers {

	////////////////////////////////////////////////////
	/// FITXERS DE TEXT - LECTURA
	////////////////////////////////////////////////////

	/**
	 * Llegeix tot el contingut d'un fitxer de text i el retorna com a String.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return El contingut del fitxer, o null si hi ha un error.
	 */
	public static String llegirTot(String ruta) {
		StringBuilder contingut = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linia;
			while((linia = br.readLine()) != null) {
				contingut.append(linia).append(System.lineSeparator());
			}
			return contingut.toString();
		} catch(IOException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
			return null;
		}
	}

	/**
	 * Llegeix totes les línies d'un fitxer de text i les retorna en un array.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return Array de línies, o llista buida si hi ha un error.
	 */
	public static String[] llegirLinies(String ruta) {
		// Comptar quantes línies té el fitxer:
		int numLinies = comptarLinies(ruta);
		if(numLinies <= 0) return new String[0];
		// Llegir les línies i guardar-les a l'array:
		String[] linies = new String[numLinies];
		int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linia;
			while((linia = br.readLine()) != null) {
				linies[i++] = linia;
			}
		} catch(IOException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
		}
		return linies;
	}
	
	/**
	 * Llegeix totes les línies d'un fitxer amb format CSV i retorna les dades en un array bidimensional. 
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param separador Símbol de separació utilitzat al document. Normalment ';' o ','.
	 * @return Array bidimensional amb un array bidimensional amb les dades de les línies i columnes.
	 */
	public static String[][] llegirCSV(String ruta, String separador) {
		String[] linies = llegirLinies(ruta);
		String[][] camps = new String[linies.length][];
		for(int i = 0; i < linies.length; i++) {
			camps[i] = linies[i].split(separador);
		}
		return camps;
	}

	/**
	 * Compta el nombre de línies d'un fitxer de text.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return Nombre de línies, o -1 si hi ha un error.
	 */
	public static int comptarLinies(String ruta) {
		int comptador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			while(br.readLine() != null) {
				comptador++;
			}
			return comptador;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
			return -1;
		}
	}

	////////////////////////////////////////////////////
	/// FITXERS DE TEXT - ESCRIPTURA
	////////////////////////////////////////////////////

	/**
	 * Escriu o sobreescriu un text en un fitxer de text.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param text Text a escriure
	 * @param afegir true afegeix al final, false sobreescriu.
	 * @return true si s'ha escrit correctament.
	 */
	public static boolean escriure(String ruta, String text, boolean afegir) {
		try (FileWriter fw = new FileWriter(ruta, afegir)) {
			fw.write(text + System.lineSeparator());
			return true;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut escriure al fitxer: " + ruta);
			return false;
		}
	}

	/**
	 * Escriu o sobreescriu una llista de línies en un fitxer de text.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param linies Llista de línies a escriure.
	 * @param afegir true afegeix al final, false sobreescriu.
	 * @return true si s'ha escrit correctament.
	 */
	public static boolean escriureLinies(String ruta, String[] linies, boolean afegir) {
		try (FileWriter fw = new FileWriter(ruta, afegir)) {
			for(String linia : linies) {
				fw.write(linia + System.lineSeparator());
			}
			return true;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut escriure al fitxer: " + ruta);
			return false;
		}
	}
	
	/**
	 * Guarda un array d'objectes en un fitxer de text en format CSV.
	 * Els objectes han d'implementar la interfície SerialitzableCSV.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param objectes Array d'objectes a guardar.
	 * @param separador Separador entre camps.
	 * @param afegir true afegeix al final, false sobreescriu.
	 * @return true si s'ha escrit correctament.
	 */
	public static <T extends SerialitzableCSV> boolean guardarObjectesCSV(String ruta, T[] objectes, String separador, boolean afegir) {
		String[] linies = new String[objectes.length];
		for(int i = 0; i < linies.length; i++) {
			linies[i] = objectes[i].aCSV(separador);
		}
		return escriureLinies(ruta, linies, afegir);
	}

	////////////////////////////////////////////////////
	/// FITXERS BINARIS
	////////////////////////////////////////////////////

	/**
	 * Escriu un array d'objectes serialitzables en un fitxer binari.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param objectes Array d'objectes a guardar.
	 * @return true si s'ha escrit correctament.
	 */
	public static <T> boolean guardarObjectes(String ruta, T[] objectes) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			for(T obj : objectes) {
				oos.writeObject(obj);
			}
			return true;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut guardar el fitxer: " + ruta);
			return false;
		}
	}
	
	/**
	 * Escriu una col·lecció d'objectes serialitzables en un fitxer binari.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param objectes Col·lecció d'objectes a guardar.
	 * @return true si s'ha escrit correctament.
	 */
	public static <T> boolean guardarObjectes(String ruta, Collection<T> objectes) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			for(T obj : objectes) {
				oos.writeObject(obj);
			}
			return true;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut guardar el fitxer: " + ruta);
			return false;
		}
	}
	
	/**
	 * Escriu un mapa d'objectes serialitzables en un fitxer binari.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param objectes Mapa d'objectes a guardar.
	 * @return true si s'ha escrit correctament.
	 */
	public static <K, V> boolean guardarMapa(String ruta, Map<K, V> mapa) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
			oos.writeObject(mapa);
			return true;
		} catch(IOException e) {
			Missatges.error("No s'ha pogut guardar el fitxer: " + ruta);
			return false;
		}
	}

	/**
	 * Llegeix tots els objectes d'un fitxer binari i els retorna en un array.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @param classe Classe dels objectes (Persona.class)
	 * @return Array d'objectes llegits, o array buit si hi ha un error.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] carregarObjectes(String ruta, Class<T> classe) {
		// Comptar quants objectes té el fitxer:
		int numObjectes = comptarObjectes(ruta);
		// Crear l'array amb la mida correcta:
		T[] array = (T[]) Array.newInstance(classe, numObjectes);
		if(numObjectes <= 0) return array;
		// Llegir els objectes i guardar-los a l'array:
		File fitxer = new File(ruta);
		if(!fitxer.exists()) return array;
		int i = 0;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			while(true) {
				try {
					array[i++] = (T) ois.readObject();
				} catch(EOFException e) {
					break;
				}
			}
		} catch(IOException | ClassNotFoundException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
		}
		return array;
	}
	
	/**
	 * Llegeix tots els objectes d'un fitxer binari i els retorna en una llista.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return Llista d'objectes llegits, o llista buida si hi ha un error.
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> carregarObjectes(String ruta) {
		List<T> llista = new ArrayList<>();
		File fitxer = new File(ruta);
		if(!fitxer.exists()) return llista;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			while(true) {
				try {
					llista.add((T) ois.readObject());
				} catch(EOFException e) {
					break;
				}
			}
		} catch(IOException | ClassNotFoundException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
		}
		return llista;
	}
	
	/**
	 * Carrega un mapa des d'un fitxer binari i el retorna.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return Mapa llegit.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> carregarMapa(String ruta) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			return (Map<K, V>) ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
			return new HashMap<>();
		}
	}

	/**
	 * Compta el nombre d'objectes emmagatzemats en un fitxer binari.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return Nombre d'objectes, o -1 si hi ha un error.
	 */
	public static int comptarObjectes(String ruta) {
		int comptador = 0;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			while(true) {
				try {
					ois.readObject();
					comptador++;
				} catch(EOFException e) {
					break;
				} catch(ClassNotFoundException e) {
					Missatges.error("Classe no trobada al fitxer: " + ruta);
					return -1;
				} 
			}
		} catch(IOException e) {
			Missatges.error("No s'ha pogut llegir el fitxer: " + ruta);
			return -1;
		}
		return comptador;
	}

	////////////////////////////////////////////////////
	/// GESTIÓ DE FITXERS
	////////////////////////////////////////////////////

	/**
	 * Comprova si un fitxer existeix.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return true si el fitxer existeix.
	 */
	public static boolean existeix(String ruta) {
		return new File(ruta).exists();
	}

	/**
	 * Esborra un fitxer.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return true si s'ha esborrat correctament.
	 */
	public static boolean esborrar(String ruta) {
		File fitxer = new File(ruta);
		if(fitxer.exists()) {
			return fitxer.delete();
		}
		Missatges.avis("El fitxer no existeix: " + ruta);
		return false;
	}

	/**
	 * Crea un fitxer buit si no existeix.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return true si s'ha creat o ja existia.
	 */
	public static boolean crearFitxerSiNoExisteix(String ruta) {
		File fitxer = new File(ruta);
		if(fitxer.exists()) return true;
		try {
			return fitxer.createNewFile();

		} catch(IOException e) {
			Missatges.error("No s'ha pogut crear el fitxer: " + ruta);
			return false;
		}
	}

	/**
	 * Crea un directori si no existeix, incloent els directoris pare.
	 * 
	 * @param ruta Ruta del fitxer.
	 * @return true si s'ha creat o ja existia.
	 */
	public static boolean crearDirectoriSiNoExisteix(String ruta) {
		File directori = new File(ruta);
		if(directori.exists()) return true;
		return directori.mkdirs();
	}

}
