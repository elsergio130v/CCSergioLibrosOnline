package es.albarregas.controller;

import es.albarregas.bean.Libro;
import es.albarregas.module.Utiles;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elser
 */
public class TiendaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
                HttpSession sesion = request.getSession();
                ArrayList<Libro> libros = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
                Cookie[] cookies = request.getCookies();

        switch (request.getParameter("enviar")) {
            
            
            case "add":
                String nbLib = request.getParameter("selectLibros");
                String cant = request.getParameter("cantidad");
                
                try {
                    int numeroEntero = Integer.parseInt(cant);
                } catch (NumberFormatException e) {
                    // En caso de excepción, redirige a la página anterior
                    String rutaTienda = request.getContextPath() + "/JSP/tienda.jsp";
                    response.sendRedirect(rutaTienda);
                    /*TODO
                        hacer que si pasa esto, arrojar un mensaje en la vista tienda con el error.
                    */
                    
                    
                }
                //recogo los datos que me da el cliente, compruebo que el nombre del libro,
                //no está contenido, si está en la lista, añado uno mas a la cantidad,
                //si no, añado un libro nuevo.
                int i = 0;
                boolean encontrado = false;
                //primero comprobamos que la lista no esta vacia, si es asi metemos el libro a la lista, si no.
                //buscamos el libro.
                if (libros == null || libros.size() < 1) {
                    Libro libro = new Libro(nbLib, Utiles.ObetenerPrecioPorNombreLibro(nbLib), Integer.parseInt(cant));
                    libros.add(libro);
                } else {
                    //funcion que recorre arraylist y suma cantidad.                   
                    Libro libro = new Libro(nbLib, Utiles.ObetenerPrecioPorNombreLibro(nbLib), Integer.parseInt(cant));
                    libros = Utiles.comprobarRepetido(libros,libro);
                }
                //cogemos la sesion y le establecemos el valor para la respuesta.
                sesion.setAttribute("carrito", libros);
                String rutaTienda = request.getContextPath() + "/JSP/tienda.jsp";
                response.sendRedirect(rutaTienda);
                
               
                for (Cookie cokie : cookies) {
                    if (cokie.getName().equals("carrito")) {
                        ArrayList<String>lista = new ArrayList<String>();
                        lista = Utiles.pasarListaLibroListaString(libros);
                        cokie.setValue(Utiles.pasarListaString(lista));
                        response.addCookie(cokie);
                    }
                }
                //actualizamos el valor de la cookie
                //para ello necesitamos pasar esta lista a un String, mi idea,
                //es con un stringbuilder mientras recorremos la lista ir añadiendo 
                //las cadenas que tiene la lista hasta acabar y pasar el StringBuilder
                //a un string.
                //iba la cookie

                break;
            case "ver":
                //metodo que recorre la lista y me pasa una lista de libros, oredenada y sin libros repetidos.
                //despues pasamos esa lista de libros a una lista de string.
                sesion.setAttribute("carrito", libros);
                String ruta = request.getContextPath() + "/JSP/verCarrito.jsp";
                response.sendRedirect(ruta);
                break;
            case "fin":
                String rutafin = request.getContextPath() + "/JSP/finCarrito.jsp";
                response.sendRedirect(rutafin);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
