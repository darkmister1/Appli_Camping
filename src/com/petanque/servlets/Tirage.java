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
import com.petanque.beans.Joueur;
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
			ArrayList<TirageEquipe> tirage_complet = new ArrayList<TirageEquipe>();
			//Traitement des resultats
			int j = 1;
			while (rs.next()) {
				ArrayList<Integer> tirage = new ArrayList<Integer>();
				TirageEquipe t = new TirageEquipe();
				Doublette d = new Doublette();
				Joueur j1 = new Joueur();
				Joueur j2 = new Joueur();
				for (int i = 1; i<=nombre_doublette; i++) {
					tirage.add(i);
					System.out.println("Add :" + i);
				}
				int elem = tirage.indexOf(rs.getInt("id"));
				System.out.println("Elem " + elem);
				tirage.remove(elem);
		
				String nom1 = rs.getString("nom1");
				String nom2 = rs.getString("nom2");
				String prenom1 = rs.getString("prenom1");
				String prenom2 = rs.getString("prenom2");
				
				j1.set_nomJoueur(nom1);
				j1.set_prenomJoueur(prenom1);
				
				j2.set_nomJoueur(nom2);
				j2.set_prenomJoueur(prenom2);
				
				d.set_joueur1(j1);
				d.set_joueur2(j2);
				d.set_numeroDoublette(j);
				
				t.set_doublette(d);
				t.set_tableau_tirage(tirage);
				
				tirage_complet.add(t);
				tirage.clear();
				j = j + 1;
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
		this.getServletContext().getRequestDispatcher("/afficher_tirage.jsp").forward(request, response);
		
	}


}
