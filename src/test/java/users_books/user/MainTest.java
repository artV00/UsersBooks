package users_books.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MainTest {
    private UserService userService;
    private MockMvc mockMvc;

    @Autowired
    public MainTest(UserService userService, MockMvc mockMvc) {
        this.userService = userService;
        this.mockMvc = mockMvc;
    }

    @Test
    void testInvalidUserId() throws Exception {
        mockMvc.perform(get("/users/{id}",10))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testPostUsers() throws Exception {
        UserEntity.Builder userBuilder = new UserEntity.Builder();
        UserEntity user = userBuilder
                .setFirstName("Piotr").setLastName("Kowalski").setEmail("Kowalski@mail.com").setDescription("Admin").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/users/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("Kowalski@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin"))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
        assertNotNull(userService.getUser(id));
    }

    @Test
    void testRetrieveUser() throws Exception {
        UserEntity.Builder userBuilder = new UserEntity.Builder();
        UserEntity user = userBuilder
                .setFirstName("Piotr").setLastName("Kowalski").setEmail("Kowalski@mail.com").setDescription("Admin").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/users/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("Kowalski@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin"))
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

        mockMvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("Kowalski@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        UserEntity.Builder userBuilder = new UserEntity.Builder();
        UserEntity user = userBuilder
                .setFirstName("Piotr").setLastName("Kowalski").setEmail("Kowalski@mail.com").setDescription("Admin").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/users/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("Kowalski@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin"))
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

        UserEntity updateUser = userBuilder
                .setFirstName("Piotr1").setLastName("Kowalski1").setEmail("Kowalski1@mail.com").setDescription("Admin1").build();

        mockMvc.perform(put("/users/{id}", id)
                        .contentType("application/json").content(objectMapper.writeValueAsString(updateUser)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.firstName").value("Piotr1"))
                .andExpect(jsonPath("$.lastName").value("Kowalski1"))
                .andExpect(jsonPath("$.email").value("Kowalski1@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        UserEntity.Builder userBuilder = new UserEntity.Builder();
        UserEntity user = userBuilder
                .setFirstName("Piotr").setLastName("Kowalski").setEmail("Kowalski@mail.com").setDescription("Admin").build();
        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletResponse response = mockMvc.perform(post("/users/")
                        .contentType("application/json").content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.firstName").value("Piotr"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"))
                .andExpect(jsonPath("$.email").value("Kowalski@mail.com"))
                .andExpect(jsonPath("$.description").value("Admin"))
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");

        mockMvc.perform(delete("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
