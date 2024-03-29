
package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class gerarControle {
    
    
    String nomeDaClasse = "Produto";
    String nomeDaClasseminusculo = "produto";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public String primeiraLetramaiscula(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }

    public gerarControle() {
        atributo.add("long;idProduto");
        atributo.add("double;precoUnitario");
        atributo.add("Date;dataDeCadastro");

        //atributo.add("boolean;aposentado");
        //atributo.add("Date;data");
        //primeira letra maiscula
        String pk = "idProduto"; //indique qual atributo é chave primaria 
        String pkt = "long"; //indique qual é o tipo da váriavel pk 
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
                + "public "+ nomeDaClasse +"Controle(){ \n}"
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
                if (atributo.get(0).split(";")[0].equals("int") || (atributo.get(0).split(";")[0].equals("Int"))) {
                    codigo.add(" == (");
                }else if (atributo.get(0).split(";")[0].equals("String") || (atributo.get(0).split(";")[0].equals("string"))){
                    codigo.add(".equals(");
                }else if (atributo.get(0).split(";")[0].equals("double") || (atributo.get(0).split(";")[0].equals("Double"))){
                    codigo.add(" == (");
                }else if(atributo.get(0).split(";")[0].equals("long") || (atributo.get(0).split(";")[0].equals("Long"))){
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