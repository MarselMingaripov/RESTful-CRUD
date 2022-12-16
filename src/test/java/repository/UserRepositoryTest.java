package repository;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.min.entity.User;
import ru.min.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;




class UserRepositoryTest {
    private static final long ID = 1L;
    private static final String USERNAME = "USERNAME";

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private EntityManager entityManager = Mockito.mock(EntityManager.class);


    @Test
    void whenFindByUsername_thenReturnUser() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        User user = new User();//mock(User.class);
        user.setUsername(USERNAME);
        user.setEmail("emailTest@mail.com");
        user.setPassword("123456Qw");
        entityManager.persist(user);
        entityManager.flush();

        Mockito.verify(entityManager).persist(captor.capture());

        User actual = captor.getValue();
        System.out.println(actual);

        assertNotNull(actual);
        assertEquals(user, actual);
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