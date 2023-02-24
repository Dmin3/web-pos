package com.example.webpos.controller;

import com.example.webpos.common.error.GlobalExceptionHandler;
import com.example.webpos.member.domain.MemberType;
import com.example.webpos.member.dto.MemberSignUpReq;
import com.example.webpos.member.controller.MemberController;
import com.example.webpos.member.service.MemberServiceImpl;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
    @InjectMocks
    private MemberController target;

    @Mock
    private MemberServiceImpl memberService;

    private MockMvc mvc;
    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    @DisplayName("mockMvc가Null이아님")
    public void mockMvc() throws Exception {
        Assertions.assertThat(target).isNotNull();
        Assertions.assertThat(mvc).isNotNull();
    }

    @DisplayName("멤버 등록실패_식별값이 없음")
    @Test
    void failMember() throws Exception {
        // given
        final String url = "/api/member";

        // when
        ResultActions resultActions = mvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(memberSignUpReq("test", "test@test", "123", "010-11-11", MemberType.ROLE_NORMAL)))
                        .contentType(MediaType.APPLICATION_JSON)
        );


        // then
//        resultActions.andExpect(status().isCreated());
    }

    @DisplayName("멤버 이름이 null 일 때")
    @Test
    void memberNameIsNull() throws Exception {
        // given
        final String url = "/api/member";
        MemberSignUpReq req = memberSignUpReq(null, "test@test", "123", "010-11-11", MemberType.ROLE_NORMAL);

        // when
        ResultActions resultActions = mvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(gson.toJson(req))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isNotFound());

    }

    @DisplayName("멤버 등록 성공")
    @Test
    void saveMember(){
        // given

        // when

        // then

    }


    private MemberSignUpReq memberSignUpReq(String name, String email, String password, String phone, MemberType memberType) {
        return new MemberSignUpReq(name, email, password, phone, memberType);
    }

}