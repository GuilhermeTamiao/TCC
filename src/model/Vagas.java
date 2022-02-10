    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author guita
 */
public class Vagas {
    private int id;
    private String Vaga;

    public int getId() {
        return id;
    }

    public String getVaga() {
        return Vaga;
    }

    public Vagas(int id, String Vaga) {
        this.id = id;
        this.Vaga = Vaga;
    }
    
    public Vagas(){
        
    }
}
