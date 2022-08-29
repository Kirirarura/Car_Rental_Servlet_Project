package model.service;

import com.pavlenko.kyrylo.model.dao.UserDao;
import com.pavlenko.kyrylo.model.dto.UserDto;
import com.pavlenko.kyrylo.model.entity.User;
import com.pavlenko.kyrylo.model.exeption.AuthenticationException;
import com.pavlenko.kyrylo.model.exeption.DataBaseException;
import com.pavlenko.kyrylo.model.exeption.EmailIsAlreadyRegisteredException;
import com.pavlenko.kyrylo.model.exeption.UserIsBlockedException;
import com.pavlenko.kyrylo.model.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserServiceTest {

    UserDao userDao = mock(UserDao.class);
    UserService userService = new UserService(userDao);


    private static final String EMAIL = "random.mail@gmail.com";
    private static final String PASSWORD = "Strongest_PASSWORD_31";
    private static final String FAKE_PASSWORD = "FAKE_PASSWORD";

    private static final User UNBLOCKED_USER = User.builder()
            .id(1L)
            .email(EMAIL)
            .password(PASSWORD)
            .isBlocked(1)
            .build();


    private static final User BLOCKED_USER = User.builder()
            .id(1L)
            .email(EMAIL)
            .password(PASSWORD)
            .isBlocked(0)
            .build();

    private static final UserDto USER_DTO = new UserDto(
            "FIRSTNAME",
            "LASTNAME",
            EMAIL,
            PASSWORD,
            PASSWORD
    );

    @Test
    void testAuthenticationShouldNotThrowException() throws DataBaseException {
        when(userDao.findByUsernameAndPassword(EMAIL, PASSWORD)).thenReturn(Optional.of(UNBLOCKED_USER));

        assertDoesNotThrow(() -> userService.authentication(EMAIL, PASSWORD));
    }

    @Test
    void testAuthenticationShouldThrowIsBlockedException() throws DataBaseException {
        when(userDao.findByUsernameAndPassword(EMAIL, PASSWORD)).thenReturn(Optional.of(BLOCKED_USER));

        assertThrows(
                UserIsBlockedException.class,
                () -> userService.authentication(EMAIL, PASSWORD)
        );
    }

    @Test
    void testAuthenticationShouldThrowAuthenticationException() throws DataBaseException {
        when(userDao.findByUsernameAndPassword(EMAIL, PASSWORD)).thenReturn(Optional.empty());

        assertThrows(
                AuthenticationException.class,
                () -> userService.authentication(EMAIL, PASSWORD)
        );
    }

    @Test
    void testRegisterNewAccountShouldWorkWithoutException() throws DataBaseException {
        when(userDao.emailAlreadyExists(EMAIL)).thenReturn(false);

        assertDoesNotThrow(() -> userService.registerNewAccount(USER_DTO));

        verify(userDao, times(1)).create(UNBLOCKED_USER);
    }

    @Test
    void testRegisterNewAccountShouldThrowUsernameIsReservedException() throws DataBaseException {
        when(userDao.emailAlreadyExists(EMAIL)).thenReturn(true);

        assertThrows(
                EmailIsAlreadyRegisteredException.class,
                () -> userService.registerNewAccount(USER_DTO)
        );
    }

    @Test
    void testRegisterNewManagerAccountShouldWorkWithoutException() throws DataBaseException {
        when(userDao.emailAlreadyExists(EMAIL)).thenReturn(false);

        assertDoesNotThrow(() -> userService.registerNewManagerAccount(USER_DTO));

        verify(userDao, times(1)).create(UNBLOCKED_USER);
    }

    @Test
    void testBlockUserById() throws DataBaseException {
        userService.blockById(1);
        verify(userDao, times(1)).blockById(1);
    }

    @Test
    void testUnBlockUserById() throws DataBaseException {
        userService.unblockById(1);
        verify(userDao, times(1)).unblockById(1);
    }

    @Test
    void testFindUserById() throws DataBaseException {
        userService.findById(1L);
        verify(userDao, times(1)).findById(1L);
    }

    @Test
    void testFindAllUsers() throws DataBaseException {
        userService.findAllUsers();
        verify(userDao, times(1)).findAll();
    }



}
