package Controller;

import custom.bean.Role;
import custom.services.RoleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RolesController", urlPatterns = "/roles")
public class RolesController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("edit");
        if(id != null && !id.isEmpty()){
            Role role = RoleDao.findRoleById(id);
            req.setAttribute("role",  role);
            role.setName(req.getParameter("role"));
            RoleDao.edit(role);
            List<Role> roles = RoleDao.getRoles();
            req.setAttribute("roles", roles);
            req.getRequestDispatcher("WEB-INF/edit_roles.jsp").forward(req, resp);
        }else{
            RoleDao.add(req.getParameter("role"));
            List<Role> roles = RoleDao.getRoles();
            req.setAttribute("roles", roles);
            req.getRequestDispatcher("WEB-INF/roles.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = RoleDao.getRoles();
        req.setAttribute("roles", roles);
        String id = req.getParameter("edit");
        if(id != null && !id.isEmpty()){
            req.setAttribute("role",  RoleDao.findRoleById(id));
            req.getRequestDispatcher("WEB-INF/edit_roles.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("WEB-INF/roles.jsp").forward(req, resp);
        }
    }
}
