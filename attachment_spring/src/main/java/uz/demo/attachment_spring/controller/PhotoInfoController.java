package uz.demo.attachment_spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.demo.attachment_spring.payload.Response;
import uz.demo.attachment_spring.service.PhotoInfoService;


@RestController
@RequestMapping("/demo/file")
public class PhotoInfoController {
    @Autowired
    PhotoInfoService photoInfoService;

    @PostMapping("/upload")
    public HttpEntity<Response> uploadFile(MultipartHttpServletRequest request){
        return photoInfoService.uploadFile(request);
    }




}
