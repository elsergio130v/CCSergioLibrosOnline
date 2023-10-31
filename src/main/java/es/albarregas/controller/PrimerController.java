/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
public class PrimerController extends HttpServlet {

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
        boolean existeCookie = false;
        Cookie[] cookies = request.getCookies();
        String valorCookie;
        if (cookies != null) {
            ArrayList lista = new ArrayList();
            for (Cookie cookie : cookies) {
                if ("carrito".equals(cookie.getName())) {
                    existeCookie = true;
                    valorCookie = cookie.getValue();
                    HttpSession sesion = request.getSession(true);
                    sesion.setAttribute("carrito", lista);
                    if (valorCookie != null && !valorCookie.isEmpty()) {
                        String[] partes = valorCookie.split("¿");
                        for (String parte : partes) {
                            lista.add(parte);
                            //Libro libro = Utiles.rellenalibro(parte);
                        }
                    }

                }
            }
            //HttpSession sesion = request.getSession(true);
            //sesion.setAttribute("carrito", lista);
        }

            String ruta = request.getContextPath() + "/JSP/tienda.jsp";
            response.sendRedirect(ruta);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

//si la cookie existe creamos la session, si no la cookie y la sesion se crea luego.
// la cookie se genera cuando añadimos valores al carro.