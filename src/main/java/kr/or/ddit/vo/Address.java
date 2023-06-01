package kr.or.ddit.vo;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Address {
	// 문자열 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다.
	@NotBlank
	private String location;

	// 문자열 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다.
	@NotBlank
	private String postCode;

}
