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
 * Servlet implementation class ModificarA
 */
public class ModificarA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarA() {
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
		
		
		
		String dni = request.getParameter("mdniA");
		String nombre = request.getParameter("mnombreA");
		String apellido = request.getParameter("mapellidoA");
		int año = Integer.parseInt(request.getParameter("manoA"));
		String ciclo = request.getParameter("mapellidoA");
		
		try{
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to matriculasBD2...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
		
		 sentencia.executeUpdate("UPDATE alumnos set "
					+"nombre = '"+nombre+"'"+","
					+"apellido = '"+apellido+"'"+","
					+"añoInscripcion = "+año+","
					+"ciclo = '"+ciclo+"'"
					+ "where dni = '"+dni+"'"+";");
		
			
			ResultSet Ralumno = sentencia.executeQuery("SELECT * FROM alumnos WHERE dni = '"+dni+"';"); 
			Ralumno.next();
			
			
			
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			out.println("<p>el alumno: "+dni +" ha sido modificado</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}