/**
 * 
 */
package com.rodizio.www.visao.controle;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * @author Wesley
 *
 */
public abstract class Remove {
    /**
     * Remove a Barra sobre os JInternalFrame
     * Retirado do Site: http://www.guj.com.br/java/98849-ocultar-barra-de-titulo-jinternalframe
     * Postado por: jmarysystems
     * @param internalFrame
     */
    public static void barraInternalFrame(JInternalFrame internalFrame) {

	((BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null); //retirar o painel superior 
	internalFrame.repaint();
    }

}
