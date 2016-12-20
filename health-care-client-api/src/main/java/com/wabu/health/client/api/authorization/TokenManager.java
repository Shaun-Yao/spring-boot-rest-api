package com.wabu.health.client.api.authorization;

public interface TokenManager {

	/**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    public Token create(String userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    public boolean check(Token token);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    public Token get(String authentication);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void delete(String userId);
}
