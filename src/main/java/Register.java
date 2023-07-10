import com.google.gson.Gson;
import controles.Erro;
import controles.Usuario;
import controles.UsuarioController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Add CORS headers
        response.addHeader("Access-Control-Allow-Origin", "*"); // replace '*' with your frontend's origin in production
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");

        Gson gson = new Gson();
        Usuario newUser = gson.fromJson(request.getReader(), Usuario.class);

        UsuarioController ucontrol = new UsuarioController();

        if (ucontrol.register(newUser)) {
            // If registration was successful, send back the new user's data.
            String json = gson.toJson(newUser);
            response.getWriter().println(json);
        } else {
            // If registration failed (because a user with this email already exists), send back an error message.
            Erro erro = new Erro();
            erro.setDescricao("Falha na inscrição! Email já existe.");
            erro.setCodigo("001");

            String json = gson.toJson(erro);
            response.getWriter().println(json);
        }
    }
}
