package kr.or.ddit.vo;

import java.util.Date;

import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Card {
	// 문자열 null이 아니고 trim한 길이가 0보다 크다는 것을 검사한다.
	@NotBlank
	private String no;
	
	// 미래 날짜인지를 검사
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validMonth;
}
