package com.cmlteam.webrtc_serv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.cmlteam.webrtc_serv.util.JsonUtil.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgoraAllowanceApiTests extends ApiTestsBase {

  @BeforeEach
  void cleanDb() {
    agoraUserRepository.deleteAll();
  }

  @Test
  public void testAllowAccessSuccess() throws Exception {
    String email = "a@a.com";
    String appId = "aaa1";
    mockMvc
        .perform(
            post("/agora/allowance")
                .content(json().add("email", email).add("appId", appId).toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Assertions.assertEquals(
        Set.of(appId), agoraUserRepository.getFirstByEmail(email).orElseThrow().getAppIds());
  }
}
