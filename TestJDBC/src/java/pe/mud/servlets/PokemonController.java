package pe.mud.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pe.mud.dao.PokemonDao;
import pe.mud.model.PokemonModel;
import pe.mud.service.PokemonService;

@MultipartConfig
@WebServlet({"/addPokemon", "/Main"})
public class PokemonController extends HttpServlet {
    
    private PokemonService servicio;
    
    //Datos fotito
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    
    @Override
    public void init() throws ServletException
    {
        servicio = new PokemonService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if(path.equals("/addPokemon"))
        {
            addPokemon(request, response);
        }else if (path.equals("/Main")) {
            listarPokemon(request, response);
        }
    }
    
    protected void addPokemon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        if (!isMultipart) {
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEMORY_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            
            String nombre = "";
            String img = "";
            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equals("nombre")) {
                        nombre = item.getString();
                        request.setAttribute("nombre", nombre);
                    }
                }else{
                    //Agregado por Adrian : Crea un nombre akjsnaif2eudadd7687 asi
                    File file = File.createTempFile("img", ".png" , factory.getRepository() );
                    
                    String filePath = uploadFolder + File.separator + file.getName() ; //fileName;
                    File uploadedFile = new File(filePath);
                    item.write(uploadedFile);
                    
                    img = DATA_DIRECTORY + File.separator + file.getName();
                    request.setAttribute("img", img );
                }
            }
            servicio.addPokemon(nombre, img);
            
           
        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
        
        
        /*
        try {
            //Datos
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            //Datos img
            
            //Proceso
            servicio.addPokemon(nombre, tipo);
            listarPokemon(request, response);
            request.setAttribute("msg", "Proceso ok");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }
        */
        
        //Forward
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("Main");
        rd.forward(request, response);
    }
    
    protected void listarPokemon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //Datos
            PokemonDao dao = new PokemonDao();
            List<PokemonModel> listaPokemon = new ArrayList<PokemonModel>();
            listaPokemon = dao.listar();
            //Proceso
            request.setAttribute("listaPokemon", listaPokemon);
            
        } catch (Exception e) {
        }
        
        //Forward
        RequestDispatcher rd;
        rd= request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
    
    
}
