package tables;

import java.util.HashMap;
import java.util.Map;

public class Asig_esp_data {
	public Map<Object, Integer> tamaños;
	public Map<Object, Integer> direcciones;
	public Map<Object, Integer> desplazamientos;
	
	public Asig_esp_data() {
		tamaños = new HashMap<Object, Integer>();
		direcciones = new HashMap<Object, Integer>();
		desplazamientos = new HashMap<Object, Integer>();
	}
}
