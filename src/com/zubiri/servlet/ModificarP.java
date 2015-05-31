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
 * Servlet implementation class ModificarP
 */
public class ModificarP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarP() {
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
		String dni = request.getParameter("mdniP");
		String nombre = request.getParameter("mnombreP");
		String apellido = request.getParameter("mapellidoP");
		String titulacion = request.getParameter("mtitulacionP");
		String departamento = request.getParameter("mdepartamentoP");
		
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
			sentencia.executeUpdate("update profesor set "
					+ "nombre = '"+nombre+"'"+","
					+"apellido= '"+apellido+"'"+","
					+"titulacion= '"+titulacion+"'"+","
					+"departamento = '"+departamento+"'"
					+ "where dni = '"+dni+"'"+";");
					
					
			ResultSet profesor = sentencia.executeQuery("SELECT * FROM profesor WHERE dni = '"+request.getParameter("dniA")+"';"); 
			profesor.next();
			out.println("<html>");
			out.println("<head><title>Respuesta</title>");
			out.println("<body>");
			out.println("<h1>profesor</h1>");
			out.println("<p>el profesor  : " + nombre +' '+apellido + " ha sido modificado</p>");
			out.println("</body></html>");

				conexion.close();		
		}
		catch(Exception e){
			System.out.println("aqui hay un problema " + e);
		}
	}

}
