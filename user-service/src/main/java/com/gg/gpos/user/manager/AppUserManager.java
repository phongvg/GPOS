package com.gg.gpos.user.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gg.gpos.common.constant.ErrorImportEnum;
import com.gg.gpos.common.constant.ProfileTypeEnum;
import com.gg.gpos.io.dto.IOAppUserDto;
import com.gg.gpos.user.dto.AppUserDto;
import com.gg.gpos.user.dto.StaffDto;
import com.gg.gpos.user.entity.AppGroup;
import com.gg.gpos.user.entity.AppUser;
import com.gg.gpos.user.entity.Role;
import com.gg.gpos.user.entity.Staff;
import com.gg.gpos.user.mapper.AppUserMapper;
import com.gg.gpos.user.repository.AppUserRepository;
import com.gg.gpos.user.repository.StaffRepository;
import com.gg.gpos.user.specification.UserSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * Generated by Speed Generator
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Slf4j
@Service
@Transactional
public class AppUserManager {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private UserSpecification userSpecification;
	@Autowired
	private StaffRepository staffRepository;
	
	public AppUserDto get(Long id) {
		return appUserRepository.findById(id).map(appUserMapper::entityToDto).orElse(null);
	}

	public AppUserDto getFull(Long id) {
		log.debug("PROCESS: GET APP_USER AND STAFF BY APP_USER_ID, ID: {}", id);
		AppUser appUser = appUserRepository.getOne(id);
		if (appUser.getProfileId() != null) {
			appUser.setStaff(staffRepository.getOne(appUser.getProfileId()));
		}
		return appUserMapper.entityToDto(appUser);
	}
	
	public boolean isExistedUsername(AppUserDto appUserDto) {
		log.debug("PROCESS: CHECK USER_NAME EXISTING, APP_USER: {}", appUserDto);
		AppUser existedUser = appUserRepository.loadUserByUsername(appUserDto.getUsername());
		if (existedUser == null) {
			return false;
		} else if (appUserDto.getId() == null) {
			return true;
		} else if (!appUserDto.getId().equals(existedUser.getId())) {
			return true;
		}
		return false;
	} 
	
	public boolean isExistedUsername(String username) {
		log.debug("PROCESS: CHECK USER_NAME EXISTING, USER_NAME: {}", username);
		AppUser existedUser = appUserRepository.loadUserByUsername(username);
		if (existedUser == null) {
			return false;
		} else {
			return true;
		}
	} 
	
	public Page<AppUserDto> gets(AppUserDto criteria) {
		log.debug("PROCESS: GETS APP_USER, CRITERIA: {}", criteria);
		Specification<AppUser> spec = Specification.where(userSpecification.filter(criteria));
		Page<AppUser> page = appUserRepository.findAll(spec, PageRequest.of(criteria.getPage(), criteria.getSize()));
		return new PageImpl<>(page.getContent().stream().map(appUserMapper::entityToDto).collect(Collectors.toList()),PageRequest.of(criteria.getPage(), criteria.getSize()), page.getTotalElements());  
	}
	
	public AppUserDto findUserById(Long id) {
		log.debug("PROCESS: GET USER BY ID, ID: {}", id);
		return Optional.ofNullable(appUserRepository.loadUserById(id)).map(appUserMapper::entityToDto).orElse(null);
	}
	
	public AppUserDto saveUser(AppUserDto appUserDto) {
		log.debug("PROCESS: SAVE APP_USER, APP_USER: {}", appUserDto);
		AppUser appUser = Optional.ofNullable(appUserDto).map(appUserMapper::dtoToEntity).orElse(null);
		StaffDto staff = appUserDto.getStaff();
		if(appUser != null) {
			appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
			AppUserDto appUserNew = Optional.ofNullable(appUserRepository.save(appUser)).map(appUserMapper::entityToDto).orElse(null);
			if(appUserNew != null) {
				appUserNew.setStaff(staff);
			}
			return appUserNew;
		}else {
			return null;
		}
		
	}
	
