/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javablog.Blog;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_JAXB {
    
    Blog miBlog;

    int preparar_JAXB(File _fichero) {
        
        try{
            //Crea una instancia JAXB.
            JAXBContext contexto = JAXBContext.newInstance(Blog.class);
            //Crea un objeto Unmarshaller.
            Unmarshaller u = contexto.createUnmarshaller();
            //Deserializa (unmarshal el fichero).
            miBlog = (Blog) u.unmarshal(_fichero);
            
            return 0;
        } catch(Exception e){
            return -1;
        }
        
    }
    

    
}
