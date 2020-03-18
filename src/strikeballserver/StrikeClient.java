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
        
        while(socket.isConnected() && !socket.isClosed()){
            
            try {
                String risposta=Partita.controllaSoluzione(LeggiMessaggio());
                InviaMessaggio(risposta);
                if(risposta.equals("Hai Vinto!")){
                    socket.close();
                    System.out.println("Ho chiuso!");
                }
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
}
