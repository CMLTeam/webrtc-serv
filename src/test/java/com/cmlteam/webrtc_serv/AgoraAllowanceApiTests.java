package com.cmlteam.webrtc_serv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.cmlteam.webrtc_serv.util.JsonUtil.json;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgoraAllowanceApiTests extends ApiTestsBase {
  private static final String EMAIL = "a@a.com";
  private static final String APP_ID = "aaa1";
  private static final String JSON_REQUEST =
      json().add("email", EMAIL).add("appId", APP_ID).toString();

  @BeforeEach
  void cleanDb() {
    agoraUserRepository.deleteAll();
  }

  @Test
  public void testAllowAccessSuccess() throws Exception {
    // WHEN
    mockMvc
        .perform(
            post("/agora/allowance").content(JSON_REQUEST).contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isOk());

    assertEquals(
        Set.of(APP_ID), agoraUserRepository.getFirstByEmail(EMAIL).orElseThrow().getAppIds());
  }

  @Test
  public void testAllowAccessFailsValidationEmail() throws Exception {
    checkGivesBadRequest("notAnEmail", APP_ID);
  }

  @Test
  public void testAllowAccessFailsValidationBlank() throws Exception {
    checkGivesBadRequest(EMAIL, "");
  }

  @Test
  public void testRevokeAccessSuccess() throws Exception {
    // GIVEN
    agoraUserRepository.save(new AgoraUserPersisted(EMAIL, Set.of(APP_ID)));

    // WHEN
    mockMvc
        .perform(
            delete("/agora/allowance")
                .content(JSON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isOk());

    assertTrue(agoraUserRepository.getFirstByEmail(EMAIL).orElseThrow().getAppIds().isEmpty());
  }

  @Test
  public void testRevokeAccessNotFound() throws Exception {
    // WHEN
    mockMvc
        .perform(
            delete("/agora/allowance")
                .content(JSON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
        // THEN
        .andExpect(status().isNotFound());
  }

  private void checkGivesBadRequest(String email, String appId) throws Exception {
    mockMvc
        .perform(
            post("/agora/allowance")
                .content(json().add("email", email).add("appId", appId).toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
