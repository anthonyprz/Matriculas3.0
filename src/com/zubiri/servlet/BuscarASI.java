package com.zubiri.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Servlet implementation class BuscarASI
 */
public class BuscarASI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarASI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String nombre = request.getParameter("nombreASIG");
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
		
	
			
			
			ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura where nombre ='"+nombre+"';"); 
			asignatura.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>asignatura</h1>");
			out.println("<p>asignatura con el nmbre : " + asignatura.getString("nombre")  + " existe en nuestra base de datos</p>");
			out.println("<p>----------------su informacion es------------------</p>");
			out.println("<p>DNI: "+asignatura.getString("nombre")+ "</p>");
			out.println("<p>nombre: "+asignatura.getString("creditos")+ "</p>");
			out.println("<p>apellido: "+asignatura.getString("dniP")+ "</p>");
			out.println("<p>titulacion: "+asignatura.getString("añoMatriculacion")+ "</p>");
			out.println("<p>departamento: "+asignatura.getString("precio")+ "</p>");
			
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
