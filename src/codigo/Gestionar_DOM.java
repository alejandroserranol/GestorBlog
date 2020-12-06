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
 *
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_DOM {

    Document doc;

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

    public int contarPosts() {
        int numeroPosts = 0;
        Node raiz = doc.getFirstChild();
        NodeList listaPosts = raiz.getChildNodes();

        for (int i = 0; i < listaPosts.getLength(); i++) {
            if (listaPosts.item(i).getNodeType() == Node.ELEMENT_NODE) {
                numeroPosts++;
            }
        }
        return numeroPosts;
    }

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

    private String[] ordenaElementos(String[] _campos) {

        String auxiliar[] = new String[14];
        String id = String.valueOf(contarPosts() + 1);
        String likes = "0";
        
        int contador = 0;
        while(contador<4){
            switch(contador){
                //Añado los primeros en un switch porque algunos atributos no están en el array _campos
                case 0: auxiliar[contador] = id; contador++; break;
                case 1: auxiliar[contador] = _campos[0]; contador++; break;
                case 2: auxiliar[contador] = _campos[1]; contador++; break;
                case 3: auxiliar[contador] = likes; contador++; break;
                default: break;
            }
        }

        for(int i=2; i<auxiliar.length-2; i++){
            auxiliar[contador] = _campos[i];
            contador++;
        }
      
        return auxiliar;
    }
    
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
        Node nredesSociales = doc.createElement("DatosContacto");
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
        ((Element)npost).setAttribute("id", _elementosOrdenados[1]);
        apuntador++;
        ((Element)npost).setAttribute("fecha", _elementosOrdenados[0]);
        apuntador++;
        ((Element)npost).setAttribute("tema", _elementosOrdenados[3]);
        apuntador++;
        ((Element)npost).setAttribute("likes", _elementosOrdenados[2]);
        apuntador++;
        
        npost.appendChild(nusuario);
        npost.appendChild(nTitulo);
        npost.appendChild(ntexto);
        
        return npost;
    }

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
