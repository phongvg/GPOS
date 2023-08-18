package com.gg.gpos.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gg.gpos.user.dto.AppGroupDto;
import com.gg.gpos.user.dto.AppUserDto;
import com.gg.gpos.user.dto.RoleDto;
import com.gg.gpos.user.entity.AppGroup;
import com.gg.gpos.user.entity.AppUser;
import com.gg.gpos.user.entity.Role;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Mapper(componentModel = "spring")
public interface AppGroupMapper {
	AppGroup dtoToEntity(AppGroupDto appGroupDto);
	//AppGroupDto entityToDto(AppGroup appGroup);

	//@Mapping(target = "appUsers", qualifiedByName = "appUserToAppUserDto")
	//@Mapping(target = "roles", qualifiedByName = "roleToRoleDto")
	AppGroupDto entityToDto(AppGroup appGroup);
	
    //@Named("appUserToAppUserDto")
    @Mapping(target = "appGroups", expression = "java(null)")
	AppUserDto appUserToAppUserDto(AppUser appUser);
    
    //@Named("roleToRoleDto")
    @Mapping(target = "appGroups", expression = "java(null)")
    @Mapping(target = "appUsers", expression = "java(null)")
    RoleDto roleToRoleDto(Role role);
}