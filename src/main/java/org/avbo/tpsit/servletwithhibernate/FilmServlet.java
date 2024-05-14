package org.avbo.tpsit.servletwithhibernate;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

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

		switch (request.getServletPath()) {
		case "/GetFilm":
			GetFilm(request, response);
			break;
		case "/DeleteFilm":
			DeleteFilm(request, response);
			break;
		case "/GetAllFilms":
			GetAllFilms(request, response);
			break;
		default:
			response.getWriter().append("Servizio non esistente");
			response.setStatus(404);
		}

	}

	protected void DeleteFilm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDao dao = new FilmDao();

		if (request.getParameter("id_film") != null) {
			int film_id = Integer.parseInt(request.getParameter("id_film"));

			if (dao.deleteFilm(film_id)) {
				response.getWriter().append("Film eliminato");
			} else {
				response.getWriter().append("Film non trovato");
				response.setStatus(404);
			}

		} else {
			response.getWriter().append("Nessun id del film richiesto");
			response.setStatus(400);
		}
	}

	protected void GetFilm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDao dao = new FilmDao();

		if (request.getParameter("id_film") != null) {
			int film_id = Integer.parseInt(request.getParameter("id_film"));
			Film film = dao.getFilm(film_id);

			if (film != null) {
				Gson gson = new Gson();
				response.getWriter().append(gson.toJson(film));
				response.setContentType("application/json");
			} else {
				response.getWriter().append("Film non trovato");
				response.setStatus(404);
			}

		} else {
			response.getWriter().append("Nessun id del film richiesto");
			response.setStatus(400);
		}
	}

	protected void GetAllFilms(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmDao dao = new FilmDao();

		var films = dao.getAllFilms();

		if (films != null) {
			Gson gson = new Gson();
			response.getWriter().append(gson.toJson(films));
			response.setContentType("application/json");
		} else {
			response.getWriter().append("Nessun film non trovato");
			response.setStatus(404);
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
