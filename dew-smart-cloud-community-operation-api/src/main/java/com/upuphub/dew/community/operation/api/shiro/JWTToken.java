package com.upuphub.dew.community.operation.api.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 重写UsernamePasswordToken为JwtToken
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/11 11:44
 */
public class JWTToken extends UsernamePasswordToken {

	// JWT密钥
	private String token;
	
	public JWTToken(String token) {
		super(JWTUtil.getUin(token),JWTUtil.getPassword(token));
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}