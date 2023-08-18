package com.gg.gpos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gg.gpos.user.manager.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = getUserDetailsService();
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/login").permitAll()
            	.antMatchers("/v1/**").permitAll()
//			System Management
            	.antMatchers("/group/list**").hasAnyRole("ADMIN","GROUP_VIEW","GROUP_ALL")
            	.antMatchers("/group/form**").hasAnyRole("ADMIN","GROUP_EDIT","GROUP_ADD","GROUP_ALL")
            	.antMatchers("/user/list**").hasAnyRole("ADMIN","ACC_VIEW","ACC_ALL")
            	.antMatchers("/user**").hasAnyRole("ADMIN","ACC_EDIT","ACC_ADD","ACC_ALL")
            	.antMatchers("/systemParameter/list**").hasAnyRole("ADMIN","SYS_PARAM_VIEW","SYS_ALL")
            	.antMatchers("/systemParameter/edit**").hasAnyRole("ADMIN","SYS_PARAM_EDIT","SYS_ALL")
//			Reference Management            	
            	.antMatchers("/ref/category/list**").hasAnyRole("ADMIN","CATEGORY_VIEW","CATEGORY_ALL")
            	.antMatchers("/category/sync-from-rk7**").hasAnyRole("ADMIN","CATEGORY_SYNC","CATEGORY_ALL","SYNC_DATA")
            	.antMatchers("/ref/currency/list**").hasAnyRole("ADMIN","CURRENCY_VIEW","CURRENCY_ALL")
            	.antMatchers("/currency/sync-from-rk7**").hasAnyRole("ADMIN","CURRENCY_SYNC","SYNC_ALL","CURRENCY_ALL")
            	.antMatchers("/ref/foodItem/list**").hasAnyRole("ADMIN","FOOD_VIEW","FOOD_ALL")
            	.antMatchers("/foodItem/sync-from-rk7**").hasAnyRole("ADMIN","FOOD_SYNC","SYNC_DATA","FOOD_ALL")
            	.antMatchers("/ref/modiGroup/list**").hasAnyRole("ADMIN","MODIGROUP_VIEW","MODIGROUP_ALL")
            	.antMatchers("/modiGroup/sync-from-rk7**").hasAnyRole("ADMIN","MODIGROUP_SYNC","SYNC_DATA","MODIGROUP_ALL")
            	.antMatchers("/ref/modiScheme/list**").hasAnyRole("ADMIN","MODISCHEME_VIEW","MODISCHEME_ALL")
            	.antMatchers("/modiScheme/sync-from-rk7**").hasAnyRole("ADMIN","MODISCHEME_SYNC","SYNC_DATA","MODISCHEME_ALL")
            	.antMatchers("/ref/modiSchemeDetail/list**").hasAnyRole("ADMIN","MODIDETAIL_VIEW","MODIDETAIL_ALL")
            	.antMatchers("/modiSchemeDetail/sync-from-rk7**").hasAnyRole("ADMIN","MODIDETAIL_SYNC","SYNC_DATA","MODIDETAIL_ALL")
            	.antMatchers("/ref/modifier/list**").hasAnyRole("ADMIN","MODIFIER_VIEW","MODIFIER_ALL")   	
            	.antMatchers("/modifier/sync-from-rk7**").hasAnyRole("ADMIN","MODIFIER_SYNC","SYNC_DATA","MODIFIER_ALL")
            	.antMatchers("/orderCategory/list**").hasAnyRole("ADMIN","ORDERCATE_VIEW","ORDERCATE_ALL")
            	.antMatchers("/orderCategory/sync-from-rk7**").hasAnyRole("ADMIN","ORDERCATE_SYNC","SYNC_DATA","ORDERCATE_ALL")
            	.antMatchers("/ref/orderType/list**").hasAnyRole("ADMIN","ORDERTYPE_VIEW","ORDERTYPE_ALL")
            	.antMatchers("/orderType/sync-from-rk7**").hasAnyRole("ADMIN","ORDERTYPE_SYNC","SYNC_DATA","ORDERTYPE_ALL")
            	.antMatchers("/ref/orderVoid/list**").hasAnyRole("ADMIN","ORDERVOID_VIEW","ORDERVOID_ALL")
            	.antMatchers("/orderVoid/sync-from-rk7**").hasAnyRole("ADMIN","ORDERVOID_SYNC","SYNC_DATA","ORDERVOID_ALL")
            	.antMatchers("/ref/currencyRate/list**").hasAnyRole("ADMIN","CURATE_VIEW","CURATE_ALL")     
            	.antMatchers("/currencyRate/sync-from-rk7**").hasAnyRole("ADMIN","CURATE_SYNC","SYNC_DATA","CURATE_ALL")
            	.antMatchers("/ref/hallPlan/list**").hasAnyRole("ADMIN","HALL_VIEW","HALL_ALL")
            	.antMatchers("/hallPlan/sync-from-rk7**").hasAnyRole("ADMIN","HALL_SYNC","SYNC_DATA","HALL_ALL")     	
            	.antMatchers("/ref/tableKitchen/list**").hasAnyRole("ADMIN","TABLEKITCHEN_VIEW","TABLEKITCHEN_ALL")
            	.antMatchers("/tableKitchen/sync-from-rk7**").hasAnyRole("ADMIN","TABLEKITCHEN_SYNC","SYNC_DATA","TABLEKITCHEN_ALL")
            	.antMatchers("/ref/guestType/list**").hasAnyRole("ADMIN","GUESTTYPE_VIEW","GUESTTYPE_ALL")
            	.antMatchers("/guestType/sync-from-rk7**").hasAnyRole("ADMIN","GUESTTYPE_SYNC","SYNC_DATA","GUESTTYPE_ALL")
            	.antMatchers("/ref/employee/list**").hasAnyRole("ADMIN","EMPLOYEEMENU_VIEW","EMPLOYEEMENU_ALL")
            	.antMatchers("/employee/sync-from-rk7**").hasAnyRole("ADMIN","EMPLOYEEMENU_SYNC","SYNC_DATA","EMPLOYEEMENU_ALL")            	
            	.antMatchers("/ref/menuType/list**").hasAnyRole("ADMIN","GROUPTYPE_VIEW","GROUPTYPE_ALL")
            	.antMatchers("/ref/menuType/form**").hasAnyRole("ADMIN","GROUPTYPE_EDIT","GROUPTYPE_ADD","GROUPTYPE_ALL")
            	.antMatchers("/ref/feature/list**").hasAnyRole("ADMIN","FEATURE_VIEW","FEATURE_ALL")
            	.antMatchers("/ref/feature/form**").hasAnyRole("ADMIN","FEATURE_EDIT","FEATURE_ADD","FEATURE_ALL")
 //Catalog Management           	
            	.antMatchers("/cag/so/list**").hasAnyRole("ADMIN","MENU_VIEW","MENU_ALL")
            	.antMatchers("/cag/so/**").hasAnyRole("ADMIN","MENU_EDIT","MENU_ADD","MENU_ALL")
            	.antMatchers("/co/catalog-list**").hasAnyRole("ADMIN","CO_VIEW","CO_ALL")
            	.antMatchers("/co/**").hasAnyRole("ADMIN","CO_EDIT","CO_ADD","RES_CONFIG_VIEW","CO_ALL")
            	.antMatchers("/coFoodItem/**").hasAnyRole("ADMIN","CO_EDIT","CO_ADD","RES_CONFIG_VIEW","CO_ALL")
            	.antMatchers("/kds/catalog-list/**").hasAnyRole("ADMIN","KDS_VIEW","KDS_ALL")
            	.antMatchers("/kds/**").hasAnyRole("ADMIN","KDS_EDIT","KDS_ADD","RES_CONFIG_VIEW","KDS_ALL")
            	.antMatchers("/printGroup/**").hasAnyRole("ADMIN","KDS_EDIT","KDS_ADD","RES_CONFIG_VIEW","KDS_VIEW")
            	.antMatchers("/param/catalog-list**").hasAnyRole("ADMIN","PARAM_VIEW","PARAM_ALL")
            	.antMatchers("/param/catalog-form**").hasAnyRole("ADMIN","PARAM_EDIT","PARAM_ALL")
            	.antMatchers("/param/sync-to-restaurant**").hasAnyRole("ADMIN","PARAM_SYNC","PARAM_ALL")
