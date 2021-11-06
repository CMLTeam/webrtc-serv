package com.cmlteam.webrtc_serv;

import com.cmlteam.webrtc_serv.config.GlobalCorsConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest()
@ContextConfiguration(classes = {TestController.class, GlobalCorsConfig.class})
public class CorsOffTest {
  @Autowired private MockMvc mockMvc;

  @Test
  public void simpleGetTest() throws Exception {
    mockMvc
        .perform(get("/test/ok"))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("OK")))
        .andExpect(header().doesNotExist("Vary"));
  }
}
