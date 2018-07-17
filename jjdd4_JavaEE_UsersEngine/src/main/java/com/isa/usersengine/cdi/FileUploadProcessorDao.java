package com.isa.usersengine.cdi;

import com.isa.usersengine.exceptions.UserImageNotFound;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public interface FileUploadProcessorDao {
     String getUploadImageFilePath() throws IOException;
     File uploadImageFile(Part filePart) throws IOException, UserImageNotFound;
}
