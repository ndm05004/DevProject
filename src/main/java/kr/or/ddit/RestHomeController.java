package kr.or.ddit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;


/*
 * @RestController는 @Controller와 @ResponseBody를 포함하고 있는 어노테이션이다.
 */
@RestController
@Slf4j
public class RestHomeController {
	
	
	// 반환값이 객체 타입이면 JSON 타입으로 자동 변환된다.
	@RequestMapping(value="/goRestHome0301", method = RequestMethod.GET)
	public Member restHome0301() {
		log.info("restHome0301() 실행....!");
		return new Member();
	}
	
	/*
	 *  4) 컬렉션 List 타입
	 *  - JSON 객체 배열 타입의 데이터를 만들어서 반환하는 용도로 사용한다.
	 *  
	 *  
	 */
	
	// 반환값이 List 타입이면 JSON 객체 배열 타입으로 자동으로 변환된다.
	@RequestMapping(value="/goRestHome0401", method = RequestMethod.GET)
	public List<Member> restHome0401(){
		log.info("restHome0401() 실행...!");
		List<Member> list = new ArrayList<Member>();
		
		Member member = new Member();
		Member member2 = new Member();
		list.add(member);
		list.add(member2);
		return list;
	}
	
	
	/*
	 *  5) 컬렉션 Map 타입
	 *  - Map 형태의 컬렉션 자료를 JSON 객체 타입의 데이터로 만들어서 반환하는 용도로 사용한다
	 *  
	 *  2) RestController를 이용하는 방식  
	 */
	
	@RequestMapping(value="/goRestHome0501", method = RequestMethod.GET)
	public Map<String, Member> restHoem0501(){
		log.info("restHome0501() 살행...!");
		Map<String, Member> map = new HashMap<String, Member>();
		
		Member member = new Member();
		Member member2 = new Member();
		
		map.put("key1", member);
		map.put("key2", member2);
		return map;
	}
	
	
	
	
	/*
	 *	6) ResponseEntity<Void> 타입
	 *	- response 할 때 HTTP 헤더 정보와 내용을 가공하는 용도로 사용한다.
	 *  
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식  
	 */
	// 200 OK 상태코드를 전송한다.
	// Void 타입은 아무런 형태가 아닌데 제네릭 타입의 무엇인가는 태워야 해서 채우는 placeholder같은 느낌
	@RequestMapping(value="/goRestHome0601", method = RequestMethod.GET)
	public ResponseEntity<Void> restHome0601(){
		log.info("restHome0601() 실행...!");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/*
	 *  7) ResponseEntity<String> 타입
	 *  - response 할 때 HTTP 헤더 정보와 문자열 데이터를 전달하는 용도로 사용한다.
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식 
	 */
	// "SUCCESS" 메세지와 200 OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome0701", method = RequestMethod.GET)
	public ResponseEntity<String> restHome0701(){
		log.info("restHome0701() 실행...!");
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	/*
	 *  8) ResponseEntity<자바빈즈 클래스> 타입
	 *  - response 할 때 HTTP 헤더 정보와 객체 데이터를 전달하는 용도로 사용한다.
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식 
	 */
	// 객체의 JSON 타입의 데이터와 200 OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome0801", method = RequestMethod.GET)
	public ResponseEntity<Member> restHome0801(){
		log.info("restHome0801() 실행...!");
		Member member = new Member();
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	
	/*
	 * 9) ResponseEntity<List> 타입
	 * - response 할 때 HTTP 헤더 정보와 객체 배열 데이터를 전달하는 용도로 사용한다.
	 * 
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식
	 */
	
	// 객체의 JSON 객체 배열 타입의 데이터와 200 OK 상태코드를 전송한다.
	@RequestMapping(value="/goRestHome0901", method = RequestMethod.GET)
	public ResponseEntity<List<Member>> restHome0901(){
		log.info("restHome0901() 실행...!");
		List<Member> list = new ArrayList<Member>();
		
		Member member = new Member();
		Member member2 = new Member();
		
		list.add(member);
		list.add(member2);
		return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
		
	}
	
	
	
	/*
	 *  10) ResponseEntity<Map> 타입
	 *  - response 할 때 HTTP 헤더 정보와 객체 데이터를 Map 형태로 전달하는 용도로 사용한다.
	 *  
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식
	 */
	// 객체 JSON 객체 타입의 데이터와 200 ok 상태 코드를 전송한다.
	@RequestMapping(value="/goRestHome1001", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Member>> restHome1001(){
		log.info("restHome1001().... 실행");
		Map<String, Member> map = new HashMap<String, Member>();
		
		Member member = new Member();
		Member member2 = new Member();
		
		map.put("key", member);
		map.put("key2", member2);
		map.put("key3", member2);
		
		return new ResponseEntity<Map<String,Member>>(map, HttpStatus.OK);
	}
	
	

	/*
	 *  11) ResponseEntity<byte[]> 타입
	 *  - response 할 때 HTTP 헤더 정보와 바이너리 파일 데이터를 전달하는 용도로 사용한다.
	 *  - 파일을 처리하기 위해서 의존라이브러리인 commons-io를 추가한다. (pom.xml)
	 *  
	 *  > 무료/유료 이미지 다운로드 홈페이지를 사용해보면 이미지 미리보기 또는 미리보기 후 다운로드를 할 수 있는 기능이 제공된다.
	 *    이와 같은 리턴타입의 형태를 설정해서 내보내는 것과 같다.
	 *  
	 *  1) ResponseBody를 이용하는 방식
	 *  2) RestController를 이용하는 방식
	 */
	
	// 바이트 배열로 이미지 파일의 데이터를 전송한다.
	@RequestMapping(value="/goRestHome1101", method = RequestMethod.GET)
	public ResponseEntity<byte[]> restHome1101(){
		log.info("restHome1101() 실행....!");
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		try {
			in = new FileInputStream("D:/A_TeachingMaterial/02.SPRING2/park.jpg");
			headers.setContentType(MediaType.IMAGE_JPEG);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			
			try {
				in.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return entity;
	}
	
	
	
	@RequestMapping(value="/goRestHome1102", method = RequestMethod.GET)
	public ResponseEntity<byte[]> restHome1102(){
		log.info("goRestHome1102() 실행...!");
		String fileName = "data.zip";
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			in = new FileInputStream("D:/A_TeachingMaterial/02.SPRING2/"+ fileName);
			// MediaType.APPLICATION_OCTET_STREAM은 이진 파일을 위한 기본값입니다.
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" +
					new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
