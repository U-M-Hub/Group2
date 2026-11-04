package com.example.demo.security.form;

import java.util.Objects;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RegisterForm {

	//ユーザー名
	@NotBlank(message = "ユーザー名を入力してください")
	@Pattern(regexp = "[a-zA-Z0-9]{1,15}",
			 message = "ユーザー名は英数字のみの1文字以上15文字以内にしてください")
	private String userName;
	
	//パスワード
	@NotBlank(message = "ユーザー名を入力してください")
	@Pattern(regexp = "^(?=(?:.*[a-zA-Z]){2})(?=(?:.*[0-9]){2})[a-zA-Z0-9]{7,20}$",
			 message = "半角英数字をそれぞれ2文字以上含む7〜20文字で入力してください")
	private String userPass;
	
	//確認用パスワード
	private String userPassCheck;
	
	@AssertTrue(message = "パスワードが一致しません")
	public boolean isCheckPassword() {
		return Objects.equals(userPass, userPassCheck);
	}
}
