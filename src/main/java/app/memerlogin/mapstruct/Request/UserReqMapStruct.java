package com.module.memerlogin.mapstruct.Request;


import com.mapper.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.module.memerlogin.dto.membership.Request.UserRequestDTO;
import com.module.memerlogin.entity.UserEntity;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReqMapStruct extends EntityReqMapper<UserEntity, UserRequestDTO>
{
}
