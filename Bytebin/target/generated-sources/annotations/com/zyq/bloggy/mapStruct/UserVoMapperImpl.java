package com.zyq.bloggy.mapStruct;

import com.zyq.bloggy.model.pojo.User;
import com.zyq.bloggy.model.vo.UserVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-07T20:44:49+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
@Component
public class UserVoMapperImpl implements UserVoMapper {

    @Override
    public UserVo toVO(User user) {
        if ( user == null ) {
            return null;
        }

        UserVo userVo = new UserVo();

        if ( user.getRole() != null ) {
            userVo.setRole( mapRole( user.getRole().intValue() ) );
        }
        if ( user.getId() != null ) {
            userVo.setId( user.getId().intValue() );
        }
        userVo.setUsername( user.getUsername() );
        userVo.setNickname( user.getNickname() );
        userVo.setAvatar( user.getAvatar() );
        userVo.setStatus( user.getStatus() );

        return userVo;
    }
}
