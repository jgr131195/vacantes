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
 * Servlet implementation class VacanteControlador
 */
@WebServlet("/vacante")
public class VacanteControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacanteControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Recibimos el parametro de accion, para ver que solicito el cliente.
		String action = request.getParameter("action");
		if ("ver".equals(action)) {
			this.verDetalle(request, response);
		} else if ("lista".equals(action)) {
			this.verTodas(request, response);
		}
	}

	private void verTodas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbConnection conn = new DbConnection();
		VacanteDao vacanteDao = new VacanteDao(conn);
		List<Vacante> lista = vacanteDao.getAll();
		conn.disconnect();
		// Compartimos la variable lista, para poder accederla desde la vista
		request.setAttribute("vacantes", lista);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/vacantes.jsp");
		rd.forward(request, response);
	}

	private void verDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recibimos id de la vacante a consultar
		int idVacante = Integer.parseInt(request.getParameter("id"));
		DbConnection conn = new DbConnection();
		VacanteDao vacanteDao = new VacanteDao(conn);
		Vacante vacante = vacanteDao.getById(idVacante);
		conn.disconnect();
		// Compartimos la variable , para poder accederla desde la vista con Expression
		// Language
		request.setAttribute("vacante", vacante);
		RequestDispatcher rd;
		// Enviarmos respuesta. Renderizamos la vista detalle.jsp
		rd = request.getRequestDispatcher("/detalle.jsp");
		rd.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// Recibimos parametros del formulario
		Vacante vacante = new Vacante(0);
		String nombreParam = request.getParameter("nombre");
		vacante.setNombre(nombreParam);
		String descripcionParam = request.getParameter("descripcion");
		vacante.setDescripcion(descripcionParam);
		String detalleParam = request.getParameter("detalle");
		vacante.setDetalle(detalleParam);
		System.out.println(vacante); //Muy importante ir comprobándolo todo
		
		// Procesamos los datos. Guardar en BD
		DbConnection conn = new DbConnection();
		VacanteDao vacanteDao = new VacanteDao(conn);
		boolean status = vacanteDao.insert(vacante);
		// Preparamos un mensaje para el usuario
		String msg = "";
		if (status) {
		msg = "La vacante fue guardada correctamente.";
		} else {
		msg = "Ocurrio un error. La vacante no fue guardada.";
		}
		conn.disconnect();
		RequestDispatcher rd;
		// Compartimos la variable msg, para poder accederla desde la vista con Expression Language
		request.setAttribute("message", msg);
		// Enviarmos respuesta. Renderizamos la vista mensaje.jsp
		rd = request.getRequestDispatcher("mensaje.jsp");
		rd.forward(request, response);
	
	
	}

}
