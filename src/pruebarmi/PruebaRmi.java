package pruebarmi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import tutifruti.RmiServerIntf;

import java.util.List;

/**
 *
 * @author EmilianoLarrea
 */
public class PruebaRmi {

    public static boolean GUI = false;
    public static boolean LOCAL = false;
    public static String PUERTO = "1099";
    public static String IPREMOTO = "//jmonetti.ddns.net:" + PUERTO + "/RmiServer";
    private static Registry registry;
    static RmiServerIntf obj = null;
    public static String Usuario = "baudonn";
    public static String Pass = "arbol45";
    public static String categoria = "accion";

    private static void writeArray(List<List<String>> diccionario) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("./" + categoria + ".dat"))) {

            out.writeObject(diccionario);
        }
    }

    private static List<List<String>> readArray(String d) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("./diccionarios/" + d + ".dat"))) {

            return (List<List<String>>) in.readObject();
        }
    }

    private static void mostrarDiccionario(List<List<String>> diccionario) {
        for (int i = 0; i < 27; i++) {
            int n = diccionario.get(i).size();
            for (int j = 0; j < n; j++) {
                System.out.println(diccionario.get(i).get(j));
            }
        }
    }

    private static void obtenerPalabra(String catego, List<List<String>> diccionario, int posicion) throws RemoteException {

        int tamano = diccionario.get(posicion).size();
        int numero = (int) (Math.random() * tamano);
        String palabra = diccionario.get(posicion).get(numero);

        System.out.println("Categoria: " + catego + ", Palabra: " + palabra);
        try {
            obj.setPalabra(palabra, Usuario, Pass);
            System.out.println("PALABRA DE " + catego + " SETEADA");
            System.out.println("-------------------------------------------------");
        } catch (Exception e) {

            System.out.println("Hubo un error al enviar palabra");
        }

    }
    private static void jugar (List<List<String>> diccionarioVegetal, List<List<String>> diccionarioPais, List<List<String>> diccionarioNombreMasculino, List<List<String>> diccionarioNombreFemenino,  List<List<String>> diccionarioFrutaVerdura, List<List<String>> diccionarioDeporte,  List<List<String>> diccionarioCosa, List<List<String>> diccionarioCiudad, List<List<String>> diccionarioCantante, List<List<String>> diccionarioAccion) throws RemoteException{    
             
            String letra = obj.getLetra(Usuario, Pass);
        
            String categorias = obj.getCategorias(Usuario, Pass);
        
            String cats[] = categorias.split("-");
         
            int posicion= 0;
                switch (letra) {
                     case "a":  posicion = 0;
                     break;
                     case "b":  posicion = 1;
                     break;
                     case "c":  posicion = 2;
                     break;
                     case "d":  posicion = 3;
                     break;
                     case "e":  posicion = 4;
                     break;
                     case "f":  posicion = 5;
                     break;
                     case "g":  posicion = 6;
                     break;
                     case "h":  posicion = 7;
                     break;
                     case "i":  posicion = 8;
                     break;
                     case "j":  posicion = 9;
                     break;
                     case "k":  posicion = 10;
                     break;
                     case "l":  posicion = 11;
                     break;
                     case "m":  posicion = 12;
                     break;
                     case "n":  posicion = 13;
                     break;
                     case "ñ":  posicion = 14;
                     break;
                     case "o":  posicion = 15;
                     break;
                     case "p":  posicion = 16;
                     break;
                     case "q":  posicion = 17;
                     break;
                     case "r":  posicion = 18;
                     break;
                     case "s":  posicion = 19;
                     break;
                     case "t":  posicion = 20;
                     break;
                     case "u":  posicion = 21;
                     break;
                     case "v":  posicion = 22;
                     break;
                     case "w":  posicion = 23;
                     break;
                     case "x":  posicion = 24;
                     break;
                     case "y":  posicion = 25;
                     break;
                     case "z":  posicion = 26;
                     break;}
                
                for (int i = 0; i < 10; i++) {
                    String catego = cats[i];
                  
                 switch (catego) {
                     case "pais":  obtenerPalabra(catego, diccionarioPais, posicion);
                     break;
                     case "vegetal":  obtenerPalabra(catego, diccionarioVegetal, posicion);
                     break;
                     case "nombremasculino":  obtenerPalabra(catego, diccionarioNombreMasculino, posicion);
                     break;
                     case "nombrefemenino":  obtenerPalabra(catego, diccionarioNombreFemenino, posicion);
                     break;
                     case "frutaverdura": obtenerPalabra(catego, diccionarioFrutaVerdura, posicion);
                     break;
                     case "deporte":  obtenerPalabra(catego, diccionarioDeporte, posicion);
                     break;
                     case "cosa":  obtenerPalabra(catego, diccionarioCosa, posicion);
                     break;
                     case "ciudad":  obtenerPalabra(catego, diccionarioCiudad, posicion);
                     break;
                     case "cantante":  obtenerPalabra(catego, diccionarioCantante, posicion);
                     break;
                     case "accion":  obtenerPalabra(catego, diccionarioAccion, posicion);
                     break;
                    }
            
                }
            String puntos = obj.getMisPuntos(Usuario, Pass);
            String ganador = obj.getGanador(Usuario, Pass);
            
            System.out.println("Puntos de " + Usuario + " : " + puntos);
            System.out.println("Ganador de esta partida: " + ganador);
            System.out.println("-------------------------------------------------");
            }
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, IOException, ClassNotFoundException {
        
        try {
            if (System.getSecurityManager() == null) {
                System.setProperty("java.security.policy", "java.policy");
                System.setSecurityManager(new RMISecurityManager());

            }
            obj = (RmiServerIntf) Naming.lookup(IPREMOTO);
            obj.comenzarComunicacion(Usuario, Pass);
            System.out.println("Conectado al server con usuario: " + Usuario);
            System.out.println("-------------------------------------------------");

            /*
            CODIGO PARA CREAR DICCIONARIOS:
            String categorias = obj.getCategorias(Usuario, Pass);
            System.out.println("Creando diccionario de categoria: " + categoria);

            String cantPalabra = obj.getCantidadPalabra(Usuario, Pass, categoria);
            System.out.println("Cantidad de Palabras: " + cantPalabra);
            int cantidadPalabra = Integer.parseInt(cantPalabra);

            List<List<String>> diccionario = new ArrayList<List<String>>();
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());
            diccionario.add(new ArrayList<String>());

            obj.setLugarPalabra(Usuario, Pass, categoria, 0);

            for (int i = 0; i < cantidadPalabra; i++) {

                String palabra = obj.getPalabra(Usuario, Pass, categoria);
                char caracter = palabra.charAt(0);
                int posicion = 0;
                switch (caracter) {
                    case 'a':
                        posicion = 0;
                        break;
                    case 'b':
                        posicion = 1;
                        break;
                    case 'c':
                        posicion = 2;
                        break;
                    case 'd':
                        posicion = 3;
                        break;
                    case 'e':
                        posicion = 4;
                        break;
                    case 'f':
                        posicion = 5;
                        break;
                    case 'g':
                        posicion = 6;
                        break;
                    case 'h':
                        posicion = 7;
                        break;
                    case 'i':
                        posicion = 8;
                        break;
                    case 'j':
                        posicion = 9;
                        break;
                    case 'k':
                        posicion = 10;
                        break;
                    case 'l':
                        posicion = 11;
                        break;
                    case 'm':
                        posicion = 12;
                        break;
                    case 'n':
                        posicion = 13;
                        break;
                    case 'ñ':
                        posicion = 14;
                        break;
                    case 'o':
                        posicion = 15;
                        break;
                    case 'p':
                        posicion = 16;
                        break;
                    case 'q':
                        posicion = 17;
                        break;
                    case 'r':
                        posicion = 18;
                        break;
                    case 's':
                        posicion = 19;
                        break;
                    case 't':
                        posicion = 20;
                        break;
                    case 'u':
                        posicion = 21;
                        break;
                    case 'v':
                        posicion = 22;
                        break;
                    case 'w':
                        posicion = 23;
                        break;
                    case 'x':
                        posicion = 24;
                        break;
                    case 'y':
                        posicion = 25;
                        break;
                    case 'z':
                        posicion = 26;
                        break;
                }

                System.out.println("Indice: " + i + " palabra: " + palabra);
                diccionario.get(posicion).add(palabra);

            }
            System.out.println("-------------------------------------------------");
            System.out.println("Termino de crear el diccionario");
            System.out.println("-------------------------------------------------");

            writeArray(diccionario);
            */
            
        
            
            List<List<String>> diccionarioVegetal = readArray("vegetal");
            List<List<String>> diccionarioPais = readArray("pais");
            List<List<String>> diccionarioNombreMasculino = readArray("nombremasculino");
            List<List<String>> diccionarioNombreFemenino = readArray("nombrefemenino");
            List<List<String>> diccionarioFrutaVerdura = readArray("frutaverdura");
            List<List<String>> diccionarioDeporte = readArray("deporte");
            List<List<String>> diccionarioCosa = readArray("cosa");
            List<List<String>> diccionarioCiudad = readArray("ciudad");
            List<List<String>> diccionarioCantante = readArray("cantante");
            List<List<String>> diccionarioAccion = readArray("accion");
            
            jugar(diccionarioVegetal, diccionarioPais, diccionarioNombreMasculino, 
                    diccionarioNombreFemenino, diccionarioFrutaVerdura, diccionarioDeporte, 
                    diccionarioCosa, diccionarioCiudad, diccionarioCantante, diccionarioAccion);
         
            for (int i = 0; i < 1; i++){
             
            
            String letra = obj.getLetra(Usuario, Pass);
            System.out.println(letra);
            
            Thread.sleep(1680000);
                         
             String letraNueva = obj.getLetra(Usuario, Pass);  
             System.out.println(letraNueva);
            
             do{
              
                letraNueva = obj.getLetra(Usuario, Pass);
              
            } while(letraNueva.equals(letra));
         
            jugar(diccionarioVegetal, diccionarioPais, diccionarioNombreMasculino, 
                    diccionarioNombreFemenino, diccionarioFrutaVerdura, diccionarioDeporte, 
                    diccionarioCosa, diccionarioCiudad, diccionarioCantante, diccionarioAccion);
         }
        
        
                
                

        } catch (Exception e) {

            System.out.println("hubo un error");
        }
    }
}

