package com.isa.usersengine.cdi;

import com.isa.usersengine.exceptions.UserImageNotFound;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Properties;

@RequestScoped
public class FileUploadProcessorDaoBean implements FileUploadProcessorDao {
    private static final String SETTINGS_FILE = "settings.properties";

    @Override
    public String getUploadImageFilePath() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResource(SETTINGS_FILE).openStream());
        return properties.getProperty("Upload.Path.Images");
    }

    public File uploadImageFile(Part filePart) throws IOException, UserImageNotFound {
        String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (filename == null || filename.isEmpty()) {
            throw new UserImageNotFound("No user image has been uploaded");
        }
        File file = new File(getUploadImageFilePath() + filename);

        InputStream is = filePart.getInputStream();
        OutputStream os = new FileOutputStream(file);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        os.flush();
        os.close();
        return file;
    }
}
