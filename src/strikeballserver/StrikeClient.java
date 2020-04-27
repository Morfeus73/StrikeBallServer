/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strikeballserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirco
 */
public class StrikeClient extends Thread {
    
    private Socket socket;
    private StrikeServer server;
    private BufferedReader Lettore;
    private PrintWriter Scrittore;
    private String nickname;
    
    public StrikeClient(Socket socket, StrikeServer server){
        this.socket=socket;
        this.server=server;
        try {
            Lettore = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scrittore = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(StrikeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        //Attesa della Fase 1...
        while(server.fase!=1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StrikeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Inserimento del Nickname
        InviaMessaggio("Inserire il nickname da utilizzare in questa partita!");
        try {
            String s=LeggiMessaggio();
            while(!server.isNicknameDisponibile(s)){
                InviaMessaggio("Il nickname inserito non è disponibile!");
                s=LeggiMessaggio();
            }
            nickname=s;
        } catch (IOException ex) {
            Logger.getLogger(StrikeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Attesa della Fase 2...
        while(server.fase!=2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StrikeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            InviaMessaggio("Attendere che tutti i giocatori inseriscano il nickname!");
        }
            
        //Fase del gioco
            InviaMessaggio("Inizio della partita!\nÈ possibile inserire i seguenti caratteri: " + Partita.getColorazioni());
            while(server.fase!=3){
                try {
                    String risposta=Partita.controllaSoluzione(LeggiMessaggio(), this);
                    InviaMessaggio(risposta);
                } catch (IOException ex) {
                    Logger.getLogger(StrikeClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
    }
    
    public void InviaMessaggio(String messaggio){
        Scrittore.println(messaggio);
    }
    
    public String LeggiMessaggio() throws IOException{
        return Lettore.readLine();
    }

    public String getNickname() {
        return nickname;
    }
    
    
}
