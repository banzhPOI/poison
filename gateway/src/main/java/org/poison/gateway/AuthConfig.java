package org.poison.gateway;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.poison.common.constant.GlobalConstant;
import org.poison.common.response.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 配置类
 *
 * @author kong
 */
@Configuration
public class AuthConfig {


    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")    /* 拦截全部path */
                // 开放地址
                .addExclude("/ws/**", "/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除/iam/login 用于开放登录
                    SaRouter.notMatch(GlobalConstant.LOGIN_URL, GlobalConstant.REGISTER_URL)
                            .match("/**", r -> StpUtil.checkLogin());

                    // 权限认证 -- 不同模块, 校验不同权限
//                    SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//                    SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//                    SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));

                    // 更多匹配 ...  */
                })
                // 异常处理方法：每次setAuth函数出现异常resp = {SaResponseForReactor@12823} 时进入
                .setError(e -> Response.fail(e.getMessage()))
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {
                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> {
                                SaHolder.getResponse()
                                        // 允许指定域访问跨域资源
                                        .setHeader("access-control-allow-origin", "*")
                                        // 允许所有请求方式
                                        .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                                        // 有效时间
                                        .setHeader("Access-Control-Max-Age", "3600")
                                        // 允许的header参数
                                        .setHeader("Access-Control-Allow-Headers", "*")
                                ;
                            }).back();
                });

    }
}