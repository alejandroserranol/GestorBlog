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
 *
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_SAX {

    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML;

    int preparar_SAX(File _fichero) {

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

    String recorrerSAX() {
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

class ManejadorSAX extends DefaultHandler {

    String cadena_resultado = "";

    //Este método se ejecuta cuando se encuentra un elemento de apertura
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        if (qName.equals("Blogs")) {
            cadena_resultado = cadena_resultado + "Se van a mostrar los posts de este blog\n************************************************\n";
        } else if (qName.equals("Post")) {
            cadena_resultado = cadena_resultado + "\nId: "
                    + attributes.getValue(attributes.getQName(0));
            cadena_resultado = cadena_resultado + "\tEn fecha: "
                    + attributes.getValue(attributes.getQName(1));
            cadena_resultado = cadena_resultado + "\tTema : "
                    + attributes.getValue(attributes.getQName(2));
            cadena_resultado = cadena_resultado + "\tNúmero de likes: "
                    + attributes.getValue(attributes.getQName(3));
        } else if (qName.equals("Usuario")) {
            cadena_resultado = cadena_resultado + "\nUsuario: "
                    + attributes.getValue(attributes.getQName(0));
            cadena_resultado = cadena_resultado + "\tNumero_mensajes: "
                    + attributes.getValue(attributes.getQName(1));
            cadena_resultado = cadena_resultado + "\n\nDatos personales:";
        } else if (qName.equals("Nombre")) {
            cadena_resultado = cadena_resultado + "\n\tNombre:\t";
        } else if (qName.equals("Apellido")) {
            cadena_resultado = cadena_resultado + "\n\tApellido:\t";
        } else if (qName.equals("RedesSociales")) {
            cadena_resultado = cadena_resultado + "\n\tRedes sociales:\t";
        } else if (qName.equals("Linkedin")) {
            cadena_resultado = cadena_resultado + "\n\t\tLinkedin:\t";
        } else if (qName.equals("Github")) {
            cadena_resultado = cadena_resultado + "\n\t\tGithub:\t";
        } else if (qName.equals("DatosContacto")) {
            cadena_resultado = cadena_resultado + "\n\tDatos de contacto:\t";
        } else if (qName.equals("Email")) {
            cadena_resultado = cadena_resultado + "\n\t\tEmail:\t";
        } else if (qName.equals("Telefono")) {
            cadena_resultado = cadena_resultado + "\n\t\tTelefono:\t";
        } else if (qName.equals("Direccion")) {
            cadena_resultado = cadena_resultado + "\n\t\tDireccion\t";
        } else if (qName.equals("Titulo")) {
            cadena_resultado = cadena_resultado + "\nTitulo:\t";
        } else if (qName.equals("Texto")) {
            cadena_resultado = cadena_resultado + "\nTexto:\t";
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
