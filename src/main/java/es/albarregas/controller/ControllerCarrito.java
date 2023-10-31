/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package es.albarregas.controller;

import es.albarregas.bean.Libro;
import es.albarregas.module.Utiles;
import java.io.IOException;
import static java.lang.System.out;
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
public class ControllerCarrito extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out.println("no es por aqui");
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            

            HttpSession sesion = request.getSession(); 
            ArrayList<Libro> libros = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
            Cookie[] cookies = request.getCookies();
            
            if (request.getParameter("enviar+")!=null) {
                //comprobamos si el cliente añade libroos en la vista carrito
                String boton = request.getParameter("enviar+");
                switch (boton) {
                    case "1984":
                        libros = Utiles.sumarUnoCantidad("1984",libros);
                        break;
                    case "Dracula":
                        libros = Utiles.sumarUnoCantidad("Dracula",libros);
                        break;
                    case "Frankenstein":
                        libros = Utiles.sumarUnoCantidad("Frankenstein",libros);
                        break;
                    case "Ulises":
                        libros = Utiles.sumarUnoCantidad("Ulises",libros);
                        break;
                    case "Niebla":
                        libros = Utiles.sumarUnoCantidad("Niebla",libros);
                        break;
                    case "Rebeldes":
                        libros = Utiles.sumarUnoCantidad("Rebeldes",libros);
                        break;
                    case "Criticon":
                        libros = Utiles.sumarUnoCantidad("Criticon",libros);
                        break;
                    case "Matilda":
                        libros = Utiles.sumarUnoCantidad("Matilda",libros);
                        break;
                    default:
                        throw new AssertionError();
                }
            }else if(request.getParameter("enviar-")!=null){
                //comprobamos si el cliente disminuye libros en la vista carrito
                 String boton = request.getParameter("enviar-");
                switch (boton) {
                    case "1984":
                        libros = Utiles.restarUnoCantidad("1984",libros);
                        break;
                    case "Dracula":
                        libros = Utiles.restarUnoCantidad("Dracula",libros);
                        break;
                    case "Frankenstein":
                        libros = Utiles.restarUnoCantidad("Frankenstein",libros);
                        break;
                    case "Ulises":
                        libros = Utiles.restarUnoCantidad("Ulises",libros);
                        break;
                    case "Niebla":
                        libros = Utiles.restarUnoCantidad("Niebla",libros);
                        break;
                    case "Rebeldes":
                        libros = Utiles.restarUnoCantidad("Rebeldes",libros);
                        break;
                    case "Criticon":
                        libros = Utiles.restarUnoCantidad("Criticon",libros);
                        break;
                    case "Matilda":
                        libros = Utiles.restarUnoCantidad("Matilda",libros);
                        break;
                    default:
                        throw new AssertionError();
                }
                
            
            }
            
            sesion.setAttribute("carrito", libros);
                for (Cookie cokie : cookies) {
                    if (cokie.getName().equals("carrito")) {
                        ArrayList<String>lista = new ArrayList<String>();
                        lista = Utiles.pasarListaLibroListaString(libros);
                        cokie.setValue(Utiles.pasarListaString(lista));
                        response.addCookie(cokie);
                    }
                }
            String rutaTienda = request.getContextPath() + "/JSP/verCarrito.jsp";
            response.sendRedirect(rutaTienda);
            
            
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
