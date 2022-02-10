/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import control.ExibirVagas;
import control.Pilha;
import control.VagasControl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Vagas;
import view.Tela;

/**
 *
 * @author guita
 */
public class Testes {
    
    public static void main(String args[]) {
        VagasControl vagasControl = new VagasControl();
        Timer timer = new Timer();
        int delay = 1000;  
        int interval = 2000;
        
        vagasControl.showTela();
 
        
        //Atualiza as vagas a cada 2 segundos
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    ArrayList<Vagas> listaVagas = vagasControl.Read();
                    vagasControl.MostrarVagas(listaVagas);
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, delay, interval);
    

      
//        while (true) {
//            try {
//                ArrayList<Vagas> listaVagas = vagasControl.Read();               
//                vagasControl.MostrarVagas(listaVagas);
//            } catch (Exception e) {
//                Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, e);
//            }            
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
    }
}
