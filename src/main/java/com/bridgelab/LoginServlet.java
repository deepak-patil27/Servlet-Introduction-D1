package com.bridgelab;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet testing",
        urlPatterns = { "/LoginServlet" },
        initParams = { @WebInitParam(name="user",value="Deepak"),
                @WebInitParam(name="password",value="Patil")
        }
)

public class LoginServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
                String user = req.getParameter("user");
                String pwd = req.getParameter("pwd");

                String regexName = "^[A-Z]{1}[a-zA-Z]{2,}$";
                String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$)";

                String userID = getServletConfig().getInitParameter("user");
                String password = getServletConfig().getInitParameter("password");

                if (userID.equals(user) && password.equals(pwd)){
                        req.setAttribute("user",user);
                        req.getRequestDispatcher("LoginSuccess.jsp").forward(req,response);
                } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                        PrintWriter out = response.getWriter();
                        out.println("<font color=red>Either user name or password is wrong.</font>");
                        rd.include(req,response);
                }
        }
}
