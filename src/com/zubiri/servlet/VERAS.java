package com.zubiri.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class VERAS
 */
public class VERAS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VERAS() {
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
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			
			ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura;"); 
			
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>asignaturas</h1>");
			while (asignatura.next()){
				out.println("<p>--------------asignatura---------------</p>");
				out.println("<p>nombre: "+asignatura.getString("nombre")+ "</p>");
				out.println("<p>creditos: "+asignatura.getString("creditos")+ "</p>");
				out.println("<p>dni del profesor: "+asignatura.getString("dniP")+ "</p>");
				out.println("<p>año de matriculacion: "+asignatura.getInt("añoMatriculacion")+ "</p>");
				out.println("<p>precio: "+asignatura.getString("precio")+ "</p>");
				
				/*ResultSet profesor = sentencia.executeQuery("SELECT nombre, apellido FROM profesor where dni= '"+asignatura.getString("dniP")+"';");
				
				while (profesor.next()){
					out.println("<p>el profesor de la asignatura es: "+profesor.getString("nombre")+" " +profesor.getString("apellido")+ "</p>");
				}*/
			}	
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