//Restaurant Managerment            	
            	.antMatchers("/brand/**").hasAnyRole("ADMIN", "BRAND_VIEW","RES_ALL")
            	.antMatchers("/restaurant/list**").hasAnyRole("ADMIN", "RES_TAG_VIEW","RES_ALL")
            	.antMatchers("/restaurant/form**").hasAnyRole("ADMIN", "RES_VIEW","RES_ALL")
            	.antMatchers("/res/so/menu**").hasAnyRole("ADMIN","RES_CONFIG_VIEW","RES_CONFIG_EDIT","RES_ALL")            	
            	.antMatchers("/area/list**").hasAnyRole("ADMIN", "RES_CONFIG_VIEW","RES_ALL")
            	.antMatchers("/kitchen/list**").hasAnyRole("ADMIN", "RES_CONFIG_VIEW","RES_ALL")
            	.antMatchers("/area/form**").hasAnyRole("ADMIN", "RES_CONFIG_EDIT","RES_ALL")
            	.antMatchers("/kitchen/form**").hasAnyRole("ADMIN", "RES_CONFIG_EDIT","RES_ALL")
            	.antMatchers("/printGroup/form**").hasAnyRole("ADMIN", "RES_CONFIG_EDIT","RES_ALL")   
            	.antMatchers("/advanced/list**").hasAnyRole("ADMIN", "RES_CONFIG_EDIT","RES_ALL")   
            	.antMatchers("/reportMenu/list**").hasAnyRole("ADMIN", "RES_CONFIG_EDIT","RES_ALL")   
            	.anyRequest().authenticated()
            	.and()
            .formLogin()
            	.successHandler(authenticationSuccessHandler)
            	.failureHandler(authenticationFailureHandler)
            	//.loginPage("/login").failureUrl("/login?error=true")
            	.loginPage("/login")
            	.usernameParameter("username")
            	.passwordParameter("password")
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/forbidden");
        
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        	.ignoring()
        	.antMatchers("/resources/**")
        	.antMatchers("/themes/**");
    }

}
