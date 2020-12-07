/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_XPath {

    Document doc;
    XPath xpath;

    public int preparar_XPath(File _fichero) {

        //doc representará el árbol DOM.
        doc = null;

        try {
            //Crea un fichero DocumentBuilderFactory para el DOM (XAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Atributos del documento.
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            //Se crea un objeto DocumentBuilder para cargar en él
            //la estructura de árbol DOM de un fichero seleccionado
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Interpreta este fichero XML, lo mapea y guarda en memoria y
            //da el apuntador a la raíz.
            doc = builder.parse(_fichero);
            //Ahorastá listo para ser recorrido.

            xpath = XPathFactory.newInstance().newXPath();

            return 0;
        } catch (Exception e) {
            return -1;
        }

    }

    String consultar_post(String _consulta) {

        String cadena_resultado = "";
        
        String datos_post[] = null;

        try {

            XPathExpression exp = xpath.compile(_consulta);

            Object resultado = exp.evaluate(doc, XPathConstants.NODE);
            Node nodoPost = (Node) resultado;
            
            datos_post = procesarPost(nodoPost);

            int auxiliar = 0;
            while(auxiliar<datos_post.length){
                switch(auxiliar){
                    //Los primeros atributos están desordenados.
                    case 0: cadena_resultado += "En fecha: " + datos_post[auxiliar] + "\t";break;
                    case 1: cadena_resultado += "Id: " + datos_post[auxiliar] + "\t";break;
                    case 2: cadena_resultado += "Número de likes: " + datos_post[auxiliar] + "\t";break;
                    case 3: cadena_resultado += "Tema : " + datos_post[auxiliar] + "\n\n";break;
                    case 4: cadena_resultado += "Usuario: " + datos_post[auxiliar] + "\n\nDatos personales:\n";break;
                    case 5: cadena_resultado += "\n\tNombre:\t" + datos_post[auxiliar];break;
                    case 6: cadena_resultado += "\n\tApellido:\t" + datos_post[auxiliar]+"\n\tRedes sociales:\t";break;
                    case 7: cadena_resultado += "\n\t\tLinkedin:\t" + datos_post[auxiliar];break;
                    case 8: cadena_resultado += "\n\t\tGithub:\t" + datos_post[auxiliar]+"\n\tDatos de contacto:\t";break;
                    case 9: cadena_resultado += "\n\t\tEmail:\t"+ datos_post[auxiliar];break;
                    case 10:cadena_resultado += "\n\t\tTelefono:\t" + datos_post[auxiliar];break;
                    case 11:cadena_resultado += "\n\t\tDireccion\t" + datos_post[auxiliar];break;
                    case 12:cadena_resultado += "\nTitulo:\t" + datos_post[auxiliar];break;
                    case 13:cadena_resultado += "\nTexto:\t" + datos_post[auxiliar];break;
                    default:break;
                }
                auxiliar++;
            }

            return cadena_resultado;
        } catch (Exception e) {
            return "Error en la ejecución de la salida";
        }
    }

    private String[] procesarPost(Node nodoPost) {

        String datos[] = new String[14];
        int contador = 0;

        NodeList nodosHijoDePost = null;
        Node nodoHijoDePost = null;

        String datosUsuario[] = null;

        //Atributos de <Post>
        for(int i=0; i<nodoPost.getAttributes().getLength(); i++){
            datos[contador] = nodoPost.getAttributes().item(i).getNodeValue();
            contador++;
        }

        //hijos de <Post>
        nodosHijoDePost = nodoPost.getChildNodes();

        for (int j = 0; j < nodosHijoDePost.getLength(); j++) {
            nodoHijoDePost = nodosHijoDePost.item(j);

            if (nodoHijoDePost.getNodeType() == Node.ELEMENT_NODE) {

                if (nodoHijoDePost.getNodeName().equals("Usuario")) {
                    //Atributos y elementos de <Usuario>
                    datosUsuario = procesaUsuario(nodoHijoDePost);

                    for (int k = 0; k < datosUsuario.length; k++) {
                        datos[contador] = datosUsuario[k];
                        contador++;
                    }
                } else {
                    //Nodo <Titulo> y nodo <Texto>
                    datos[contador] = nodoHijoDePost.getFirstChild().getNodeValue();
                    contador++;
                }
            }

        }
        return datos;
    }

    private String[] procesaUsuario(Node nodoUsuario) {

        String datos[] = new String[8];
        int contador = 0;

        NodeList NodosHijosDeUsuario = nodoUsuario.getChildNodes();
        Node NodoHijoDeUsuario = null;
        NodeList listaAuxiliar = null;
        Node nodoAuxiliar = null;

        //Atributos de <Post>
        for (int i = 0; i < nodoUsuario.getAttributes().getLength(); i++) {
            datos[contador] = nodoUsuario.getAttributes().item(contador).getNodeValue();
            contador++;
        }

        for (int j = 0; j < NodosHijosDeUsuario.getLength(); j++) {
            NodoHijoDeUsuario = NodosHijosDeUsuario.item(j);

            if (NodoHijoDeUsuario.getNodeType() == Node.ELEMENT_NODE) {

                if (NodoHijoDeUsuario.getNodeName().equals("RedesSociales") || NodoHijoDeUsuario.getNodeName().equals("DatosContacto")) {
                    listaAuxiliar = NodoHijoDeUsuario.getChildNodes();

                    for (int k = 0; k < listaAuxiliar.getLength(); k++) {
                        nodoAuxiliar = listaAuxiliar.item(k);
                        if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE) {
                            datos[contador] = nodoAuxiliar.getFirstChild().getNodeValue();
                            contador++;
                        }
                    }
                } else {
                    datos[contador] = NodoHijoDeUsuario.getFirstChild().getNodeValue();
                    contador++;
                }
            }
        }
        return datos;
    }

    public String consultar_usuarios(String _consulta) {
        
        String cadena_resultado = "";
        ArrayList<String> usuarios = new ArrayList<>();
        int numeroUsuario = 1;
        
        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;
            
            for(int i=0; i<listaNodos.getLength();i++){
                if(!usuarios.contains(listaNodos.item(i).getNodeValue())){
                    usuarios.add(listaNodos.item(i).getNodeValue());
                }                
            }
            
            for(String usuario: usuarios){
                cadena_resultado += "Usuario "+ numeroUsuario + ":\t" + usuario + "\n";
                numeroUsuario++;
            }
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cadena_resultado;
    }

    String consultar_temas(String _consulta) {
        
        
        String cadena_resultado = "";
        ArrayList<String> temas = new ArrayList<>();
        int numeroUsuario = 1;
        
        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;
            
            for(int i=0; i<listaNodos.getLength();i++){
                if(!temas.contains(listaNodos.item(i).getNodeValue())){
                    temas.add(listaNodos.item(i).getNodeValue());
                }                
            }
            
            for(String tema: temas){
                cadena_resultado += "Tema "+ numeroUsuario + ":\t" + tema + "\n";
                numeroUsuario++;
            }
            
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cadena_resultado;
    }

}
