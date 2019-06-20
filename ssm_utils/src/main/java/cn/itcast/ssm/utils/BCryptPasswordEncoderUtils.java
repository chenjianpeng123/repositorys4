package cn.itcast.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密根据类
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public static String encodePassword(String password){
       return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        //$2a$10$B/RzIHEiNHDvnNDvTl9RtOOHp/xGGCenmQv6aXP0VgGnFiVVmYZBK
        String password1 = encodePassword(password);
        System.out.println(password1);
    }
}
