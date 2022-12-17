package repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.*;
import ru.min.entity.User;
import ru.min.repository.UserRepository;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


class UserRepositoryTest {
    private static final long ID = 1L;
    private static final String USERNAME = "USERNAME";

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private EntityManager entityManager = Mockito.mock(EntityManager.class);

    @Test
    void whenFindUserByUsername_thenReturnUser() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail("emailTest@mail.com");
        user.setPassword("123456Qw");

        entityManager.persist(user);
        entityManager.flush();
        Mockito.verify(entityManager).persist(captor.capture());

        given(userRepository.findUserByUsername(USERNAME)).willReturn(user);
        Mockito.verify(userRepository).findUserByUsername(USERNAME);

        User actual = captor.getValue();
        User found = userRepository.findUserByUsername(USERNAME);

        assertNotNull(actual);
        assertEquals(actual, found);
    }

    @Test
    void whenExistsByUsername_thenReturnBoolean() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail("emailTest@mail.com");
        user.setPassword("123456Qw");

        entityManager.persist(user);
        entityManager.flush();
        Mockito.verify(entityManager).persist(captor.capture());

        given(userRepository.existsByUsername(USERNAME)).willReturn(false);

        User actual = captor.getValue();
        boolean test = userRepository.existsByUsername(USERNAME);
        Mockito.verify(userRepository).existsByUsername(USERNAME);

        assertNotNull(false);
        assertEquals(false, test);

    }

    @Test
    void existsByEmail() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        User user = new User();
        user.setUsername(USERNAME);
        user.setEmail("emailTest@mail.com");
        user.setPassword("123456Qw");

        entityManager.persist(user);
        entityManager.flush();
        Mockito.verify(entityManager).persist(captor.capture());

        User actual = captor.getValue();

        given(userRepository.existsByEmail(actual.getEmail())).willReturn(false);

        boolean test = userRepository.existsByEmail(actual.getEmail());
        Mockito.verify(userRepository).existsByEmail(actual.getEmail());

        assertNotNull(false);
        assertEquals(false, test);
    }

//    @Test
//    void whenFindById_thenReturnUser() {
//        final User user = mock(User.class);
//        user.setUsername("userNameTest");
//        user.setEmail("emailTest@mail.com");
//        user.setPassword("123456Qw");
//        entityManager.persist(user);
//        when(user.getId()).thenReturn(ID);
//
//        userRepository.findById(ID);
//        final User actual = userRepository.findById(ID);
//
//        assertNotNull(actual);
//        assertEquals(user, actual);
//    }

//    @Test
//    void findByUsername() {
//    }
}