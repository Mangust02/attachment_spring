package uz.demo.attachment_spring.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private int success;
    private Object info;

    public Response(String message, int success){
        this.message = message;
        this.success = success;
    }

}
