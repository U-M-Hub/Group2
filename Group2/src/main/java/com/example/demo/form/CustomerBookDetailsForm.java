package com.example.demo.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CustomerBookDetailsForm {

	/** 注文数 															*/
	/* @NotNull：														*/
	/*   文字列が null であることを禁止するもの							*/
	/* @Min：															*/
	/*   指定した数値以上であることをチェック							*/
	/* ※value = 1：1以上がOK											*/
	@NotNull(message = "必須入力です")
	@Min(value = 1, message = "1以上の整数を入力してください")
	private Integer bookCount = 1;

}
