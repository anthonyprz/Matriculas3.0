package com.zubiri.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zubiri.matriculas.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class ADDAS
 */
public class ADDAS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADDAS() {
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
		String nombre = request.getParameter("nombre");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		String dnipr = request.getParameter("dni");
		int anomatriculacion = Integer.parseInt(request.getParameter("anomatriculacion"));
		double precio = Double.parseDouble(request.getParameter("anomatriculacion"));
	
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			
			sentencia.executeUpdate("INSERT INTO asignatura (nombre, creditos, dniP, aÑoMatriculacion, precio)"
						+ "VALUES ('"+nombre+"','"+creditos+"','"+dnipr+"','"+anomatriculacion+"','"+precio+"')");
					
			
			ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura WHERE nombre = '"+request.getParameter("nombre")+"';"); 
			asignatura.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			//out.println("<p>el alumno con el dni : " + alumno.getString("dni")  + " ha sido añadido a la base de datos</p>");
			out.println("<p>la asignatura con el nombre : " + asignatura.getString("nombre")
					+ " ha sido añadido a la base de datos</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	
	}

}
