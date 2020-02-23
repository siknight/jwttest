package jwt.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
@SuppressWarnings("all")
public class JwtTest {
    /**
     * 创建jwt
     */
    @Test
    public void test01(){
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date()) //用于设置签发时间
                .signWith(SignatureAlgorithm.HS256, "itcast"); //设置签名秘钥

        System.out.println(builder.compact());

    }

    /**
     * 解析jwt
     */
    @Test
    public void test02(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODI0ODE2Mjd9.pkQbEQiI2xjMcSH4kAQq4lUfXwPbS_s-qk8ING3TziU";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt："+claims.getIssuedAt());
    }


    /**
     * 创建jwt  设置token过期时间
     */
    @Test
    public void test03(){
        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date()) //用于设置签发时间
                .signWith(SignatureAlgorithm.HS256, "itcast") //设置签名秘钥
                .setExpiration(new Date(exp));

        System.out.println(builder.compact());

    }

    /**
     * 解析jwt 有token过期时间
     */
    @Test
    public void test04(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODI0ODI0MDYsImV4cCI6MTU4MjQ4MjQ2Nn0.P8H13CLB6grpqBb2l3fzAiFDTisuBNMn-sfWjMl7WKE";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("IssuedAt："+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
    }

    /**
     * 自定义claims
     */
    @Test
    public void test05(){
        //为了方便测试，我们将过期时间设置为1分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000*60;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date()) //用于设置签发时间
                .signWith(SignatureAlgorithm.HS256, "itcast") //设置签名秘钥
                .setExpiration(new Date(exp))  //设置过期时间
                .claim("roles","admin")  //自定义claims 设置角色
                .claim("logo","logo.png");  // 自定义claims 设置logo

        System.out.println(builder.compact());
    }


    /**
     * 解析jwt 有token过期时间 自定义claims
     */
    @Test
    public void test06(){
        String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE1ODI0ODI3NzYsImV4cCI6MTU4MjQ4MjgzNSwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJsb2dvLnBuZyJ9.SeIDukmV1XLHFZvT6RW9AOSfywmk4NWdj-ywlGMBknY";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();

        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("IssuedAt："+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
    }


}
