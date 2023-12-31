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
public interface RoleMapper {
	Role dtoToEntity(RoleDto roleDto);
	RoleDto entityToDto(Role role);
	
	@Mapping(target = "appUsers", expression = "java(null)")
	@Mapping(target = "roles", expression = "java(null)")
	AppGroupDto appGroupToAppGroupDto(AppGroup appGroup);

	@Mapping(target = "appGroups", expression = "java(null)")
	@Mapping(target = "roles", expression = "java(null)")
	AppUserDto appUserToAppUserDto(AppUser appUser);

}