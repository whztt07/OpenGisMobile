package code.google.com;

import android.text.Editable;


public class ValidacionDatos {

	

	public static boolean validarTexto(String texto){
		
		boolean r = isInteger(texto);
		texto = texto.trim();
		String comilla = "'";
		for (int i = 0; i < texto.length(); i++) {
			if (Character.isLetter(texto.charAt(i)) == false
					&& texto.charAt(i) != (' ')
					|| texto.charAt(i) == comilla.charAt(0)) {
				

				return false;

			}
			if (texto.charAt(i) == ' ' && texto.charAt(i - 1) == ' ') {
				
				return false;
			}
		}

		if (r == true || texto.length() < 2) {


			return false;

		} else {

			return true;

		}
		
	}
	
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e2) {
			return false;
		}

	}


	
}
