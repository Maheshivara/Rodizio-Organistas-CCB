package com.rodizio.www.visao.controle;
/**
 * Classe Abstrata com o Metodo: TrataEnter
 * Responsavel por acionar a Tecla TAB em um determinado Campo
 * Quando a Tecla ENTER for Precionada.
 * @author http://www.guj.com.br/java/92862-tecla-enter-no-jtextfield-
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class TrataEnter {
	public static void acionaEnter (Component comp) {
		if(comp!=null){
			Set<AWTKeyStroke> keystrokes = comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);  
			Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke> (keystrokes);  
			newKeystrokes.add (AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));  
			comp.setFocusTraversalKeys (KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);  
		}
	}  
}
