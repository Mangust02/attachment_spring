package uz.demo.attachment_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.demo.attachment_spring.entity.PhotoInfo;
import uz.demo.attachment_spring.enums.Quality;
import uz.demo.attachment_spring.payload.Response;
import uz.demo.attachment_spring.repository.PhotoInfoRepository;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

@Service
public class PhotoInfoService {

    @Value("${file.folder}")
    private String path;

    @Autowired
    PhotoInfoRepository photoInfoRepository;

    public HttpEntity<Response> uploadFile(MultipartHttpServletRequest request) {

        Iterator<String> iterator = request.getFileNames();
        MultipartFile multipartFile;
        while (iterator.hasNext()) {
            multipartFile = request.getFile(iterator.next());
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setSize(multipartFile.getSize());
            photoInfo.setName(multipartFile.getOriginalFilename());
            photoInfo.setContentType(multipartFile.getContentType());
            photoInfo.setExtension(getExtension(multipartFile.getOriginalFilename()));
            PhotoInfo save = photoInfoRepository.save(photoInfo);

            Calendar calendar = new GregorianCalendar();
            File uploadFolder = new File(path+"/"+Quality.ORIGINAL+"/"+calendar.get(Calendar.YEAR) + "/" + calendar.get((Calendar.MONTH) + 1)+"/"+calendar.get(Calendar.DAY_OF_MONTH));

         if (uploadFolder.mkdirs()&&uploadFolder.exists()){

             System.out.println("New Folder created -> " + uploadFolder.getAbsolutePath());
         }

            File file = new File(uploadFolder + "/" + save.getId() + "_" + save.getExtension());
            save.setPathOriginal(file.getAbsolutePath());

            try {
                multipartFile.transferTo(file);
                photoInfoRepository.save(save);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("save", HttpStatus.ACCEPTED.value()));
    }


    public String getExtension(String fileName) {
        String e = null;

        if (fileName != null && fileName.isEmpty()) {
            int point = fileName.lastIndexOf(".");
            if (point > 0 && point <= fileName.length() - 2) {
                e = fileName.substring(point);
            }
        }
        return e;
    }

}
