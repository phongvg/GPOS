package com.gg.gpos.user.mapper;

import com.gg.gpos.user.dto.AppGroupDto;
import com.gg.gpos.user.dto.AppUserDto;
import com.gg.gpos.user.dto.RoleDto;
import com.gg.gpos.user.dto.StaffDto;
import com.gg.gpos.user.entity.AppGroup;
import com.gg.gpos.user.entity.AppUser;
import com.gg.gpos.user.entity.Role;
import com.gg.gpos.user.entity.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-01T17:39:23+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class AppGroupMapperImpl implements AppGroupMapper {

    @Override
    public AppGroup dtoToEntity(AppGroupDto appGroupDto) {
        if ( appGroupDto == null ) {
            return null;
        }

        AppGroup appGroup = new AppGroup();

        appGroup.setId( appGroupDto.getId() );
        appGroup.setName( appGroupDto.getName() );
        appGroup.setDescription( appGroupDto.getDescription() );
        appGroup.setEnabled( appGroupDto.isEnabled() );
        appGroup.setSystemGroup( appGroupDto.getSystemGroup() );
        appGroup.setAppUsers( appUserDtoListToAppUserList( appGroupDto.getAppUsers() ) );
        appGroup.setRoles( roleDtoListToRoleList( appGroupDto.getRoles() ) );

        return appGroup;
    }

    @Override
    public AppGroupDto entityToDto(AppGroup appGroup) {
        if ( appGroup == null ) {
            return null;
        }

        AppGroupDto appGroupDto = new AppGroupDto();

        appGroupDto.setId( appGroup.getId() );
        appGroupDto.setName( appGroup.getName() );
        appGroupDto.setDescription( appGroup.getDescription() );
        appGroupDto.setEnabled( appGroup.isEnabled() );
        appGroupDto.setSystemGroup( appGroup.getSystemGroup() );
        appGroupDto.setAppUsers( appUserListToAppUserDtoList( appGroup.getAppUsers() ) );
        appGroupDto.setRoles( roleListToRoleDtoList( appGroup.getRoles() ) );

        return appGroupDto;
    }

    @Override
    public AppUserDto appUserToAppUserDto(AppUser appUser) {
        if ( appUser == null ) {
            return null;
        }

        AppUserDto appUserDto = new AppUserDto();

        appUserDto.setId( appUser.getId() );
        appUserDto.setAccountExpired( appUser.isAccountExpired() );
        appUserDto.setAccountLocked( appUser.isAccountLocked() );
        appUserDto.setCredentialsExpired( appUser.isCredentialsExpired() );
        appUserDto.setAccountEnabled( appUser.isAccountEnabled() );
        appUserDto.setUsername( appUser.getUsername() );
        appUserDto.setPassword( appUser.getPassword() );
        appUserDto.setVersion( appUser.getVersion() );
        appUserDto.setUserLevel( appUser.getUserLevel() );
        appUserDto.setCreatedBy( appUser.getCreatedBy() );
        appUserDto.setCreatedDate( appUser.getCreatedDate() );
        appUserDto.setModifiedBy( appUser.getModifiedBy() );
        appUserDto.setModifiedDate( appUser.getModifiedDate() );
        appUserDto.setPasswordChangedDate( appUser.getPasswordChangedDate() );
        appUserDto.setProfileId( appUser.getProfileId() );
        appUserDto.setProfileType( appUser.getProfileType() );
        appUserDto.setRoles( roleListToRoleDtoList( appUser.getRoles() ) );
        appUserDto.setStaff( staffToStaffDto( appUser.getStaff() ) );

        appUserDto.setAppGroups( null );

        return appUserDto;
    }

    @Override
    public RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );
        roleDto.setDescription( role.getDescription() );
        roleDto.setType( role.getType() );

        roleDto.setAppUsers( null );
        roleDto.setAppGroups( null );

        return roleDto;
    }

    protected List<AppGroup> appGroupDtoListToAppGroupList(List<AppGroupDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AppGroup> list1 = new ArrayList<AppGroup>( list.size() );
        for ( AppGroupDto appGroupDto : list ) {
            list1.add( dtoToEntity( appGroupDto ) );
        }

        return list1;
    }

    protected List<AppUser> appUserDtoListToAppUserList(List<AppUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AppUser> list1 = new ArrayList<AppUser>( list.size() );
        for ( AppUserDto appUserDto : list ) {
            list1.add( appUserDtoToAppUser( appUserDto ) );
        }

        return list1;
    }

    protected Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setName( roleDto.getName() );
        role.setDescription( roleDto.getDescription() );
        role.setType( roleDto.getType() );
        role.setAppGroups( appGroupDtoListToAppGroupList( roleDto.getAppGroups() ) );
        role.setAppUsers( appUserDtoListToAppUserList( roleDto.getAppUsers() ) );

        return role;
    }

    protected List<Role> roleDtoListToRoleList(List<RoleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( RoleDto roleDto : list ) {
            list1.add( roleDtoToRole( roleDto ) );
        }

        return list1;
    }

    protected Staff staffDtoToStaff(StaffDto staffDto) {
        if ( staffDto == null ) {
            return null;
        }

        Staff staff = new Staff();

        staff.setId( staffDto.getId() );
        staff.setFullname( staffDto.getFullname() );
        staff.setPosition( staffDto.getPosition() );
        staff.setEmail( staffDto.getEmail() );
        staff.setPhone( staffDto.getPhone() );
        staff.setStatus( staffDto.isStatus() );

        return staff;
    }

    protected AppUser appUserDtoToAppUser(AppUserDto appUserDto) {
        if ( appUserDto == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        appUser.setId( appUserDto.getId() );
        appUser.setAccountExpired( appUserDto.isAccountExpired() );
        appUser.setAccountLocked( appUserDto.isAccountLocked() );
        appUser.setCredentialsExpired( appUserDto.isCredentialsExpired() );
        appUser.setAccountEnabled( appUserDto.isAccountEnabled() );
        appUser.setUsername( appUserDto.getUsername() );
        appUser.setPassword( appUserDto.getPassword() );
        appUser.setVersion( appUserDto.getVersion() );
        appUser.setUserLevel( appUserDto.getUserLevel() );
        appUser.setCreatedBy( appUserDto.getCreatedBy() );
        appUser.setCreatedDate( appUserDto.getCreatedDate() );
        appUser.setModifiedBy( appUserDto.getModifiedBy() );
        appUser.setModifiedDate( appUserDto.getModifiedDate() );
        appUser.setPasswordChangedDate( appUserDto.getPasswordChangedDate() );
        appUser.setProfileId( appUserDto.getProfileId() );
        appUser.setProfileType( appUserDto.getProfileType() );
        appUser.setAppGroups( appGroupDtoListToAppGroupList( appUserDto.getAppGroups() ) );
        appUser.setRoles( roleDtoListToRoleList( appUserDto.getRoles() ) );
        appUser.setStaff( staffDtoToStaff( appUserDto.getStaff() ) );

        return appUser;
    }

    protected List<AppUserDto> appUserListToAppUserDtoList(List<AppUser> list) {
        if ( list == null ) {
            return null;
        }

        List<AppUserDto> list1 = new ArrayList<AppUserDto>( list.size() );
        for ( AppUser appUser : list ) {
            list1.add( appUserToAppUserDto( appUser ) );
        }

        return list1;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }

    protected StaffDto staffToStaffDto(Staff staff) {
        if ( staff == null ) {
            return null;
        }

        StaffDto staffDto = new StaffDto();

        staffDto.setId( staff.getId() );
        staffDto.setFullname( staff.getFullname() );
        staffDto.setPosition( staff.getPosition() );
        staffDto.setEmail( staff.getEmail() );
        staffDto.setPhone( staff.getPhone() );
        staffDto.setStatus( staff.isStatus() );

        return staffDto;
    }
}
