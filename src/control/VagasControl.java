/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Uteis.Entradas;
import dao.Read;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Vagas;
import test.Testes;
import view.Tela;
import java.util.List;
import java.util.Timer;


/**
 *
 * @author guita
 */
public class VagasControl {
    protected Tela tela = new Tela();
    
    public void showTela(){
        tela.setVisible(true);
    }
    
    
    private Pilha QualEntrada(Entradas entrada){
        ///Carrega a ordem em que as vagas devem ser indicadas
        Pilha p = new Pilha();
        
        String linha = new String();
        String nomeArquivo = null ;
             
        switch(entrada){
            case PRINCIPAL:
                nomeArquivo = "C:\\Users\\guita\\Desktop\\TCC\\TCCtest\\src\\EstruturaVagas\\Principal.txt";
                break; 
            case SECUNDARIA:
                nomeArquivo = "C:\\Users\\guita\\Desktop\\TCC\\TCCtest\\src\\EstruturaVagas\\Lateral.txt";
                break;
        }
        
        File arq = new File(nomeArquivo);
        
        if(arq.exists()){
            try {
                FileReader leitorDeArquivo = new FileReader(nomeArquivo);
                BufferedReader bufferDeArquivo = new BufferedReader(leitorDeArquivo);
                
                //Carrega a pilha com os valores das vagas 
                while (true) {                    
                    linha = bufferDeArquivo.readLine();
                    if (linha == null) {
                        break;
                    }
                    p.push(Integer.parseInt(linha));
                }
            } catch (IOException | NumberFormatException e) {
                Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        return p; 
    }
    
    public ArrayList<Vagas> Read() {
        ArrayList<Vagas> listaVagas = new ArrayList();
        try {
            listaVagas = Read.read();
            
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, e);
        }
        return listaVagas;
    }
    
    public int EncotrarMaisProxima(ArrayList<Vagas> listaVagas, Entradas entrada) {
        Pilha p = QualEntrada(entrada);
   
        Vagas vaga = new Vagas();
        int v = 0;

        for (int i = 0; i < listaVagas.size(); i++) {
            v = (p.pop()) - 1;
            vaga = listaVagas.get(v);
            if (vaga.getVaga().equals("0")) {
                return v + 1;
            }
        }
        return -1;
    }
    
    
    public void MostrarVagas(ArrayList<Vagas> listaVagas) {         
        try {
            
            ImageIcon imgVerdeReto = new ImageIcon(getClass().getResource("/imagens/VerdeReto.png"));
            ImageIcon imgVerdeTorto = new ImageIcon(getClass().getResource("/imagens/VerdeTorto.png"));    
            ImageIcon imgVermelhoReto = new ImageIcon(getClass().getResource("/imagens/VermelhoReto.png"));
            ImageIcon imgVermelhoTorto = new ImageIcon(getClass().getResource("/imagens/VermelhoTorto.png"));
            
            JLabel[] ListaLbls = tela.getLbl();
            
           
             
            //Lista de vagas em 45 graus
            List<Integer> lista45 = Arrays.asList(new Integer[]{14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
                31,31,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,51,52,53,54,
                55,56,57,58,59,60,61,62,63,64,65,66});
            
    
            for(int i = 0; i < listaVagas.size(); i++){
                
                if(listaVagas.get(i).getVaga().equals("0")){
                    //Verifica se a vaga esta livre
                    if(lista45.contains(i+1)){
                        //Verifica se a vaga está em 45 graus para inserir a imagem correta
                        ListaLbls[i].setIcon(imgVerdeTorto);
                    }else{
                        ListaLbls[i].setIcon(imgVerdeReto);
                    }             
                }
                else{
                    if(lista45.contains(i+1)){
                        //Verifica se a vaga está em 45 graus para inserir a imagem correta
                        ListaLbls[i].setIcon(imgVermelhoTorto);
                    }
                    else{
                        ListaLbls[i].setIcon(imgVermelhoReto);
                        System.out.println("teste git");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
//    public final void AtualizarVagas() {
//        int delay = 2000;  
//        int interval = 5000;
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    VagasControl vagasControl = new VagasControl();
//                    ArrayList<Vagas> listaVagas = vagasControl.Read();
//                    vagasControl.MostrarVagas(listaVagas);
//
//                    
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }, delay, interval);
//    }
}
