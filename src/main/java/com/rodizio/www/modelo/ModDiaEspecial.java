package com.rodizio.www.modelo;

import java.util.Date;

public class ModDiaEspecial {
    
    private Date dataEspecial;
    public Date getDataEspecial() {
        return dataEspecial;
    }
    public void setDataEspecial(Date dataEspecial) {
        this.dataEspecial = dataEspecial;
    }
    public String getDiaSemana() {
        return diaSemana;
    }
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
    private String diaSemana;

}
