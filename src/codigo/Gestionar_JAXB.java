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
 * Gestionar_JAXB --- Clase con los métodos utilizados para acceder al contenido del fichero XML con JAXB.
 * @author Alejandro Serrano Loredo
 */
public class Gestionar_JAXB {

    Blog miBlog;
    /**
     * @see  Prepara el objeto Blog con el método unmarshal() de la clase Unmarshaller.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int preparar_JAXB(File _fichero) {

        try {
            //Crea una instancia JAXB.
            JAXBContext contexto = JAXBContext.newInstance(Blog.class);
            //Crea un objeto Unmarshaller.
            Unmarshaller u = contexto.createUnmarshaller();
            //Deserializa (unmarshal el fichero).
            miBlog = (Blog) u.unmarshal(_fichero);

            return 0;
        } catch (Exception e) {
            return -1;
        }

    }
    /**
     * @see Incrementa en una unidad el número de likes del post elegido en el combobox.
     * @param _idPost: String con el id del post al que dar like.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int annadirLike(String _idPost, File _fichero) {

        try {
            List<Blog.Post> listaPosts = miBlog.getPost();
            int numeroLikes = 0;

            for (int i = 0; i < listaPosts.size(); i++) {

                if (listaPosts.get(i).getId().equals(_idPost)) {
                    numeroLikes = Integer.parseInt(listaPosts.get(i).getLikes());
                    numeroLikes++;
                    listaPosts.get(i).setLikes(String.valueOf(numeroLikes));
                }
            }

            serializar_JAXB(_fichero);

            return 0;
        } catch (Exception e) {
            return -1;
        }

    }
    
    /**
     * @see Modifica el elemento seleccionado con el RadioButton.
     * @param _elementoACambiar: String con el nombre del elemento que se desea cambiar.
     * @param _elementoAntiguo: String con el valor antiguo del elemento a sustituir introducido en el TextField.
     * @param _elementoNuevo: String con el valor nuevo del elemento a sustituir introducido en el TextField.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     * @return int para manejar los errores que se puedan producir.
     */
    public int modificar_JAXB(String _elementoACambiar, String _elementoAntiguo, String _elementoNuevo, File _fichero) {
        
        try {
            List<Blog.Post> listaPosts = miBlog.getPost();

            if(_elementoACambiar.equals("Alias")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getAlias().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().setAlias(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Nombre")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getNombre().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().setNombre(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Apellido")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getApellido().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().setApellido(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Linkedin")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getRedesSociales().getLinkedin().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().getRedesSociales().setLinkedin(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Github")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getRedesSociales().getGithub().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().getRedesSociales().setGithub(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Email")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getDatosContacto().getEmail().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().getDatosContacto().setEmail(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Telefono")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getDatosContacto().getTelefono().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().getDatosContacto().setTelefono(_elementoNuevo);
                    }
                }
            } else if(_elementoACambiar.equals("Direccion")){
                for(int i=0; i<listaPosts.size(); i++){
                    if(listaPosts.get(i).getUsuario().getDatosContacto().getDireccion().equals(_elementoAntiguo)){
                        listaPosts.get(i).getUsuario().getDatosContacto().setDireccion(_elementoNuevo);
                    }
                }
            }


            serializar_JAXB(_fichero);

            return 0;
        } catch (Exception e) {
            return -1;
        }
        
    }
    /**
     * @see Guarda la modificación realizada en memoria en el disco duro.
     * @param _fichero: Fichero abierto en el método "dialogoSeleccionarFichero()".
     */
    private void serializar_JAXB(File _fichero) {

        try {
            //Crea una instancia JAXB.
            JAXBContext contexto = JAXBContext.newInstance(Blog.class);
            //Crea un objeto Marshaller.
            Marshaller m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Serializa (marshal el fichero).
            m.marshal(miBlog, _fichero);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
