package com.module.memerlogin.mapstruct.Response;

import com.mapper.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.module.memerlogin.dto.membership.Response.UserResponseDTO;
import com.module.memerlogin.entity.UserEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResMapStruct extends EntityResMapper<UserEntity, UserResponseDTO>
{

}
