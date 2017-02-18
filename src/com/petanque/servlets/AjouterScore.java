package com.petanque.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjouterScore
 */
@WebServlet("/AjouterScore")
public class AjouterScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_eq1 = Integer.parseInt(request.getParameter("id_eq1"));
		int id_eq2 = Integer.parseInt(request.getParameter("id_eq2"));
		int score_eq1 = Integer.parseInt(request.getParameter("score_eq1"));
		int score_eq2 = Integer.parseInt(request.getParameter("score_eq2"));
		int winner;
		if (score_eq1 == 13) {
			winner = id_eq1;
		} else {
			winner = id_eq2;
		}
		
		try {
			//messages_2.add("Chargement du driver ...");
			Class.forName("com.mysql.jdbc.Driver");
			//messages_2.add("Driver charge !");
		} catch (ClassNotFoundException e) {
			//messages_2.add("Erreur lors du chargement du driver : " + e.getMessage());
		}
		
		String url_bdd="jdbc:mysql://localhost:9090/bdd_thomas";
		String utilisateur="admin";
		String mdp="database";
		Connection co = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//Connexion à la BDD
			//messages.add("Connexion a la BDD");
			co = DriverManager.getConnection(url_bdd,utilisateur,mdp);
			//messages.add("Connexion etablie !");
			
			//Gestion des requetes
			st = co.createStatement();
			@SuppressWarnings("static-access")
			PreparedStatement ps = co.prepareStatement("INSERT INTO bdd_thomas.Tournoi (eq1,eq2,scEq1M1,scEq2M1,winner) VALUES(?,?,?,?,?);");
			ps.setInt(1, id_eq1);
			ps.setInt(2, id_eq2);
			ps.setInt(3, score_eq1);
			ps.setInt(4, score_eq2);
			ps.setInt(5, winner);
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			//messages.add("Erreur dans la connexion ! <br />");			
		} finally {
			//messages.add( "Fermeture de l'objet ResultSet." );
	        if ( rs != null ) {
	            try {
	                rs.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        //messages.add( "Fermeture de l'objet Statement." );
	        if ( st != null ) {
	            try {
	                st.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        //messages.add( "Fermeture de l'objet Connection." );
	        if ( co!= null ) {
	            try {
	                co.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
		}
		this.getServletContext().getRequestDispatcher("/valider_insert_score.jsp").forward(request, response);
	}
}
