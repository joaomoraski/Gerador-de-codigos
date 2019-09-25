
package Gerando;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class gerarEntidade {
    

    String nomeDaClasse = "Produto";
    String nomeDaClasseminusculo = "produto";
    List<String> atributo = new ArrayList<>();
    List<String> codigo = new ArrayList<>();
    List<String> codigocontrole = new ArrayList<>();

    public String primeiraLetramaiscula(String s) {
        return String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1, s.length());
    }

    public gerarEntidade() {
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

        codigo.add("package Main;\n"
                + "\n "
                + "\nimport java.util.Date;\n"
                + "public class " + nomeDaClasse + " \n {");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("private " + aux[0] + " " + aux[1] + ";");
        }

        codigo.add("\n"
                + " public " + nomeDaClasse + "() {\n"
                + "    }\n");

       
        String a = "public " + nomeDaClasse + " (";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            String c = aux[0] + " " + aux[1] + ", ";
        a=a+c;
        }
        
        a=a.substring(0,a.length()-2);
        codigo.add(a+") { ");
        
        
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            codigo.add("this." + aux[1] + "= " + aux[1] + ";\n");
        
        }
        codigo.add("}\n\n");
        /*codigo.add(""
                + "this.id = id;\n" 
                + "this.nome = nome;\n" 
                + "this.altura = altura;\n"
                + "this.esporte = esporte;\n" 
                + "}\n");        
*/
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            if (aux[0].equals("boolean") || aux[0].equals("Boolean")) {
                codigo.add("public "+ aux[0] + " is"+ primeiraLetramaiscula(aux[1])+"(){\nreturn "+aux[1]+";\n }");
            }
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
        codigo.add("public String toString() {\n");
 
        
        
        /*        
        String a = "public " + nomeDaClasse + " (";
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            String c = aux[0] + " " + aux[1] + ", ";
        a=a+c;
        }
        
        a=a.substring(0,a.length()-2);
        codigo.add(a+") { ");*/
        String campos = "";
        String acampos = "";
        codigo.add("\treturn ");
        for (int i = 0; i < atributo.size(); i++) {
            String aux[] = atributo.get(i).split(";");
            campos = aux[1] + "+ \";\" +";
            acampos=acampos+campos;
        }
        
        acampos = acampos.substring(0, acampos.length()-1);
        codigo.add(acampos + ";}\n");
        
        //finalizar o codigo
        codigo.add("}");
    
        for (int i = 0; i < codigo.size(); i++) {
        System.out.println(codigo.get(i));
        }
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("C:/Users/jvmor/Documents/NetBeansProjects/Cobaia/src/Main/" + nomeDaClasse + ".java", codigo);
    }
    


};