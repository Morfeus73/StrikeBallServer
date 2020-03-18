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
    
    private static final String[] coloriNome = {"R", "G", "V", "A", "B", "F", "N"};
    private static final String[] coloriConsole = {"\u001b[31m", "\u001b[33m", "\u001b[32m", "\u001b[36m", "\u001b[34m", "\u001b[35m", "\u001b[30m"};
    
    private static final int lunghezzaSoluzione = 4;
    private static String soluzione;
    
    public static void generaSoluzione(){
        soluzione="";
        for(int i=0;i<lunghezzaSoluzione;i++){
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
    
    //la proposta di soluzione deve essere una stringa di caratteri senza spazi
    public static String controllaSoluzione(String propostaSoluzione){
        if(propostaSoluzione.equals(soluzione))return "Hai Vinto!";
        int strike = 0, ball = 0;
        char[] soluzioneModificabile = soluzione.toCharArray();
        char[] proposta = propostaSoluzione.toCharArray();
        
        for(int i=0;i<lunghezzaSoluzione;i++){
            if(soluzioneModificabile[i]==proposta[i]){
                soluzioneModificabile[i]=' ';
                proposta[i]=' ';
                strike++;
            }
        }
        
        for(int i=0;i<lunghezzaSoluzione;i++){
            for(int j=0;j<lunghezzaSoluzione && proposta[i]!=' ';j++){
                if(proposta[i]==soluzioneModificabile[j]){
                    soluzioneModificabile[j]=' ';
                    proposta[i]=' ';
                    ball++;
                }
            }
        }

        return "Hai effettuato "+strike+" Strike e "+ball+" Ball!";
    }
}
