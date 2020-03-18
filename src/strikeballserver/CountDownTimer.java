/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strikeballserver;

import java.util.Arrays;


/**
 *
 * @author mirco
 */
public class CountDownTimer implements Runnable{
    private StrikeServer strikeServer;
    private int secondiRimanenti;
    private String msg;
    private final Integer[] numeriAvvisati = {60,45,30,20,15,10,5,3,2,1};
    
    public CountDownTimer(int secondi, StrikeServer strikeServer, String msg){
        this.secondiRimanenti = secondi;
        this.strikeServer=strikeServer;
        this.msg=msg;
    }
    
    @Override
    public void run() {
        while(secondiRimanenti>0){
            
            if(Arrays.asList(numeriAvvisati).contains(secondiRimanenti)) {
                System.out.println(secondiRimanenti+" secondi rimanenti!");
                strikeServer.InviaMessaggioAiClient(msg+secondiRimanenti+ " secondi!");
            }
            
            try {
                secondiRimanenti--;
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.err.println("Interruzione inaspettata del CountDownTimer");
            }
        }
    }
    
    public void Stop(){
        secondiRimanenti=0;
    }
    
    public int getSecondiRimanenti(){
        return secondiRimanenti;
    }
    
}











