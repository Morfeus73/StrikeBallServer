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
    private final int SECONDIATTESAINIZIOPARTITA=30;
    
    
    public StrikeServer() {
        listaClient=new ArrayList();
    }
    
    public void apriLobbyPerConnessioni(){
        CountDownTimer cdt = null;
        int secondiRimanenti=SECONDIATTESAINIZIOPARTITA;
        System.out.println("Il Server è in attesa di connessioni nella porta "+porta);
        while(listaClient.size()<NUMEROMASSIMOGIOCATORI && secondiRimanenti>0){
            try(ServerSocket serverSocket = new ServerSocket(porta)){
                serverSocket.setSoTimeout(1000);
                if(listaClient.size()>=2 && cdt==null){
                    cdt = new CountDownTimer(SECONDIATTESAINIZIOPARTITA,this);
                    Thread t = new Thread(cdt);
                    t.start();
                }
                if(cdt!=null)secondiRimanenti=cdt.getSecondiRimanenti();
                
                Socket connessione=serverSocket.accept();
                System.out.println("Connessione stabilita con "+connessione.getInetAddress());
                StrikeClient sc = new StrikeClient(connessione, this);
                sc.start();
                listaClient.add(sc);
            }catch (SocketTimeoutException ex){
                //Tempo di connessione esaurito, creazione di un nuovo serverSocket
            }catch (IOException ex){
                System.err.println("Errore nella creazione del ServerSocket");
            }
        }
        cdt.Stop();
    }
    
    public void InviaMessaggioAiClient(String messaggio){
        for(StrikeClient sc:listaClient){
            sc.InviaMessaggio(messaggio);
        }
    }
    
}
