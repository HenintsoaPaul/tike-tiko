import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/pdf-from-spring")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");

        // Extrait le nom du fichier à partir de l'en-tête "Content-Disposition"
        String header = filePart.getHeader("Content-Disposition");
        String fileName = extractFileName(header);


        // Chemin où le fichier sera enregistré
        String uploadPath = "C:\\Users\\Henintsoa\\Documents\\tike_tiko_data\\" + fileName;
        try (InputStream fileContent = filePart.getInputStream()) {
            // Crée les répertoires nécessaires s'ils n'existent pas
            Files.createDirectories(Paths.get(uploadPath).getParent());
            // Enregistre le fichier sur le serveur
            Files.copy(fileContent, Paths.get(uploadPath), StandardCopyOption.REPLACE_EXISTING);
        }

        response.getWriter().write("File received successfully");
    }

    private String extractFileName(String contentDisposition) {
        for (String part : contentDisposition.split(";")) {
            if (part.trim().startsWith("filename")) {
                return part.split("=")[1].trim().replace("\"", "");
            }
        }
        return "unknown_file";
    }
}
