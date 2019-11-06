package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DbConnection;
import dao.VacanteDao;
import modelo.Vacante;

/**
 * Servlet implementation class BusquedaControlador
 */
@WebServlet("/buscar")
public class BusquedaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// Recibimos la cadena de busqueda del usuario
		String q = request.getParameter("query");
		List<Vacante> lista = null;
		DbConnection conn = new DbConnection();
		// Con nuestro objeto DAO, hacemos la busqueda de vacantes del usaurio
		VacanteDao vacanteDao = new VacanteDao(conn);
		lista = vacanteDao.getByQuery(q);
		conn.disconnect();
		RequestDispatcher rd;
		request.setAttribute("vacantes", lista); // Redireccionamos al vacantes.jsp que ya tenemos
		rd = request.getRequestDispatcher("/vacantes.jsp");
		rd.forward(request, response);
	}

}
