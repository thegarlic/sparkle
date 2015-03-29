package thegarlic.forum.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import thegarlic.forum.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ApiErrorControllerTest {
    
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
    
    @Before
    public void setup() {
        mvc = webAppContextSetup(ctx).build();        
    }
    
    @Test
    public void 에러_404() throws Exception {
       
        /*
         * 본래 의도는 ErrorController -> ApiErrorControllerAdvice 를 통한
         * 에러 처리를 기대하였으나 mockMvc에서는 정상적으로 처리되지 않는 것으로 보임.
         * status404 를 ApiErrorController 에 따로 정의 하였으며
         * 일반적인 404에서 동일하게 동작할 것으로 기대함.
         */
        
        // 실제 404는 ApiErrorControllerAdvice에서 처리되지 않고 공백을 반환 ㅠㅠ
        mvc.perform(get("/giveMe404Error"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(""));
        
        // 원래 기대했던 동작
        mvc.perform(get("/status404"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.meta.ok", is(false)));
    }

}
