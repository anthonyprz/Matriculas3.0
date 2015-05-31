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
 * Servlet implementation class ModificarASI
 */
public class ModificarASI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarASI() {
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
		String nombre = request.getParameter("nombreAS");
		int creditos = Integer.parseInt(request.getParameter("creditos"));
		String dnipr = request.getParameter("dniP");
		int anomatriculacion = Integer.parseInt(request.getParameter("anoMA"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		try{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Connecting to database...");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/matriculasBD2", "root", "zubiri");
			System.out.println("Connecting to parking...");
			Statement sentencia = conexion.createStatement();
			System.out.println("sentencia creada");
			
			//update alumno set nombre = "x" where dni = "a";
			sentencia.executeUpdate("update asignatura set "
					+ "creditos = '"+creditos+"'"+","
					+"dniP= '"+dnipr+"'"+","
					+"añoMatriculacion= '"+anomatriculacion+"'"+","
					+"precio = '"+precio+"'"
					+ "where nombre = '"+nombre+"'"+";");
					
					
			ResultSet asignatura = sentencia.executeQuery("SELECT * FROM asignatura WHERE nombre = '"+request.getParameter("nombreAS")+"';"); 
			asignatura.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>asignatura</h1>");
			out.println("<p>ela asignatura  : " + nombre +" ha sido modificado</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
