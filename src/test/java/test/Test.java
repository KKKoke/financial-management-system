package test;

import com.dhy_zk.financialSystem.Main;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author 大忽悠
 * @create 2022/1/19 21:42
 */
//@SpringBootTest(classes = Main.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test
{
   // @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @org.junit.jupiter.api.Test
    public void test()
    {
        passwordEncoder=new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("123", "$2a$10$LDWYjQPXY1uTiLvrvWXcWOh9sGdtjeh4Yfm19QzW48.tA8O7jVL7m");
        System.out.println(matches);
    }
}
