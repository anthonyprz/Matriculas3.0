package com.zubiri.servlet;

import java.io.IOException;

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
 * Servlet implementation class ADDPR
 */
public class ADDPR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADDPR() {
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
		Profesor profesorjar = new Profesor(null, null);
		profesorjar.setDni(request.getParameter("dniP"));
		profesorjar.setNombre(request.getParameter("nombreP"));
		profesorjar.setApellido(request.getParameter("apellidoP"));
		profesorjar.setTitulacion(request.getParameter("titulacion"));
		profesorjar.setDepartamento(request.getParameter("departamento"));
	
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			
			sentencia.executeUpdate("INSERT INTO profesor (dni, nombre, apellido, titulacion, departamento)"
						+ "VALUES ('"+profesorjar.getDni()+"','"+profesorjar.getNombre()+"','"+profesorjar.getApellido()+"','"+profesorjar.getTitulacion()+"','"+profesorjar.getDepartamento()+"')");
					
			
			//sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo)"
				//		+ "VALUES ('"+Persona.getDni()+"','"+Persona.getNombre()+"','"+Persona.getApellido()+"','"+Alumno.getAñoInscripcion()+"','"+Alumno.getCiclo()+"')");
					
			ResultSet alumno = sentencia.executeQuery("SELECT * FROM alumnos WHERE dni = '"+request.getParameter("dniA")+"';"); 
			alumno.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			out.println("<p>el profesor con el: " + profesorjar.formattedProfesor()
					+ " ha sido añadido a la base de datos</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	
	
	}

}
