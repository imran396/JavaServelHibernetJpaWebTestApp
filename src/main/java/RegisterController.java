
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "RegistrationController", urlPatterns = "/register")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class RegisterController extends HttpServlet {
    private final String UPLOAD_DIRECTORY = "/home/imran/projectuodaexam/uploads";

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
       // InputStream fileContent = filePart.getInputStream();

        filePart.write(UPLOAD_DIRECTORY + File.separator + fileName);

        String userName = request.getParameter("userName");
        String password = request.getParameter("password1");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");

        HttpSession session = request.getSession(true);
        try {
            UserDao userDAO = new UserDao();
            userDAO.addUserDetails(userName, password, email, phone, city);
            //response.sendRedirect("Success");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}


