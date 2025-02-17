/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Gestionar_SAX --- Clase con los métodos utilizados para acceder al contenido del fichero XML con SAX.
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_SAX {

    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML;

    /**
     * @see Prepara el SAXParser y el ManejadorSAX para recorrer el fichero.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int preparar_SAX(File _fichero) {

        try {
            //Se crea un objeto SAXParser para interpretar el documentoXML.
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();

            //Se crea una instancia del manejador que será el que recorra
            //el documento XML secuencialmente
            sh = new ManejadorSAX();
            ficheroXML = _fichero;

            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    /**
     * @see Recorre el fichero XML y maneja las interrupciones con la clase ManejadorSAX.
     * @return String que se desea mostrar en pantalla.
     */
    public String recorrerSAX() {
        try {
            sh.cadena_resultado = "";
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;

        } catch (SAXException ex) {
            return "Error al parsear con SAX";
        } catch (IOException ex) {
            return "Error al parsear con SAX";
        }
    }

}
/**
 * ManejadorSAX --- Maneja las interrupciones que se producen al recorrer el fichero XML con SAX.
 * @author    Alejandro Serrano Loredo
 */
class ManejadorSAX extends DefaultHandler {

    String cadena_resultado = "";

    //Este método se ejecuta cuando se encuentra un elemento de apertura
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equals("Blogs")) {
            cadena_resultado = cadena_resultado + "Se van a mostrar los posts de este blog\n************************************************\n";
        } else if (qName.equals("Post")) {
            cadena_resultado = cadena_resultado + "\nEn fecha: "
                    + attributes.getValue(attributes.getQName(0));
            cadena_resultado = cadena_resultado + "\tId: "
                    + attributes.getValue(attributes.getQName(1));
            cadena_resultado = cadena_resultado + "\tNúmero de likes: "
                    + attributes.getValue(attributes.getQName(2));
            cadena_resultado = cadena_resultado + "\tTema : "
                    + attributes.getValue(attributes.getQName(3));
        } else if (qName.equals("Usuario")) {
            cadena_resultado = cadena_resultado + "\nUsuario: "
                    + attributes.getValue(attributes.getQName(0));
            cadena_resultado = cadena_resultado + "\n\nDatos personales:";
        } else if (qName.equals("Nombre")) {
            cadena_resultado = cadena_resultado + "\tNombre:\t";
        } else if (qName.equals("Apellido")) {
            cadena_resultado = cadena_resultado + "\tApellido:\t";
        } else if (qName.equals("RedesSociales")) {
            cadena_resultado = cadena_resultado + "\n\tRedes sociales:\t";
        } else if (qName.equals("Linkedin")) {
            cadena_resultado = cadena_resultado + "\t\tLinkedin:\t";
        } else if (qName.equals("Github")) {
            cadena_resultado = cadena_resultado + "\t\tGithub:\t";
        } else if (qName.equals("DatosContacto")) {
            cadena_resultado = cadena_resultado + "\n\tDatos de contacto:\t";
        } else if (qName.equals("Email")) {
            cadena_resultado = cadena_resultado + "\t\tEmail:\t";
        } else if (qName.equals("Telefono")) {
            cadena_resultado = cadena_resultado + "\t\tTelefono:\t";
        } else if (qName.equals("Direccion")) {
            cadena_resultado = cadena_resultado + "\t\tDireccion\t";
        } else if (qName.equals("Titulo")) {
            cadena_resultado = cadena_resultado + "Titulo:\t";
        } else if (qName.equals("Texto")) {
            cadena_resultado = cadena_resultado + "Texto:\t";
        }

    }

    //Cuando se detecta una cadena de texto guarda ese texto en la variable de salida.
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for (int i = start; i < length + start; i++) {
            cadena_resultado = cadena_resultado + ch[i];
        }
        cadena_resultado = cadena_resultado.trim() + "\n";
    }

    //Cuando se detecta el final de un elemento <libro>,
    //se pone una línea discontinua en la salida
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Post")) {
            cadena_resultado += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        }
    }
}
