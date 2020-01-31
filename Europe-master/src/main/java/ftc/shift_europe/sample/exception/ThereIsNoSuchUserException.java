package ftc.shift_europe.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is no such user")
public class ThereIsNoSuchUserException extends RuntimeException {
}
