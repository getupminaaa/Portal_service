package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;


public class UserDaoTests {
    static UserDao userDao;

    @BeforeAll
    public static void setup() throws ClassNotFoundException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("kr.ac.jejunu"); //Bean을 관리하는 spring의 기본 오브젝트, 어노테이션으로 정의된 애들을 관리 -> daoFactory
        userDao = applicationContext.getBean("userDao", UserDao.class); //Bean은 고유한 이름이 있음, getBean.(메소드 명을 이름으로 채택, userDao라는 클래스 타입임을 정의함)
        // context -> strategy pattern 적용 ,dependency lookup
/*
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("daoFactory.xml");
        userDao = applicationContext.getBean("userDao", UserDao.class);

*/

    }


    @Test
    public void get() {
        Integer id = 1;
        String name = "hulk";
        String password = "1234";
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() {
        String name = "허윤호";
        String password = "1111";

        User user = User.builder().name(name).password(password).build();
        userDao.insert(user); //요구사항을 다 담음

        User insertedUser = userDao.findById(user.getId());// 우리가 만든 것을 가지고 찾아오자 , id는 자동생성

        assertThat(user.getId(), greaterThan(0)); //DB에 등록이 되었다면 실제값을 찾아오자
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update(){
        String name = "허윤호";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user); //요구사항을 다 담음

        user.setName("hulk");
        user.setPassword("1234");

        userDao.update(user);

        User updateUser = userDao.findById(user.getId());

        assertThat(updateUser.getId(),is(user.getId()));
        assertThat(updateUser.getName(),is(user.getName()));
        assertThat(updateUser.getPassword(),is(user.getPassword()));

    }
    @Test
    public void delete() {
        String name = "허윤호";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user); //요구사항을 다 담음

        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser,nullValue());
    }
//
//    @Test
//    public void getHalla() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "hulk";
//        String password = "1234";
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//    @Test
//    public void insertHalla() throws SQLException, ClassNotFoundException {
//        String name ="Halla허윤호";
//        String password = "1111";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        userDao.insert(user); //요구사항을 다 담음
//
//        User insertedUser = userDao.findById(user.getId());// 우리가 만든 것을 가지고 찾아오자 , id는 자동생성
//
//        assertThat(user.getId(),greaterThan(0)); //DB에 등록이 되었다면 실제값을 찾아오자
//        assertThat(insertedUser.getId(),is(user.getId()));
//        assertThat(insertedUser.getName(),is(user.getName()));
//        assertThat(insertedUser.getPassword(),is(user.getPassword()));
//    }

}









