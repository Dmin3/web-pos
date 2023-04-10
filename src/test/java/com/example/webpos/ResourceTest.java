package com.example.webpos;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourceTest {
    private Resource resource;

    @Test
    void 파일_받아_오기() throws IOException {
        resource = new ClassPathResource("/word/badword_list.txt");
        String filename = resource.getFilename();
        assertThat(filename).isEqualTo("badword_list.txt");
    }

    @Test
    void 파일_내용_가져오기_TXT() throws IOException {
        resource = new ClassPathResource("/word/badword_list.txt");
        InputStream is = resource.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        List<String> badWordList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            badWordList.add(line);
        }

        for (String s : badWordList) {
            System.out.println("욕 = " + s);
        }

        System.out.println("욕 사이즈 = " + badWordList.size());
    }

    @Test
    void 파일_내용_가져오기_CSV() throws IOException {
        resource = new ClassPathResource("/word/badword_list.csv");
        InputStream is = resource.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        List<String> badWordList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            badWordList.add(line);
        }

        for (String s : badWordList) {
            System.out.println("욕 = " + s);
        }

        System.out.println("욕 사이즈 = " + badWordList.size());
    }
}
