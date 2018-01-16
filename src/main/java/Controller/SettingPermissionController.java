package Controller;

import custom.bean.Role;
import custom.bean.Teacher;
import custom.services.AclDao;
import custom.services.RoleDao;
import custom.services.TeacherClasDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SettingPermissionController", urlPatterns = "/settings-permission")
public class SettingPermissionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String id = req.getParameter("roleId");
        if(id != null && !id.isEmpty()){
            Role role = RoleDao.findRoleById(id);
            req.setAttribute("role",  role);
            AclDao.setPermission(role);
            // AclDao.test();
           // TeacherClasDao.save();
        }

        req.getRequestDispatcher("WEB-INF/settings_permission.jsp").forward(req, resp);
    }
}