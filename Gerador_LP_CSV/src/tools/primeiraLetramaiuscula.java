package tools;

public class primeiraLetramaiuscula {

    public String primeiraLetramaiscula(String s){
       return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1,s.length());
    }
        
}
