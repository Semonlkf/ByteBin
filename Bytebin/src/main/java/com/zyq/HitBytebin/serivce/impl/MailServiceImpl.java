package com.zyq.HitBytebin.serivce.impl;

import com.zyq.HitBytebin.exception.BusinessException;
import com.zyq.HitBytebin.exception.ServiceException;
import com.zyq.HitBytebin.model.pojo.Article;
import com.zyq.HitBytebin.model.pojo.User;
import com.zyq.HitBytebin.serivce.ArticleService;
import com.zyq.HitBytebin.serivce.MailService;
import com.zyq.HitBytebin.serivce.UserService;
import com.zyq.HitBytebin.util.ValidateCodeUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@CacheConfig(cacheNames = "mail")
public class MailServiceImpl implements MailService {
    private static final String REG_SUBJECT = "HitBytebin:验证您的邮箱";
    private static final String ADVICE_SUBJECT = "HitBytebin:通知";
    private static final String KEY_VALIDATE_CODE = "validateCode";
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Resource
    JavaMailSender javaMailSender;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${spring.mail.username}")
    private String SENDER_MAIL;

    @Override
    @Async
    public void sendValidateCode(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(SENDER_MAIL);
            helper.setTo(email);
            helper.setSubject(REG_SUBJECT);
            Context context = new Context();
            String code = ValidateCodeUtil.createMailCode();
            context.setVariable("code", code);
            String content = templateEngine.process("validateMail", context);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
            redisTemplate.opsForValue().set(KEY_VALIDATE_CODE + ":" + email, code, 20, TimeUnit.MINUTES);
        } catch (MessagingException e) {
            throw new BusinessException("邮件发送错误", e);
        }
    }

    @Override
    //@CacheEvict(cacheNames = "validateCode",key = "#email")
    public Boolean verify(String email, String code) throws NullPointerException {
        code = code.toUpperCase();
        String resultVal = (String) redisTemplate.opsForValue().get("validateCode:" + email);
        if (code.equals(resultVal)) {
            return true;
        }
        return false;
    }

    @Override
    @Async
    public void sendWorkDeletedAdvice(Long id, String reason) {
        Map<String, Object> map = new HashMap<>();
        Article article = articleService.getById(id);
        User user = userService.getUserDetail(article.getAuthor());
        map.put("account", user.getUsername());
        map.put("id", id);
        map.put("title", article.getTitle());
        map.put("reason", reason);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(SENDER_MAIL);
            helper.setTo(user.getEmail());
            helper.setSubject(ADVICE_SUBJECT);
            Context context = new Context();
            context.setVariables(map);
            String content = templateEngine.process("authorAdviceMail", context);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new ServiceException("邮件发送失败,该用户可能已注销", e);
        }
    }
}
