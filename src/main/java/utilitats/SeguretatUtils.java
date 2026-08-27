package utilitats;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Classe d'utilitat per a operacions de seguretat.
 * 
 * @author Andreu
 * @version 1.0
 */
public final class SeguretatUtils {

	private SeguretatUtils() {
		/*
		 * Classe d'utilitat no instanciable.
		 */
	}

	//-------------------------------
	// MÈTODE HASH
	//-------------------------------
	
	/**
	 * Retorna el hash SHA-256 de la contrasenya en format hexadecimal.
	 * <p>
	 * La conversió de {@code char[]} a bytes es fa sense passar per {@code String}
	 * per evitar que la contrasenya quedi a la memòria. Els bytes intermedis es
	 * buiden immediatament després de calcular el hash.
	 * <p>
	 * Un cop cridat aquest mètode, buida l'array original:
	 * <pre>
	 * 		Arrays.fill(contrasenya, '\0');
	 * </pre>
	 * 
	 * @param contrasenya Contrasenya en format {@code char[]}.
	 * @return Hash SHA-256 en hexadecimal (64 caràcters).
	 */
	public static String hashSHA256(char[] contrasenya) {
		
		/*
		 * Convertir char[] a byte[]
		 * 
		 * contrasenya.length * 2 perquè cada char ocupa 2 bytes.
		 * El >> 8 agafa el byte alt i el (byte) sol agafa el byte baix.
		 * Així es preserven tots els caràcters, fins i tot els especials. 
		 */
		byte[] bytes = new byte[contrasenya.length * 2];
		for(int i = 0; i < contrasenya.length; i++) {
			bytes[i * 2] = (byte) (contrasenya[i] >> 8); // desplaçem 8 bits a la dreta.
			bytes[i * 2 + 1] = (byte) (contrasenya[i]);
		}
		
		try {
			/*
			 * Fer el hash i buidar els bytes intermedis.
			 */
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); // obté el motor de hash del JDK.
			byte[] hash = digest.digest(bytes); // calcula el hash i retorna un nou array de 32 bytes.
			Arrays.fill(bytes, (byte) 0); // buida immediatament els bytes de la contrasenya de la memòria.
			
			/*
			 * Convertir el hash a hexadecimal.
			 * 
			 * El hash és un array de 32 bytes en números, però per guardar-lo o comparar-lo
			 * necessitem convertir-lo a text. Hex és el format estàndard, cada byte es representa
			 * amb 2 caràcters (%02x) que significa: format hexadecimal, mínim 2 caràcters, omplint amb 0 si cal.
			 * El resultat és un String de 64 caràcters (hash.length * 2) → 32 bytes * 2 = 64 bytes
			 */
			StringBuilder hex = new StringBuilder(hash.length * 2);
			for(byte b : hash) {
				hex.append(String.format("%02x", b));
			}
			
			return hex.toString();
			
		} catch(NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 no disponible.", e);
		}
	}

}
