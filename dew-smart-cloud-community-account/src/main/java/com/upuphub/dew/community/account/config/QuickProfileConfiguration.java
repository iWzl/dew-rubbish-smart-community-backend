package com.upuphub.dew.community.account.config;

import com.upuphub.profile.annotation.ProfileServiceScan;
import com.upuphub.profile.component.ProfileParametersManager;
import com.upuphub.profile.reflect.ProfileMethodHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户QuickProfile的信息
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 20:56
 */
@ProfileServiceScan(basePackages = "com.upuphub.dew.community.account.service")
@Configuration
public class QuickProfileConfiguration {

    @Bean
    public ProfileParametersManager buildProfileParametersManager(ProfileMethodHandler profileMethodHandler,
                                                                  @Value("${profiles.config.xmlPath}") String xmlPath){
        ProfileParametersManager profileParametersManager = new ProfileParametersManager(xmlPath);
        profileParametersManager.setProfileMethodHandler(profileMethodHandler);
        return profileParametersManager;
    }
}