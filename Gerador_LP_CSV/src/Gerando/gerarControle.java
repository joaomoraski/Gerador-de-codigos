
package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class gerarControle {
    
    String nomeDaClasse = "Atleta";
    String nomeDaClasseminusculo = "atleta";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    
    public String primeiraLetramaiscula(String s){
       return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1,s.length());
    }
    
    public gerarControle(){
        //Gerando a classe
        atributo.add("int;id");
        atributo.add("String;nome");
        atributo.add("double;altura");
        atributo.add("String;esporte");
        atributo.add("double;peso");

        String get = "get";
        String set = "set";
       
        //nomeDaClasseminusculo = nomeDaClasse.toLowerCase();
        
        codigo.add("package Main; \n \n"
                + "import java.util.ArrayList;\n "
                + "import java.util.List; \n \n"
                + "public class "+ nomeDaClasse + "Controle {  \n "
                + "\n "
                + ""
                + "List<"+nomeDaClasse+"> lista = new ArrayList<>();\n"
                + "public AtletaControle(){ \n}"
                );
        
        codigo.add("public void limparLista(){"
                + "\t lista.clear();");

    
        
        
        codigo.add("\n }"
                + "\n"
                + "public void adicionar("+ nomeDaClasse+" "+ nomeDaClasseminusculo+"){\n"
                        + "lista.add("+nomeDaClasseminusculo + ");\n } \n");
        
        codigo.add("\n"
                + "public List<"+nomeDaClasse+"> listar(){\n"
                        + "\t return lista; \n "
                        + "}");
        
        codigo.add("public " + nomeDaClasse + " buscar("
                +atributo.get(0).split(";")[0]
                +" "
                +atributo.get(0).split(";")[1]
                +") {");
                
        codigo.add("for (int i=0; i < lista.size(); i++)"
                + " { \n if (lista.get(i).get" 
                + primeiraLetramaiscula(atributo.get(0).split(";")[1]) + "()");
                if (atributo.get(0).split(";")[0].equals("int")) {
                    codigo.add(" == (");
                }else if (atributo.get(0).split(";")[0].equals("String")){
                    codigo.add(".equals(");
                }else if (atributo.get(0).split(";")[0].equals("double")){
                    codigo.add(" == (");
                } 
                codigo.add(atributo.get(0).split(";")[1]
                        +")) {");
        
        codigo.add("return lista.get(i);"
                + " \n } \n } "
                + "return null; "
                + "\n }");
        
        
        
        codigo.add("public void alterar("+nomeDaClasse + " " + nomeDaClasseminusculo + ", " 
                + nomeDaClasse + " " + nomeDaClasseminusculo + "Antigo) {\n"
                        + "\t lista.set(lista.indexOf(" + nomeDaClasseminusculo+"Antigo)"+", " + nomeDaClasseminusculo + ");\n}");
        
        codigo.add("public void excluir(" + nomeDaClasse + " " +  nomeDaClasseminusculo + ") {\n \t" + "lista.remove(" + nomeDaClasseminusculo+");\n}" + "\n \n }");
    
    
        
        
        //finalizar o codigo
        //codigo.add("}");
    
        
        for (int i = 0; i < codigo.size(); i++) {
        System.out.println(codigo.get(i));
        }
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + "Controle" + ".java", codigo);
    }
    


};