	public AppUserDto save(AppUserDto appUserDto) {
		log.debug("PROCESS: SAVE, APP_USER: {}", appUserDto);
		AppUser appUser = new AppUser();
		BeanUtils.copyProperties(appUserDto, appUser);
		
		Staff staff = new Staff();
		BeanUtils.copyProperties(appUserDto.getStaff(), staff);
		
		// save staff
		staff = staffRepository.save(staff);
		
		// save user
    	if (appUserDto.getGroupIds() != null && !appUserDto.getGroupIds().isEmpty()) {
    		List<AppGroup> groups = new ArrayList<>();
    		appUserDto.getGroupIds().stream().forEach(groupId -> {
    			AppGroup group = new AppGroup();
    			group.setId(groupId);
    			groups.add(group);
    		});
    		appUser.setAppGroups(groups);
    	}
    	if(appUserDto.getRoleIds()!= null && !appUserDto.getRoleIds().isEmpty()) {
    		List<Role> roles = new ArrayList<>();
    		appUserDto.getRoleIds().stream().forEach(roleId -> {
    			Role role = new Role();
    			role.setId(roleId);
    			roles.add(role);
    		});
    		appUser.setRoles(roles);    
    	}
    	appUser.setProfileId(staff.getId());
    	appUser.setProfileType(ProfileTypeEnum.STAFF.val);
		
		// Get and prepare password management-related artifacts
    	boolean passwordChanged = false;
        if (appUser.getId() == null) {
            // New user, always encrypt
            passwordChanged = true;
        } else {
            // Existing user, check password in DB
        	AppUser currentUser = appUserRepository.loadUserByUsername(appUser.getUsername());
        	String currentPassword = currentUser.getPassword();
            if (currentPassword == null) {
                passwordChanged = true;
            } else {
                if (!currentPassword.equals(appUser.getPassword())) {
                    passwordChanged = true;
                }
            }
        }
        // If password was changed (or new user), encrypt it
        if (passwordChanged) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        }
        appUser = appUserRepository.save(appUser);
        appUser.setStaff(staff);
        return Optional.ofNullable(appUserRepository.save(appUser)).map(appUserMapper::entityToDto).orElse(null);
	}
	
	public AppUserDto changePass(String username, String password) {
		log.debug("PROCESS: CHANGE PASSWORD APP_USER, USERNAME: {}, PASSWORD: {}", username, password);
		AppUser appUser = appUserRepository.findByUsernameAndAccountEnabledIsTrue(username);
		appUser.setPassword(passwordEncoder.encode(password));
		appUser.setPasswordChangedDate(LocalDate.now());
		
		return Optional.ofNullable(appUserRepository.save(appUser)).map(appUserMapper::entityToDto).orElse(null); 
	}
	
	public boolean checkChangeDate() {
		log.debug("PROCESS: CHECK CHANGE_DATE");
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		boolean check = true;
		if (currentUser != null) {
			AppUser appUser = appUserRepository.findByUsernameAndAccountEnabledIsTrueAndPasswordChangedDateIsNull(currentUser.getName());
			if(appUser != null) {
				check = false;
			}
		}
		return check;
	}
	
	public boolean checkPass(String passwordOld) {
		log.debug("PROCESS: CHECK PASSWORD, PASSWORD_OLD: {}", passwordOld);
		boolean check = false;
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser != null) {
			AppUser appUser = appUserRepository.findByUsernameAndAccountEnabledIsTrue(currentUser.getName());
			if(appUser != null && passwordEncoder.matches(passwordOld, appUser.getPassword())) {
				check = true;
			}
		}
		return check;
	}
	
	public boolean check() {
		log.debug("PROCESS: CHECK");
		boolean check = false;
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser != null) {
			AppUser appUser = appUserRepository.findByUsernameAndAccountEnabledIsTrueAndPasswordChangedDateIsNull(currentUser.getName());
			if(appUser != null) {
				check = true;
			}
		}
		return check;
	}
	
	public List<IOAppUserDto> importAppUser(List<IOAppUserDto> ioAppUserDtos) {
		log.debug("PROCESS: IMPORT APP_USERS, IOAPPUSERDTOS: {}" , ioAppUserDtos);
		List<IOAppUserDto> ioAppUserErrs = new ArrayList<>();
		if(!CollectionUtils.isEmpty(ioAppUserDtos)) {
			ioAppUserDtos.stream().forEach(item -> {
				try {
					// Loại bỏ các bản ghi bị lỗi không đọc được từ file excel
					if(StringUtils.isNotBlank(item.getError())) {
						item.setStatus(false);
						ioAppUserErrs.add(item);
					} else {
						// Kiểm tra xem user đã tồn tại chưa nếu đã tồn tại thì báo lỗi
						AppUserDto appUserDto = Optional.ofNullable(appUserRepository.findByUsernameAndAccountEnabledIsTrue(item.getUsername())).map(appUserMapper::entityToDto).orElse(null);
						if(appUserDto != null) {
							item.setStatus(false);
							item.setError(ErrorImportEnum.ERROR_USERNAME_IS_EXIST.val);
							ioAppUserErrs.add(item);
						}else {
							Staff staff = new Staff();
							staff.setEmail(item.getEmail());
							staff.setFullname(item.getFullName());
							Staff staffSaved = staffRepository.save(staff);
							
							AppUser appUser = new AppUser();
							appUser.setUsername(item.getUsername());
							appUser.setProfileId(staff.getId());
					    	appUser.setPasswordChangedDate(LocalDate.now());
					    	appUser.setProfileType(ProfileTypeEnum.STAFF.val);
					    	appUser.setPassword(passwordEncoder.encode(item.getPassword()));
					    	appUser.setAccountEnabled(true);
					        appUser.setStaff(staffSaved);
					        appUserRepository.save(appUser);
						}
					}
				} catch (Exception e) {
					log.error("ERROR IMPORT APP_USER: {}", e);
					item.setStatus(false);
					item.setError(e.toString());
					ioAppUserErrs.add(item);
				}
			});
		}
		return ioAppUserErrs;
	}
	
	
}