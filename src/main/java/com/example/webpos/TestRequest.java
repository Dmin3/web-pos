package com.example.webpos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Getter
public class TestRequest {
    private String tagNickname;
    private String content;

//    @JsonCreator
//    public TestRequest(@JsonProperty("name") String name, @JsonProperty("content") String content) {
//        System.out.println("생성자 이용하기!! ->" + name + content);
//        if (content == null) {
//            content = "생성자로 변경";
//        }
//        this.name = name;
//        this.content = content;
//    }

    public void validationTagNickname() {
        if (tagNickname != null && tagNickname.trim().startsWith("@")) {
            this.content = tagNickname + " " + content;
            this.tagNickname = tagNickname.trim().substring(1);
            return;
        }
        this.tagNickname = null;
    }
}
