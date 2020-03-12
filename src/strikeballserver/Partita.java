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
public class Partita {
    
    private static final String[] coloriNome = {"R", "G", "V", "A", "B", "R", "N"};
    private static final String[] coloriConsole = {"\u001b[31m", "\u001b[33m", "\u001b[32m", "\u001b[36m", "\u001b[34m", "\u001b[35m", "\u001b[30m"};
    
    private static String soluzione;
    
    public static void generaSoluzione(){
        soluzione="";
        for(int i=0;i<4;i++){
            soluzione+=coloriNome[(int)(Math.random() * coloriNome.length)];
        }
    }
    
    public static String getColorazioni(){
        String s="";
        for(int i=0;i<coloriNome.length;i++){
            s+=coloriConsole[i]+coloriNome[i]+" ";
        }
        return s;
    }
    
    public static String getSoluzione(){
        return soluzione;
    }
}
