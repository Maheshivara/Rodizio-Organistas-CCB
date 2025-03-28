package com.rodizio.www.util.ferramentas;

import java.net.URISyntaxException;

public class PastaCorrente {  
	public PastaCorrente() {  
		try {  
			String caminho = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();  
			caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);  
			System.out.println(caminho);  
		} catch (URISyntaxException e) {  
			e.printStackTrace();  
		}  
	}  
}  
