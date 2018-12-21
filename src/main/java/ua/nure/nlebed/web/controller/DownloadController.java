package ua.nure.nlebed.web.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.nlebed.web.Navigation;
import ua.nure.nlebed.web.Section;

@Controller
@Navigation(Section.DOWNLOADS)
public class DownloadController {

    @RequestMapping("/downloads")
    public String downloads() {
        return "downloads/index";
    }

    @RequestMapping(value="/downloads/android")
    public ResponseEntity<InputStreamResource> downloadFile() {
        InputStreamResource resource = new InputStreamResource(getClass().getClassLoader()
                .getResourceAsStream("downloads/android/app.apk"));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "androidNureWifi.apk")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(resource);
    }


}
