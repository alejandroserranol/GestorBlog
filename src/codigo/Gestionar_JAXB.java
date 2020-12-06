/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.util.List;
import javablog.Blog;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_JAXB {
    
    Blog miBlog;

    public int preparar_JAXB(File _fichero) {
        
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

    public int annadirLike(String _idPost, File _fichero) {
        
        try {
            List<Blog.Post> listaPosts = miBlog.getPost();
            
            for(int i=0; i<listaPosts.size(); i++){
                
                if(listaPosts.get(i).getId().equals(_idPost)){
                    int numeroLikes = Integer.parseInt(listaPosts.get(i).getLikes());
                    numeroLikes++;
                    listaPosts.get(i).setLikes(String.valueOf(numeroLikes));
                }
            }
            
            JAXBContext contexto = JAXBContext.newInstance(Blog.class);
            
            Marshaller m = contexto.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            m.marshal(miBlog, _fichero);
            
            
            
            return 0;            
        } catch (Exception e) {
            return -1;
        }
        
    }
    

    
}
