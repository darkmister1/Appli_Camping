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

import com.petanque.beans.Doublette;
import com.petanque.beans.TirageEquipe;

/**
 * Servlet implementation class Tirage
 */
@WebServlet("/Tirage")
public class Tirage extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			PreparedStatement ps = co.prepareStatement("SELECT * FROM bdd_thomas.Doublettes;");
			rs=ps.executeQuery();
			int nombre_doublette = 0;
			rs.last();
			nombre_doublette = rs.getInt("id");
			rs.beforeFirst();
			List<Tirage> tirage_complet = new ArrayList<Tirage>();
			//Traitement des resultats
			while (rs.next()) {
				ArrayList<Integer> tirage = new ArrayList<Integer>();				
				for (int i = 1; i<nombre_doublette; i++) {
					tirage.add(i);
				}
				int elem = tirage.indexOf(rs.getInt("id"));
				tirage.remove(elem);
				TirageEquipe t = new TirageEquipe();
				Doublette d = new Doublette();
				t.set_tableau_tirage(tirage);
			}
			request.setAttribute("tableau_tirage", tirage_complet);			
			
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
		
	}


}
