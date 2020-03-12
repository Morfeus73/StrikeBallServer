/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strikeballserver;

/**
 *
 * @author mirco
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        StrikeServer server =new StrikeServer();
        server.apriLobbyPerConnessioni();
        System.out.println("Inizio della partita!");
        server.InviaMessaggioAiClient("Inizio della partita!");
        server.InviaMessaggioAiClient("È possibile inserire i seguenti caratteri: " + Partita.getColorazioni());
        
        Partita.generaSoluzione();
        System.out.println(Partita.getSoluzione());
    }
    
}
