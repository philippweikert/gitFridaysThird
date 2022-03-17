package de.neuefische.todoapp.loginfeatures;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class User {
    
    @Id
    private String userid;
    private String username;
    private String userpassword;
    
}
