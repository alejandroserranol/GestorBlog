/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 * Gestionar_DOM --- Clase con los métodos utilizados para acceder al contenido del fichero XML con DOM
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_DOM {

    Document doc;
    /**
     * Prepara el Document para recorrer el fichero.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int preparar_DOM(File _fichero) {

        //doc representará el árbol DOM.
        doc = null;

        try {
            //Se crea un objeto DocumentBuilderFactory.
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

            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    /**
     * @see Cuenta el número de posts en el blog.
     * @return int con el número de posts en el blog.
     */
    public int contarPosts() {
        int numeroPosts = 0;
        Node raiz = doc.getFirstChild();
        NodeList listaPosts = raiz.getChildNodes();

        for (int i = 0; i < listaPosts.getLength(); i++) {
            if (listaPosts.item(i).getNodeType() == Node.ELEMENT_NODE && listaPosts.item(i).getNodeName().equals("Post")) {
                numeroPosts++;
            }
        }
        return numeroPosts;
    }
    /**
     * @see Modifica el DOM para añadir un elemento Post y lo guarda en el disco duro.
     * @param _campos: Array de Strings que contiene los elementos utilizados para crear un nodo Post.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int publicarPost(String[] _campos, File _fichero) {

        try {
            
            String elementosOrdenados[] = ordenaElementos(_campos);
            
            Node npost = crearNodoPost(elementosOrdenados);
            
            Node raiz = doc.getFirstChild();
            raiz.appendChild(npost);
            
            guardar_DOM_como_fichero(_fichero);
            
            return 0;
        } catch (Exception e) {
            return -1;
        }

    }
    /**
     * @see Añade los elementos no introducidos por el usuario y lo devuelve ordenado.
     * @param _campos: Array de Strings que contiene los elementos utilizados para crear un nodo Post.
     * @return Array de Strings con la información que se desea añadir al nuevo nodo post.
     */
    private String[] ordenaElementos(String[] _campos) {

        String auxiliar[] = new String[14];
        String id = String.valueOf(contarPosts() + 1);
        String likes = "0";
        
        

        for(int i=0; i<auxiliar.length; i++){
            if(i==0){
                auxiliar[i] = _campos[i];
            } else if(i==1){
                auxiliar[i] = id;
            } else if(i==2){
                auxiliar[i] = likes;
            } else {
                //el array campos tiene dos elementos menos que auxiliar
                auxiliar[i] = _campos[i-2];
            }
        }
      
        return auxiliar;
    }
    /**
     * @see Crea los nodos descendientes del nodo Post
     * @param _elementosOrdenados: Array de Strings con la información que se desea añadir al nuevo nodo post.
     * @return Nodo post a añadir a la estructura del DOM.
     */
    private Node crearNodoPost(String[] _elementosOrdenados) {
        
        int apuntador = _elementosOrdenados.length-1;
        //Hijo de Post
        Node ntexto = doc.createElement("Texto");
        Node ntexto_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        ntexto.appendChild(ntexto_text);
        apuntador--;
        //Hijo de Post
        Node nTitulo = doc.createElement("Titulo");
        Node nTitulo_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        nTitulo.appendChild(nTitulo_text);
        apuntador--;
        
        Node ndireccion = doc.createElement("Direccion");
        Node ndireccion_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        ndireccion.appendChild(ndireccion_text);
        apuntador--;
        
        Node ntelefono = doc.createElement("Telefono");
        Node ntelefono_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        ntelefono.appendChild(ntelefono_text);
        apuntador--;
        
        Node nemail = doc.createElement("Email");
        Node nemail_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        nemail.appendChild(nemail_text);
        apuntador--;
        //Hijo de Usuario
        Node ndatosContacto = doc.createElement("DatosContacto");
        ndatosContacto.appendChild(nemail);
        ndatosContacto.appendChild(ntelefono);
        ndatosContacto.appendChild(ndireccion);
        
        Node ngithub = doc.createElement("Github");
        Node ngithub_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        ngithub.appendChild(ngithub_text);
        apuntador--;
        
        Node nlinkedin = doc.createElement("Linkedin");
        Node nlinkedin_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        nlinkedin.appendChild(nlinkedin_text);
        apuntador--;        
        //Hijo de Usuario
        Node nredesSociales = doc.createElement("RedesSociales");
        nredesSociales.appendChild(nlinkedin);
        nredesSociales.appendChild(ngithub);
        //Hijo de Usuario
        Node napellido = doc.createElement("Apellido");
        Node napellido_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        napellido.appendChild(napellido_text);
        apuntador--;
        //Hijo de Usuario
        Node nnombre = doc.createElement("Nombre");
        Node nnombre_text = doc.createTextNode(_elementosOrdenados[apuntador]);
        nnombre.appendChild(nnombre_text);
        apuntador--;
        //Hijo de Post
        Node nusuario = doc.createElement("Usuario");
        ((Element)nusuario).setAttribute("alias", _elementosOrdenados[apuntador]);
        apuntador--;
        
        nusuario.appendChild(nnombre);
        nusuario.appendChild(napellido);
        nusuario.appendChild(nredesSociales);
        nusuario.appendChild(ndatosContacto);
        
        Node npost = doc.createElement("Post");
        apuntador = 0;
        ((Element)npost).setAttribute("fecha", _elementosOrdenados[apuntador]);
        apuntador++;
        ((Element)npost).setAttribute("id", _elementosOrdenados[apuntador]);
        apuntador++;
        ((Element)npost).setAttribute("likes", _elementosOrdenados[apuntador]);
        apuntador++;
        ((Element)npost).setAttribute("tema", _elementosOrdenados[apuntador]);
        apuntador++;
        
        npost.appendChild(nusuario);
        npost.appendChild(nTitulo);
        npost.appendChild(ntexto);
        
        return npost;
    }
    /**
     * @see Guarda la estructura del DOM almacenada en memoria en el disco duro.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @throws IOException 
     */
    private void guardar_DOM_como_fichero(File _fichero) throws IOException {

        try {
            //Especifico el formato de salida.
            OutputFormat format = new OutputFormat(doc);
            //Especifica que la salida esté indentada
            format.setIndenting(true);

            XMLSerializer serializer;

            serializer = new XMLSerializer(new FileOutputStream(_fichero), format);
            serializer.serialize(doc);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gestionar_DOM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
