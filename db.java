package com.ajai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
//import javax.servlet.GenericServlet;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;

public class db extends GenericServlet{

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            res.setContentType("text/html");
            PrintWriter out=res.getWriter();

            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            
//            String val= req.getParameter("r1");
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","");
//                out.print(val);
                String query="SELECT * FROM test";
                stm=con.createStatement();
                rs=stm.executeQuery(query);
                if(rs.next()) {
                    out.print(rs.getString("value"));
                }else {
                    out.println("invalid");
                }
            }catch(ClassNotFoundException e){
                out.println(e);
            }
            catch (SQLException e) {
                System.out.println(e);
            }finally {
                try {    
                    rs.close();
                    stm.close();
                    con.close();
                }catch (Exception e) {
                    System.out.println(e);
                }
            }    
    }    
}