package controller.validators;

import com.pavlenko.kyrylo.controller.validator.UserValidator;
import com.pavlenko.kyrylo.controller.validator.statuses.StatusesContainer;
import com.pavlenko.kyrylo.model.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserValidatorTest {

    private final HttpServletRequest REQUEST = mock(HttpServletRequest.class);

    private static final String CORRECT_FIRSTNAME = "Ben";
    private static final String CORRECT_LASTNAME = "Brown";
    private static final String CORRECT_EMAIL = "ben.brown@gmail.com";
    private static final String CORRECT_PASSWORD = "12345678";
    private static final String CORRECT_RE_PASSWORD = "12345678";


    private static final String INCORRECT_FIRSTNAME = "";
    private static final String INCORRECT_LASTNAME = "";
    private static final String INCORRECT_FIRSTNAME_MAX_SIZE = "qwertqwertqwertqwertqwertqwertqwertqwertqwert";
    private static final String INCORRECT_LASTNAME_MAX_SIZE = "qwertqwertqwertqwertqwertqwertqwertqwertqwert";
    private static final String INCORRECT_EMAIL = "ben.brown.mail.com";
    private static final String INCORRECT_EMAIL_MAX_SIZE = "ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben." +
            "ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben.ben@gmail.com";
    private static final String INCORRECT_PASSWORD = "1234567";
    private static final String INCORRECT_RE_PASSWORD = "123";
    private static final String INCORRECT_INPUT_EMPTY = "";

    private UserDto CORRECT_USER_DTO;

    @BeforeEach
    void init() {
        CORRECT_USER_DTO = new UserDto(
                CORRECT_FIRSTNAME,
                CORRECT_LASTNAME,
                CORRECT_EMAIL,
                CORRECT_PASSWORD,
                CORRECT_RE_PASSWORD
        );
    }

    @Test
    void testValidateShouldReturnTrue() {
        assertTrue(UserValidator.validate(CORRECT_USER_DTO, REQUEST));
    }

    @Test
    void testValidateUserWithIncorrectFirstname() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setFirstName(INCORRECT_FIRSTNAME);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectLastname() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setLastName(INCORRECT_LASTNAME);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }
    @Test
    void testValidateUserWithIncorrectFirstnameMaxSize() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setFirstName(INCORRECT_FIRSTNAME_MAX_SIZE);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.FIRST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectLastnameMaxSize() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setLastName(INCORRECT_LASTNAME_MAX_SIZE);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.LAST_NAME_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectEmail() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setEmail(INCORRECT_EMAIL);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMAIL_NOT_MATCH_PATTERN_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectEmailSize() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setEmail(INCORRECT_EMAIL_MAX_SIZE);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMAIL_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectEmailEmpty() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setEmail(INCORRECT_INPUT_EMPTY);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPassword() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setPassword(INCORRECT_PASSWORD);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PASSWORD_SIZE_OUT_OF_BOUNDS_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectPasswordEmpty() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setPassword(INCORRECT_INPUT_EMPTY);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.EMPTY_FIELD_EXCEPTION);
    }

    @Test
    void testValidateUserWithIncorrectRePassword() {
        UserDto USER_WITH_INCORRECT_USERNAME = CORRECT_USER_DTO;
        USER_WITH_INCORRECT_USERNAME.setRepeatPassword(INCORRECT_RE_PASSWORD);

        boolean isValid = UserValidator.validate(USER_WITH_INCORRECT_USERNAME, REQUEST);

        assertFalse(isValid);
        verify(REQUEST, times(1))
                .setAttribute("status", StatusesContainer.PASSWORD_MATCH_EXCEPTION);
    }


}
