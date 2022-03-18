package de.neuefische.todoapp.loginfeatures.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appuser")
@Data
public class AppUser {
    
    @Id
    private String userid;
    private String username;
    private String password;
    private String role;


}
