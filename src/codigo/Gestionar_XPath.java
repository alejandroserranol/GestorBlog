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
 * Gestionar_XPath --- Clase con los métodos utilizados para acceder al contenido del fichero XML con XPath
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_XPath {

    Document doc;
    XPath xpath;
    /**
     * @see Prepara el Document y el XPath para recorrer el fichero.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
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
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @return String con la información que se desea mostrar en el jTextArea.
     */
    public String consultar_post(String _consulta) {

        String cadena_resultado = "";

        String datos_post[] = null;

        try {

            XPathExpression exp = xpath.compile(_consulta);

            Object resultado = exp.evaluate(doc, XPathConstants.NODE);
            Node nodoPost = (Node) resultado;

            datos_post = procesarPost(nodoPost);

            int auxiliar = 0;
            while (auxiliar < datos_post.length) {
                switch (auxiliar) {
                    //Los primeros atributos están desordenados.
                    case 0: cadena_resultado += "En fecha: " + datos_post[auxiliar] + "\t"; break;
                    case 1: cadena_resultado += "Id: " + datos_post[auxiliar] + "\t"; break;
                    case 2: cadena_resultado += "Número de likes: " + datos_post[auxiliar] + "\t"; break;
                    case 3: cadena_resultado += "Tema : " + datos_post[auxiliar] + "\n\n"; break;
                    case 4: cadena_resultado += "Usuario: " + datos_post[auxiliar] + "\n\nDatos personales:\n"; break;
                    case 5: cadena_resultado += "\n\tNombre:\t" + datos_post[auxiliar]; break;
                    case 6: cadena_resultado += "\n\tApellido:\t" + datos_post[auxiliar] + "\n\tRedes sociales:\t"; break;
                    case 7: cadena_resultado += "\n\t\tLinkedin:\t" + datos_post[auxiliar]; break;
                    case 8: cadena_resultado += "\n\t\tGithub:\t" + datos_post[auxiliar] + "\n\tDatos de contacto:\t"; break;
                    case 9: cadena_resultado += "\n\t\tEmail:\t" + datos_post[auxiliar]; break;
                    case 10: cadena_resultado += "\n\t\tTelefono:\t" + datos_post[auxiliar]; break;
                    case 11: cadena_resultado += "\n\t\tDireccion\t" + datos_post[auxiliar]; break;
                    case 12: cadena_resultado += "\nTitulo:\t" + datos_post[auxiliar]; break;
                    case 13: cadena_resultado += "\nTexto:\t" + datos_post[auxiliar]; break;
                    default: break;
                }
                auxiliar++;
            }

            return cadena_resultado;
        } catch (Exception e) {
            return "Error en la ejecución de la salida";
        }
    }
    /**
     * @see Procesa el nodo recibido y devuelve la información deseada.
     * @param _nodoPost: Nodo del que se desea obtener información.
     * @return Array de Strings que contiene la información del nodo.
     */
    private String[] procesarPost(Node _nodoPost) {

        String datos[] = new String[14];
        int contador = 0;

        NodeList nodosHijoDePost = null;
        Node nodoHijoDePost = null;

        String datosUsuario[] = null;

        //Atributos de <Post>
        for (int i = 0; i < _nodoPost.getAttributes().getLength(); i++) {
            datos[contador] = _nodoPost.getAttributes().item(i).getNodeValue();
            contador++;
        }

        //hijos de <Post>
        nodosHijoDePost = _nodoPost.getChildNodes();

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
    /**
     * @see Procesa el nodo recibido y devuelve la información deseada.
     * @param _nodoUsuario: Nodo del que se desea obtener información.
     * @return Array de Strings que contiene la información del nodo.
     */
    private String[] procesaUsuario(Node _nodoUsuario) {

        String datos[] = new String[8];
        int contador = 0;

        NodeList NodosHijosDeUsuario = _nodoUsuario.getChildNodes();
        Node NodoHijoDeUsuario = null;
        NodeList listaAuxiliar = null;
        Node nodoAuxiliar = null;

        //Atributos de <Post>
        for (int i = 0; i < _nodoUsuario.getAttributes().getLength(); i++) {
            datos[contador] = _nodoUsuario.getAttributes().item(contador).getNodeValue();
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
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @return  String con la información que se desea mostrar en el jTextArea.
     */
    public String consultar_atributos(String _consulta) {

        String cadena_resultado = "";
        ArrayList<String> atributos = new ArrayList<>();
        int numeroUsuario = 1;

        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;

            for (int i = 0; i < listaNodos.getLength(); i++) {
                if (!atributos.contains(listaNodos.item(i).getNodeValue())) {
                    atributos.add(listaNodos.item(i).getNodeValue());
                }
            }

            for (String atributo : atributos) {
                cadena_resultado += "Usuario " + numeroUsuario + ":\t" + atributo + "\n";
                numeroUsuario++;
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena_resultado;
    }
    /**
     * @see Procesa la consulta y devuelve un int con la información deseada.
     * @return int con el id del post con más likes.
     */
    public int buscar_post_mas_popular() {
        int id = 1;
        int likesNodoActual = 0;
        int likesNodoSiguiente = 0;
        String consulta = "//@likes";

        try {
            XPathExpression exp = xpath.compile(consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;

            for (int i = 0; i < listaNodos.getLength() - 1; i++) {
                likesNodoActual = Integer.parseInt(listaNodos.item(i).getNodeValue());
                likesNodoSiguiente = Integer.parseInt(listaNodos.item(i + 1).getNodeValue());
                if (likesNodoActual < likesNodoSiguiente) {
                    id = i + 2;
                }
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @return String con la información que se desea mostrar en el jTextArea.
     */
    public String consultar_numero_posts(String _consulta) {

        String cadena_resultado = "Número de posts en el blog:\t";
        int contador = 0;

        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;

            for (int i = 0; i < listaNodos.getLength(); i++) {
                contador++;
            }

            cadena_resultado += String.valueOf(contador);

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena_resultado;
    }
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @param _alias: String con el alias del usuario del que se desea realizar la consulta.
     * @return String con la información que se desea mostrar en el jTextArea.
     */
    String consultar_numero_posts(String _consulta, String _alias) {
        String cadena_resultado = "Número de posts de '"+ _alias +"' en el blog:\t";
        int contador = 0;
        NodeList listaHijosDePost = null;
        Node hijoDePost = null;

        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;

            for (int i = 0; i < listaNodos.getLength(); i++) {

                listaHijosDePost = listaNodos.item(i).getChildNodes();

                for (int j = 0; j < listaHijosDePost.getLength(); j++) {
                    hijoDePost = listaHijosDePost.item(j);
                    if (hijoDePost.getNodeType() == Node.ELEMENT_NODE && hijoDePost.getNodeName().equals("Usuario")) {
                        if (hijoDePost.getAttributes().item(0).getNodeValue().equals(_alias)) {
                            contador++;
                        }
                    }
                }

            }

            cadena_resultado += String.valueOf(contador);

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena_resultado;
    }
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @param _datosUsuario: String que indica si se desea consultar los datos de contacto o las redes sociales.
     * @return String con la información que se desea mostrar en el jTextArea.
     */
    public String consultar_informacion_usuario(String _consulta, String _datosUsuario) {
        String cadena_resultado = "";
        int apuntador = 0;
        String datosContacto[] = { "\tEmail:\t", "\tTeléfono:\t", "\tDirección:\t" };
        String redesSociales[] = { "\tLinkedin:\t", "\tGithub:\t" };
        Node usuario = null;
        boolean isNodoUsuarioCreado = false;
        NodeList listaHijosDeUsuario = null;
        Node hijoDeUsuario = null;

        NodeList listaAuxiliar = null;
        Node auxiliar = null;

        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;

            for (int i = 0; i < listaNodos.getLength(); i++) {
                if (listaNodos.item(i).getNodeType() == Node.ELEMENT_NODE && !isNodoUsuarioCreado) {
                    //Solo necesito un nodo (la información se repite en todos)
                    usuario = listaNodos.item(i);
                    isNodoUsuarioCreado = true;

                    if (_datosUsuario.equals("DatosContacto")) {
                        cadena_resultado += "Datos de contacto:\n";
                        listaHijosDeUsuario = usuario.getChildNodes();
                        for (int j = 0; j < listaHijosDeUsuario.getLength(); j++) {
                            hijoDeUsuario = listaHijosDeUsuario.item(j);
                            if (hijoDeUsuario.getNodeType() == Node.ELEMENT_NODE && hijoDeUsuario.getNodeName().equals(_datosUsuario)) {
                                listaAuxiliar = hijoDeUsuario.getChildNodes();
                                for (int k = 0; k < listaAuxiliar.getLength(); k++) {
                                    auxiliar = listaAuxiliar.item(k);
                                    //Email, telefono dirección
                                    if (auxiliar.getNodeType() == Node.ELEMENT_NODE) {
                                        datosContacto[apuntador] += auxiliar.getChildNodes().item(0).getNodeValue() + "\n";
                                        apuntador++;
                                    }
                                }
                            }
                        }
                        
                        for(String dato: datosContacto){
                            cadena_resultado += dato;
                        }
                    } else if (_datosUsuario.equals("RedesSociales")) {
                        cadena_resultado += "Redes sociales:\n";
                        listaHijosDeUsuario = usuario.getChildNodes();
                        for (int j = 0; j < listaHijosDeUsuario.getLength(); j++) {
                            hijoDeUsuario = listaHijosDeUsuario.item(j);
                            if (hijoDeUsuario.getNodeType() == Node.ELEMENT_NODE && hijoDeUsuario.getNodeName().equals(_datosUsuario)) {
                                listaAuxiliar = hijoDeUsuario.getChildNodes();
                                for (int k = 0; k < listaAuxiliar.getLength(); k++) {
                                    auxiliar = listaAuxiliar.item(k);
                                    //Linkedin y Github
                                    if (auxiliar.getNodeType() == Node.ELEMENT_NODE) {
                                        redesSociales[apuntador] +=  auxiliar.getChildNodes().item(0).getNodeValue() + "\n";
                                        apuntador++;
                                    }
                                }
                            }
                        }
                        for(String red: redesSociales){
                            cadena_resultado += red;
                        }
                    }
                }
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena_resultado;
    }
    /**
     * @see Procesa la consulta y devuelve un string con la información deseada.
     * @param _consulta: String con la información a compilar con el XPathExpression.
     * @return String con la información que se desea mostrar en el jTextArea.
     */
    public String consultar_todos_posts(String _consulta) {
        
        String cadena_resultado = "";
        String datos_post[] = null;

        try {
            XPathExpression exp = xpath.compile(_consulta);
            Object resultado = exp.evaluate(doc, XPathConstants.NODESET);
            NodeList listaNodos = (NodeList) resultado;
            
            for(int i=0;i<listaNodos.getLength();i++){
                datos_post = procesarPost(listaNodos.item(i));
                int auxiliar = 0;
                while (auxiliar < datos_post.length) {
                    switch (auxiliar) {
                        //Los primeros atributos están desordenados.
                        case 0: cadena_resultado += "En fecha: " + datos_post[auxiliar] + "\t"; break;
                        case 1: cadena_resultado += "Id: " + datos_post[auxiliar] + "\t"; break;
                        case 2: cadena_resultado += "Número de likes: " + datos_post[auxiliar] + "\t"; break;
                        case 3: cadena_resultado += "Tema : " + datos_post[auxiliar] + "\n\n"; break;
                        case 4: cadena_resultado += "Usuario: " + datos_post[auxiliar] + "\n\nDatos personales:\n"; break;
                        case 5: cadena_resultado += "\n\tNombre:\t" + datos_post[auxiliar]; break;
                        case 6: cadena_resultado += "\n\tApellido:\t" + datos_post[auxiliar] + "\n\tRedes sociales:\t"; break;
                        case 7: cadena_resultado += "\n\t\tLinkedin:\t" + datos_post[auxiliar]; break;
                        case 8: cadena_resultado += "\n\t\tGithub:\t" + datos_post[auxiliar] + "\n\tDatos de contacto:\t"; break;
                        case 9: cadena_resultado += "\n\t\tEmail:\t" + datos_post[auxiliar]; break;
                        case 10: cadena_resultado += "\n\t\tTelefono:\t" + datos_post[auxiliar]; break;
                        case 11: cadena_resultado += "\n\t\tDireccion\t" + datos_post[auxiliar]; break;
                        case 12: cadena_resultado += "\nTitulo:\t" + datos_post[auxiliar]; break;
                        case 13: cadena_resultado += "\nTexto:\t" + datos_post[auxiliar]; break;
                        default: break;
                    }
                    auxiliar++;
                }
                cadena_resultado += "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
            }


        } catch (XPathExpressionException ex) {
            Logger.getLogger(Gestionar_XPath.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cadena_resultado;
        
    }
}


            
            
                