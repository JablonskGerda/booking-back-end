package lt.sdacademy.booking.beauty.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public abstract class AbstractIT {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    <T> T sendGet(String url, TypeReference<T> type) throws Exception {
        return sendRequest(get(url), type);
    }
    <T> T sendPost(String url, Object body, TypeReference<T> type) throws Exception {
        return sendRequest(post(url).contentType("application/json").content(objectMapper.writeValueAsString(body)), type);
    }
    private <T> T sendRequest(MockHttpServletRequestBuilder requestBuilder, TypeReference<T> type) throws Exception {
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsString(), type);
    }
}
