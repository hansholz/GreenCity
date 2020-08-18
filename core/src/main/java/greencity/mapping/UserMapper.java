package greencity.mapping;

import greencity.dto.user.UserForListDto;
import greencity.entity.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractConverter<UserForListDto, User> {
    @Override
    protected User convert(UserForListDto userForListDto) {
        return User.builder()
            .id(userForListDto.getId())
            .name(userForListDto.getName())
            .email(userForListDto.getEmail())
            .userCredo(userForListDto.getUserCredo())
            .userStatus(userForListDto.getUserStatus())
            .build();
    }
}
