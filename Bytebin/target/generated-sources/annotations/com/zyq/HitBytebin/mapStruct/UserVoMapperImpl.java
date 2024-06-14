package com.zyq.HitBytebin.mapStruct;

import com.zyq.HitBytebin.model.pojo.User;
import com.zyq.HitBytebin.model.vo.UserVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T15:22:43+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
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
