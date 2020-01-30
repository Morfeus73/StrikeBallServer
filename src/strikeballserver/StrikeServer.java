/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strikeballserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 *
 * @author mirco
 */
public class StrikeServer {
    
    private static int porta=3500;
    private ArrayList<StrikeClient> listaClient;
    private final int NUMEROMASSIMOGIOCATORI=5;
    
    
    public StrikeServer() {
        listaClient=new ArrayList();
        try(ServerSocket serverSocket = new ServerSocket(porta)){

            while(listaClient.size()<NUMEROMASSIMOGIOCATORI){
                System.out.println("Il Server è in attesa di connessioni nella porta "+serverSocket.getLocalPort());
                Socket connessione=serverSocket.accept();
                System.out.println("Connessione stabilita con "+connessione.getInetAddress());
                listaClient.add(new StrikeClient(connessione));
            }
            
        }catch (SocketTimeoutException ex){
            System.err.println("Il server ha impiegato troppo tempo per accettare le connessioni");
        }catch (IOException ex){
            System.err.println("Errore nella creazione del ServerSocket");
        }
    }
    
}
