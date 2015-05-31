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
 * Servlet implementation class ADDALDATABASE
 */
public class ADDALDATABASE extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADDALDATABASE() {
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
	Alumno alumnojar = new Alumno();
		
		alumnojar.setDni(request.getParameter("dniA"));
		alumnojar.setNombre(request.getParameter("nombreA"));
		alumnojar.setApellido(request.getParameter("apellidoA"));
		alumnojar.setAñoInscripcion(Integer.parseInt(request.getParameter("anoINSA")));
		alumnojar.setCiclo(request.getParameter("cicloA"));
		
		
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			sentencia.executeUpdate("create database if not EXISTS matriculasBD4;");
			
			sentencia.executeUpdate("use matriculasBD4;");
			
			
			sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS alumno"
					+ "(dni varchar (15) primary key,"
					+"nombre varchar(15),"
					+"apellido varchar(15),"
					+"añoInscripcion int(4),"
					+"ciclo varchar (15));");
			
			
			sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo)"
						+ "VALUES ('"+alumnojar.getDni()+"','"+alumnojar.getNombre()+"','"+alumnojar.getApellido()+"','"+alumnojar.getAñoInscripcion()+"','"+alumnojar.getCiclo()+"')");
					
			
			//sentencia.executeUpdate("INSERT INTO alumno (dni, nombre, apellido, añoInscripcion, ciclo)"
				//		+ "VALUES ('"+Persona.getDni()+"','"+Persona.getNombre()+"','"+Persona.getApellido()+"','"+Alumno.getAñoInscripcion()+"','"+Alumno.getCiclo()+"')");
					
			ResultSet alumno = sentencia.executeQuery("SELECT * FROM alumno WHERE dni = '"+request.getParameter("dniA")+"';"); 
			alumno.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>alumnos</h1>");
			out.println("<p>el alumno con el: " + alumnojar.formattedAlumno()
					+ " ha sido añadido a la base de datos</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
