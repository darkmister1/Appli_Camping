package com.petanque.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petanque.beans.Joueur;

/**
 * Servlet implementation class AddJoueur
 */
@WebServlet("/AddJoueur")
public class AddJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperation des champs de la page
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		//Creation du bean
		Joueur j = new Joueur();
		
		j.set_nomJoueur(nom);
		j.set_prenomJoueur(prenom);
		
		//Insertion du joueur en DB
		List<String> messages = j.insertJoueurIntoDB(request);		
		
		request.setAttribute("messages", messages);
		
		//Renvoi vers l'affichage
		this.getServletContext().getRequestDispatcher("/messages_database.jsp").forward(request, response);
		
	}	

}
