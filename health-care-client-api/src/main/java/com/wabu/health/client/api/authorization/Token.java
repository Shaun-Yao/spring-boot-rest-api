package com.wabu.health.client.api.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Token {

	//商家id
	private String userId;
	
	//随机生成的uuid
    private String token;
}
