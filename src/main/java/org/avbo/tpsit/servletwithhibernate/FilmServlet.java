package org.avbo.tpsit.servletwithhibernate;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FilmServlet
 */
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Ottiene il context della servlet
		ServletContext context = config.getServletContext();
		// Ottiene il percorso in cui è stato spostato il file
		File f = new File(context.getRealPath("sakila_master.db"));
		// Aggiorna il percorso del database prima che il file venga aperto
		HibernateUtil.SetFilePath(f.getPath());
	}

	/**
	 * Default constructor.
	 */
	public FilmServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FilmDao dao = new FilmDao();

		if (request.getParameter("id_film") != null) {
			int film_id = Integer.parseInt(request.getParameter("id_film"));
			Film film = dao.getFilm(film_id);
			
			if (film != null) {
				response.getWriter().append(film.getTitle() + ": ").append(request.getContextPath());
			}
			else {
				response.getWriter().append("Film non trovato");
				response.setStatus(404);
			}
				
		}
		else {
			response.getWriter().append("Nessun id del film richiesto");
			response.setStatus(400);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
