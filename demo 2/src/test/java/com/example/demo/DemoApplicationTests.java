package com.example.demo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception{
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    
//    @Test()
//    public void whenQuerySuccess() throws Exception{
//    	JSONObject obj = new JSONObject();
//    	obj.put("name", "bingting");
//        mockMvc.perform(MockMvcRequestBuilders.post("/doc/generatePdf")
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(obj.toString()))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
    
    @Test()
    public void htmlToPdf() throws Exception{
    	JSONObject obj = new JSONObject();
    	obj.put("name", "bingting");
    	obj.put("idcard", "43022319891210151X");
        mockMvc.perform(MockMvcRequestBuilders.post("/doc/htmlToPdf")
         		.param("name", "tingting1")
         		.param("idcard", "430223199307071")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test()
    public void md5() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/digest/md5")
         		.param("name", "tingting1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test()
    public void sha256() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/digest/sha256")
         		.param("name", "tingting1")
         		.param("idcard", "430223199307071")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
