package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserList")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        String option = request.getParameter("option");

            if ( name != null && email != null && password != null){
                UserDAO dao = new UserDAO();

                User user = new User(name, email, password);
                try{
                    dao.create(user);

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        List<User> users = new ArrayList<User>();
            try {
                users = creataeList();
                request.setAttribute("users",users);
                request.getRequestDispatcher("listForm.jsp").forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    private List<User> creataeList () throws SQLException{
        UserDAO dao = new UserDAO();
        List<User> users = dao.read();
        return users;
    }
 }