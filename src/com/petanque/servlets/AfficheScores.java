package com.petanque.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petanque.beans.ResultatMatch;

/**
 * Servlet implementation class AfficheScores
 */
@WebServlet("/AfficheScores")
public class AfficheScores extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<ResultatMatch> Resultats = new ArrayList<ResultatMatch>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			PreparedStatement ps = co.prepareStatement("SELECT * FROM bdd_thomas.Tournoi;");
			rs=ps.executeQuery();
			//Traitement des resultats
			while (rs.next()) {
				int id_eq1 = rs.getInt("eq1");
				int id_eq2 = rs.getInt("eq2");
				int scEq1M1 = rs.getInt("scEq1M1");
				int scEq2M1 = rs.getInt("scEq2M1");
				int winner = rs.getInt("winner");
				ResultatMatch rm = new ResultatMatch();
				rm.set_eq1(id_eq1);
				rm.set_eq2(id_eq2);
				rm.set_score1(scEq1M1);
				rm.set_score2(scEq2M1);
				rm.set_winner(winner);
				Resultats.add(rm);
			}
			request.setAttribute("resultats", Resultats);			
			
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
		this.getServletContext().getRequestDispatcher("/afficher_scores.jsp").forward(request, response);
		Resultats.clear();
	}


}
