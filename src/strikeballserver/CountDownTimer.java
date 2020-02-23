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
    private int secondi;
    private int secondiRimanenti;
    private final Integer[] numbers = {60,45,30,20,15,10,5,4,3,2,1};
    
    public CountDownTimer(int secondi, StrikeServer strikeServer){
        this.secondi=secondi;
        this.secondiRimanenti = secondi;
        this.strikeServer=strikeServer;
    }
    
    @Override
    public void run() {
        while(secondiRimanenti>0){
            
            if(Arrays.asList(numbers).contains(secondiRimanenti)) {
            System.out.println(secondiRimanenti+" secondi rimanenti!");
            strikeServer.InviaMessaggioAiClient("La partita inizierà tra "+secondiRimanenti+ " secondi!");
            }
            
            try {
                secondiRimanenti--;
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                //I don't think you need to do anything for your particular problem
            }
        }
    }
    
    public void Stop(){
        secondiRimanenti=0;
    }
    
    public int getSecondi(){
        return secondi;
    }
    
    public int getSecondiRimanenti(){
        return secondiRimanenti;
    }
    
}











