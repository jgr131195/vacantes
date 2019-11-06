package controlador;

import java.io.IOException;

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
