/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.albarregas.module;

import es.albarregas.bean.Libro;
import java.util.ArrayList;

/**
 *
 * @author elser
 */
public class Utiles {

    public static Libro rellenalibro(String cadena) {
        //String str = "1-NbLib:lolo/Prec:18.00/cant:3/dev ¿";
        String[] parts = cadena.split("/");

        String nbLibPart = parts[0];
        String precPart = parts[1];
        String cantPart = parts[2];

        String nbLib = nbLibPart.split(":")[1];
        String prec = precPart.split(":")[1];
        float precio = Float.parseFloat(prec);
        String cant = cantPart.split(":")[1];
        int cantidad = Integer.parseInt(cant);
        Libro libro = new Libro(nbLib, precio, cantidad);

        return libro;

    }

    public static double ObetenerPrecioPorNombreLibro(String nombre) {
        double precio = 0;
        switch (nombre) {
            case "1984":
                precio = 12.56;
                break;
            case "Dracula":
                precio = 13.56;
                break;
            case "Frankenstein":
                precio = 14.56;
                break;
            case "Ulises":
                precio = 15.56;
                break;
            case "Niebla":
                precio = 16.56;
                break;
            case "Matilda":
                precio = 17.56;
                break;
            case "Rebeldes":
                precio = 18.56;
                break;
            case "Criticon":
                precio = 19.56;
                break;
            default:
                throw new AssertionError();
        }
        return precio;

    }

    public static String pasarListaString(ArrayList<String> lista) {

        StringBuilder stb = new StringBuilder();
        String resultado = "";
        for (String string : lista) {
            stb.append(string);
        }
        resultado = stb.toString();
        return resultado;
    }

    public static ArrayList<Libro> convertirListaStringAListaLibro(ArrayList<String> lista) {

        ArrayList<Libro> libros = new ArrayList<>();

        for (String elem : lista) {
            Libro lib = rellenalibro(elem);
            boolean encontrado = false;

            for (Libro libro : libros) {
                if (lib.getNombre().equals(libro.getNombre())) {
                    libro.setCantidad(libro.getCantidad() + lib.getCantidad());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {

                libros.add(lib);
            }

        }

        return libros;
    }

    public static ArrayList<String> pasarListaLibroListaString(ArrayList<Libro> libros) {
        //String str = "1-NbLib:lolo/Prec:18.00/cant:3/dev ¿";
        ArrayList<String> lista = new ArrayList<>();

        for (Libro lib : libros) {
            lista.add(pasarLibroString(lib));
        }

        return lista;
    }

    //hacer metodo que me pase un objeto libro al formato utilizado String para las listas
    public static String pasarLibroString(Libro libro) {

        return "NbLib:" + libro.getNombre() + "/Prec:" + libro.getPrecio() + "/cant:" + libro.getCantidad() + "/¿";
    }

    public static ArrayList<Libro> comprobarRepetido(ArrayList<Libro> lista, Libro libro2) {
        boolean encontrado = false; // Movemos la variable fuera del bucle

        // Buscamos en la lista de libros si ya tenemos el elemento en la lista.
        // Si es así, sumamos la cantidad de libros; si no, añadimos el nuevo objeto a la lista.
        for (Libro libro : lista) {
            if (libro.getNombre().equals(libro2.getNombre())) {
                libro.setCantidad(libro.getCantidad() + libro2.getCantidad());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            lista.add(libro2);
        }

        return lista;
    }
    
    public static ArrayList<Libro> sumarUnoCantidad(String name, ArrayList<Libro>lista){

        // Buscamos en la lista de libros si tenemos el libro, incrementamos en 1 cantidad.
        for (Libro libro : lista) {
            if (libro.getNombre().equals(name)) {
                libro.setCantidad(libro.getCantidad() + 1);
                break;
            }
        }
        return lista;
    }
    
    public static ArrayList<Libro> restarUnoCantidad(String name, ArrayList<Libro>lista){
        // Buscamos en la lista de libros si tenemos el libro, incrementamos en 1 cantidad.
        for (Libro libro : lista) {
            if (libro.getNombre().equals(name)) {
                libro.setCantidad(libro.getCantidad() - 1);
                break;
            }
            
        }
        return lista;
    }

}
