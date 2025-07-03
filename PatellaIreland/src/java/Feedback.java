/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author student
 */
@WebServlet(urlPatterns = {"/Feedback"})
public class Feedback extends HttpServlet {

    int user_id;
    int star;
    String reason;

    Connection conn;
    PreparedStatement prepStat;
    Statement stat;

    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "assign";
        String userName = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
            stat = (Statement) conn.createStatement();
            System.out.println("Connected");
            stat.execute("CREATE TABLE IF NOT EXISTS feedback "
                    + "(user_id INT(8), star INT, reason TEXT, FOREIGN KEY(user_id) REFERENCES user(user_id) )");
            System.out.println("After table create");
        } catch (Exception e) {
            System.err.println(e);
        }
    } // end of init() method

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        user_id = Integer.parseInt(request.getParameter("user_id"));
        star = Integer.parseInt(request.getParameter("star"));
        reason = request.getParameter("reason");

        try {
            String query = "INSERT INTO feedback VALUES (?,?,?)";
            prepStat = (PreparedStatement) conn.prepareStatement(query);
            prepStat.setInt(1, user_id);
            prepStat.setInt(2, star);
            prepStat.setString(3, reason);

            prepStat.executeUpdate();
            System.out.println("After insert");

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet DBData</title>");
                out.println("<style> body{background-color: rgb(2, 89, 76); font-family: Verdana, Geneva, Tahoma, sans-serif; text-align: center;}");
                out.println("h1{color:white;}");

                out.println("""
                            .databut {
                            padding: 10px 20px;
                            background-color: #4CAF50;
                            color: white;
                            border: none;
                            border-radius: 25px;
                            font-size: 16px;
                            cursor: pointer;
                            transition: background-color 0.3s ease;
                            }              """);

                out.println("""
                             .databut:hover {
                                background-color: #45a049;
                            }""");

                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<br><br><br><h1>Data inserted successfully</h1>");
                out.println("<br> <br> <br><a href =\"index.html\"><button class=\"databut\">Home</button></a>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            System.err.println(e);

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet DBData</title>");
                out.println("<style> body{background-color: rgb(2, 89, 76); font-family: Verdana, Geneva, Tahoma, sans-serif; text-align: center;}");
                out.println("h1{color:white;}");

                out.println("""
                            .databut {
                            padding: 10px 20px;
                            background-color: #4CAF50;
                            color: white;
                            border: none;
                            border-radius: 25px;
                            font-size: 16px;
                            cursor: pointer;
                            transition: background-color 0.3s ease;
                            }              """);

                out.println("""
                             .databut:hover {
                                background-color: #45a049;
                            }""");

                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<br><br><br><h1>Error on insert</h1>");
                out.println("<br> <br> <br><a href =\"WriteData.html\"><button class=\"databut\">Return</button></a>");
                out.println("</body>");
                out.println("</html>");
            }
        }

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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
