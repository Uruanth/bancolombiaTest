package co.com.onneq.Error;

import lombok.Getter;

@Getter
public class UserError extends RuntimeException{
    public UserError(String msg){
        super(msg);
    }
}
