package com.rodizio.www.util.ferramentas;


import java.io.File;

public abstract class CreateFolder {

    public CreateFolder(){
		
	}
    public static void create(String diretorio) {
        File DATABASE = new File(diretorio + "arquivo.1");
    	DATABASE.getParentFile().mkdirs();
    }  
}
