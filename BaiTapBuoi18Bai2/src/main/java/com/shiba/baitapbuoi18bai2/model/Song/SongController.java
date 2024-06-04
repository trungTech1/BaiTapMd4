package com.shiba.baitapbuoi18bai2.model.Song;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
public class SongController {
    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        System.out.println("da vao day");
        model.addAttribute("song", new SongModel());
        model.addAttribute("genres", Arrays.asList("Rock", "Pop", "Hip-hop", "Classical"));
        return "upload-form";
    }
   @PostMapping("/uploaddata")
   public String handleUpload(@ModelAttribute("song") SongModel song, BindingResult result) {
       if (result.hasErrors()) {
           // Kiểm tra lỗi
           return "upload-form";
       }
       // Lấy thông tin file
       MultipartFile file = song.getFile();
       String fileName = file.getOriginalFilename();
       String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

       // Kiểm tra file có phải là file audio không
       if (!Arrays.asList("mp3", "wav", "ogg", "m4p").contains(fileExtension)) {
           result.rejectValue("file", "error.file", "File must be an audio file (.mp3, .wav, .ogg, .m4p)");
           return "upload-form";
       }
       // Lưu file và thông tin bài hát vào cơ sở dữ liệu hoặc thư mục
       return "success";
   }
}
