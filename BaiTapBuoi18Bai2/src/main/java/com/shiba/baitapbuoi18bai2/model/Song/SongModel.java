package com.shiba.baitapbuoi18bai2.model.Song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongModel {
    private String title;
    private String artist;
    private String genre;
    private MultipartFile file;
}
