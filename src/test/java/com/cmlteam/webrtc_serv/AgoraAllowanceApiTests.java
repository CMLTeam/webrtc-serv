package com.cmlteam.webrtc_serv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    mockMvc
        .perform(
            post("/agora/allowance")
                .content(json().add("email", "a@a.com").add("appId", "aaa1").toString()))
        .andExpect(status().isOk());
  }
}
