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
 * Servlet implementation class BuscarP
 */
public class BuscarP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarP() {
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
String dni = request.getParameter("buscarDNIP");
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
		
	
			
			
			ResultSet profesor = sentencia.executeQuery("SELECT * FROM profesor where dni ='"+dni+"';"); 
			profesor.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>profesors</h1>");
			out.println("<p>el profesor con el dni : " + profesor.getString("dni")  + " existe en nuestra base de datos</p>");
			out.println("<p>----------------su informacion es------------------</p>");
			out.println("<p>DNI: "+profesor.getString("dni")+ "</p>");
			out.println("<p>nombre: "+profesor.getString("nombre")+ "</p>");
			out.println("<p>apellido: "+profesor.getString("apellido")+ "</p>");
			out.println("<p>titulacion: "+profesor.getString("titulacion")+ "</p>");
			out.println("<p>departamento: "+profesor.getString("departamento")+ "</p>");
			
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
