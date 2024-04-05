package forms;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorPalette {

	private Map<String, Color> cp;

	public ColorPalette(String design) {
		switch (design) {
		case "GREEN" -> {
			cp = new HashMap<String, Color>();
			cp.put("", Color.LIGHT_GRAY); // Color predeterminado
			cp.put("2", new Color(71, 255, 209)); // Amarillo claro
			cp.put("4", new Color(145, 255, 209)); // Amarillo
			cp.put("8", new Color(150, 255, 248)); // Verde claro
			cp.put("16", new Color(79, 255, 248)); // Verde
			cp.put("32", new Color(150, 232, 255)); // Verde azulado claro
			cp.put("64", new Color(145, 202, 255)); // Verde azulado
			cp.put("128", new Color(89, 225, 255)); // Verde azulado oscuro
			cp.put("256", new Color(90, 187, 255)); // Verde azul oscuro
			cp.put("512", new Color(98, 146, 250)); // Verde azul
			cp.put("1024", new Color(99, 105, 245)); // Verde azul profundo
			cp.put("2048", new Color(143, 88, 98)); // Azul profundo
		}
		case "PASTEL" -> {
			cp = new HashMap<String, Color>();
			cp.put("", Color.LIGHT_GRAY); // Color predeterminado
			cp.put("2", new Color(242, 215, 213));
			cp.put("4", new Color(235, 222, 240));
			cp.put("8", new Color(212, 230, 241));
			cp.put("16", new Color(209, 242, 235));
			cp.put("32", new Color(212, 239, 223));
			cp.put("64", new Color(252, 243, 207));
			cp.put("128", new Color(250, 229, 211));
			cp.put("256", new Color(250, 219, 216));
			cp.put("512", new Color(215, 189, 226));
			cp.put("1024", new Color(169, 204, 227));
			cp.put("2048", new Color(163, 228, 215));
		}

		default -> {
			cp = new HashMap<String, Color>();
			cp.put("", Color.LIGHT_GRAY); // Color predeterminado
			cp.put("2", new Color(71, 255, 209)); // Amarillo claro
			cp.put("4", new Color(145, 255, 209)); // Amarillo
			cp.put("8", new Color(150, 255, 248)); // Verde claro
			cp.put("16", new Color(79, 255, 248)); // Verde
			cp.put("32", new Color(150, 232, 255)); // Verde azulado claro
			cp.put("64", new Color(145, 202, 255)); // Verde azulado
			cp.put("128", new Color(89, 225, 255)); // Verde azulado oscuro
			cp.put("256", new Color(90, 187, 255)); // Verde azul oscuro
			cp.put("512", new Color(98, 146, 250)); // Verde azul
			cp.put("1024", new Color(99, 105, 245)); // Verde azul profundo
			cp.put("2048", new Color(143, 88, 98)); // Azul profundo
		}
		}
	}

	public Color getColor(String i) {
		return cp.get(i);
	}

}
