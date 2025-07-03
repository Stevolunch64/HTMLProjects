
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = {"/readAll"})
public class readAll extends HttpServlet {

    Connection conn;
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

        } catch (Exception e) {
            System.err.println(e);
        }
    } // end of init() method

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Statement stmt;
        // Set response content type 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "View data here";
        String docType = "<!doctype html>";
        out.println(docType + "<html>\n"
                + "<head><style>"
                + "html{font-family: Verdana, Geneva, Tahoma, sans-serif; text-align: center;}"
                + "h1 {color: white;}"
                + "body {background-color: rgb(2, 89, 76);}"
                + "#allData {"
                + "  background-color: rgb(242, 232, 211);"
                + "  border: 10px solid black;"
                + "  border-radius: 10px;"
                + "  padding: 14px 16px;"
                + "  margin-top: 10px;"
                + "  width:50%;"
                + "  margin: 0 auto; "
                + "}"
                
                + ".databut {"
                + " padding: 10px 20px;"
                +  "background-color: #4CAF50;"
                + " color: white;"
                + " border: none;"
                + " border-radius: 25px;"
                + " font-size: 16px;"
                + "cursor: pointer;"
                + " transition: background-color 0.3s ease;"
                + "text-decoration:none;"
                + " }"

                + " .databut:hover {"
                + " background-color: #45a049;"
                + " }"
                
                + "</style><title>" + title + "</title></head>\n"
                + "<body>\n"
                + "<h1 align=\"center\">" + title + "</h1><div id=\"allData\">\n"
        );

        try {
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT * FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set 
            while (rs.next()) {
                //Retrieve by column name

                int first = rs.getInt("user_id");
                String second = rs.getString("user_name");
                String last = rs.getString("email");

                //Display values
                out.println("<strong>User ID  :</strong> " + first);
                out.println("<strong>Username :</strong> " + second);
                out.println("<strong>Email    :</strong> " + last + "<br>");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } //end try

        try {
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT * FROM report";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set 
            while (rs.next()) {
                //Retrieve by column name

                int first = rs.getInt("report_id");
                String second = rs.getString("subject");
                int last = rs.getInt("page");

                //Display values
                out.println("<br><strong>Report ID :</strong> " + first);
                out.println("<strong>Subject       :</strong> " + second);
                out.println("<strong>Pages         :</strong> " + last);

            }
            out.println("<br>");

        } catch (Exception e) {
            e.printStackTrace();
        } //end try

        try {
            // Execute SQL query
            stmt = (Statement) conn.createStatement();
            String sql;
            sql = "SELECT * FROM feedback";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set 
            while (rs.next()) {
                //Retrieve by column name

                int first = rs.getInt("user_id");
                int second = rs.getInt("star");
                String last = rs.getString("reason");

                //Display values
                out.println("<br><strong>User ID         :</strong> " + first);
                out.println("<strong>Star :</strong> " + second);
                out.println("<strong>Reason         :</strong> " + last);

            }
            out.println("<br> <br> <br> <a href=\"index.html\" class='databut'>Home</a>");
            out.println("</div></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        } //end try
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
