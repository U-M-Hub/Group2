package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http
		// HTTPリクエストに対するセキュリティ設定
		.authorizeHttpRequests(authz -> authz
		// 以下のリクエストは認証を必要としない
		.requestMatchers("/login",
						 "/images/**",
						 "/css/**").permitAll()
		//以下のリクエストはゲストのみ許可
		.requestMatchers("/register/**").anonymous()
		//以下のリクエストは管理者のみ許可
		.requestMatchers("/admin/**",
						 "/adminBookList/**",
						 "/adminBookEdit/**").hasAuthority("ROLE_ADMIN")
		//以下のリクエストは消費者のみ許可
		.requestMatchers("/customer/**").hasAuthority("ROLE_USER")
		//以下のリクエストは認証済みユーザーのみ許可
		.requestMatchers("/test").authenticated()
		// その他のリクエストは認証が必要
		.anyRequest().authenticated())
		
		// フォームベースのログイン設定
		.formLogin(form -> form
		// カスタムログインページのURLを指定
		.loginPage("/login")
		// ログイン処理のURLを指定(このURLのpostリクエストを受けたら処理をする)
		.loginProcessingUrl("/authentication")
		// ユーザー名のname属性を指定(URLリクエストのどの部分をユーザー名として扱うか指定)
		.usernameParameter("userNameInput")
		// パスワードのname属性を指定(URLリクエストのどの部分をパスワードとして扱うか指定)
		.passwordParameter("userPassInput")
		// ログイン成功時のリダイレクト先を指定
		.defaultSuccessUrl("/test")
		// ログイン失敗時のリダイレクト先を指定
		.failureUrl("/login?error"))
		
		// ログアウト設定
        .logout(logout -> logout
        // ログアウトを処理するURLを指定
        .logoutUrl("/logout")
        // ログアウト成功時のリダイレクト先を指定
        .logoutSuccessUrl("/login?logout")
        // ログアウト時にセッションを無効にする
        .invalidateHttpSession(true)
        // ログアウト時にCookieを削除する
        .deleteCookies("JSESSIONID")

		);
		
		return http.build();
	}
}
