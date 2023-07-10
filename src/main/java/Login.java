import com.google.gson.Gson;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controles.Erro;
import controles.Usuario;
import controles.UsuarioController;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");

        Gson gson = new Gson();
        Usuario user = gson.fromJson(request.getReader(), Usuario.class);

        UsuarioController ucontrol = new UsuarioController();

        if(ucontrol.login(user)){
            request.getSession().setAttribute("usuario", user);
            getServletContext().setAttribute(request.getSession().getId(), request.getSession());
            user.setSessionID(request.getSession().getId());
            String json = gson.toJson(user);
            response.getWriter().println(json);

        }else{
            request.getSession().removeAttribute("usuario");

            Erro erro = new Erro();
            erro.setDescricao("Login Não Realizado!");
            erro.setCodigo("001");

            String json = gson.toJson(erro);
            response.getWriter().println(json);
        }
    }
}
