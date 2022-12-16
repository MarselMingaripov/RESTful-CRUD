package repository;

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
        given(userRepository.findUserByUsername(USERNAME)).willReturn(user);
        entityManager.persist(user);
        entityManager.flush();
        System.out.println(user);
        Mockito.verify(entityManager).persist(captor.capture());

        User actual = captor.getValue();
        User found = userRepository.findUserByUsername(USERNAME);
        Mockito.verify(userRepository).findUserByUsername(USERNAME);

        //System.out.println(actual);
        System.out.println(found);

        assertNotNull(actual);
        assertEquals(actual, found);
    }

//    @Test
//    void existsByUsername() {
//    }
//
//    @Test
//    void existsByEmail() {
//    }

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
//    void findUserByUsername() {
//    }
}