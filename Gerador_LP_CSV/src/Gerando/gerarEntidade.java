
package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class gerarEntidade {
    
    String nomeDaClasse = "Atleta";
    String nomeDaClasseminusculo = "atleta";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();
    
    public String primeiraLetramaiscula(String s){
       return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1,s.length());
    }
    
    public gerarEntidade(){
        //Gerando a classe
        atributo.add("int;id");
        atributo.add("String;nome");
        atributo.add("double;altura");
        atributo.add("String;esporte");
        atributo.add("double;peso");

        String get = "get";
        String set = "set";

        codigo.add("package Main;\n"
                + "public class " + nomeDaClasse + " \n {");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("private " + aux[0] + " " + aux[1] + ";");
        }

        codigo.add("\n"
                + " public " + nomeDaClasse + "() {\n"
                + "    }\n");

       
        String a = "public "+nomeDaClasse + " (";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            String c = aux[0] + " " + aux[1] + ", ";
        a=a+c;
        }
        
        a=a.substring(0,a.length()-2);
        codigo.add(a+") { ");
        
        codigo.add(""
                + "this.id = id;\n" 
                + "this.nome = nome;\n" 
                + "this.altura = altura;\n"
                + "this.esporte = esporte;\n" 
                + "}\n");        

        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("public "+aux[0] +" get"+primeiraLetramaiscula(aux[1])+"(){\nreturn "+aux[1]+";\n }");
            codigo.add("public void"
                    +" set"
                    +primeiraLetramaiscula(aux[1])
                    +"("+aux[0]
                    +"  "+aux[1]
                    +"){\n this."
                    +aux[1]
                    +" = "
                    +aux[1]+";\n }");
         }
        
        
        
       
        
        codigo.add("@Override \n");
        codigo.add("public String toString() {");
 
        String s1 = " ";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            String s = "\""+aux[1]+"=\""+" + "+aux[1]+" + ";
            s1=s1+s;
                }
            codigo.add("return "+"\""+nomeDaClasse+"{\""+"+"+s1+"\"}\";");
            codigo.add("}");
        
        
        //finalizar o codigo
        codigo.add("}");
    
        for (int i = 0; i < codigo.size(); i++) {
        System.out.println(codigo.get(i));
        }
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + ".java", codigo);
    }
    


};