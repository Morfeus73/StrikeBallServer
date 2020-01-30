/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strikeballserver;

import java.net.Socket;

/**
 *
 * @author mirco
 */
public class StrikeClient implements Runnable {
    
    private Socket socket;
    
    public StrikeClient(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
