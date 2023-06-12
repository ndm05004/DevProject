package kr.or.ddit.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// @Component는 스프링 빈으로 등록하기 위한 어노테이션
// @Aspect는 어노테이션을 붙여 이 클래스가 Aspect를 나타내는 클래스 라는 걸 명시
// AOPController 클래스 빈 등록 시, aOPController로 할지 aoPController로 할지가 명확하지 않아 aopController라는 이름을 명시해줌

@Component("aopController")
@Aspect
@Slf4j
public class AOPController {
	
	/*
	 * 14장 AOP
	 * 
	 * 1. AOP 설명
	 * 		
	 * 		[AOP란? 예시]
	 * 		406호 반장 정수학생이 신입으로 프로젝트를 진행하고 있습니다.
	 * 		그러던 어느날, 성희 팀장님을 통해서 '반장님, 지금 개발중인 서비스 처리 속도좀 로그로 남겨주세요!'라는 부탁을 받는다.
	 * 		반장 정수학생은 부탁받은 요구사항을 이행하기 위해서 본인이 만들고 있는 서비스 로직에서 처리 속도를 찍어볼 메소드를 개발해 처리 속도가 잘 찍히는걸 확인합니다.
	 * 		기분이 좋은 정수학생은 성희학생에게 달려갑니다. '기능구현 완료했습니다!'라고 컨펌을 받습니다.
	 * 		성희 팀장님은 아주 긍정적인 검토안을 정수학생한테 전달하면서 우리 서비스 전체에도 각 처리속도를 찍어주세요라고 다시 부탁합니다.
	 * 		정수학생은 본인이 만들어낸 메소드를 각 기능별 서비스 로직에 하나하나씩 약 2만개쯤 넣을쯤에 의문을 갖습니다.
	 * 
	 * 			* 서비스 로직에서 제일 중요한 로직은 본래의 기능이 제일 중요하고 지금 내가 작성하는 로직은 옵션(부가기능)이 추가되는게 아닐려나?
	 * 			
	 * 			여기서 시간을 측정하고 권한을 체크하는 등의 기능은 옵션과 같은 부가기능의 일종으로 인프라 로직이라고 하는데, 이 인프라 로직은 애플리케이션 전 영역에서
	 * 			나타날 수 있고, 중복코드를 만들어 내 개발의 효율성을 저하시키고 유지보수가 힘들어질 수 있다.
	 * 
	 * 			이러한 인프라 로직은 아래처럼 하나의 관심사를 가질 수 있는데 이런 관심사들의 중복이 횡단으로 나타나는데, 이것을 가르켜 '횡단 관심사(Cross-Cutting Concern)'라고 함.
	 * 				
	 * 				______________________________________________________________________________
	 * 					
	 * 						[처리속도측정]		[처리속도측정]		[처리속도측정]		[처리속도측정]
	 * 				______________________________________________________________________________
	 * 						[비지니스로직]      [비지니스로직]     [비지니스로직]     [비지니스로직]      
	 * 						[처리내용로깅]	     [처리내용로깅]		[처리내용로깅]		[처리내용로깅]
	 * 			
	 * 				이런 횡단 관심사를 통해서 프로그래밍 하는 것이 AOP라고 한다.
	 * 				
	 * 				** 간단하게 맛보기
	 * 				- Aspect: AOP의 단위가 되는 횡단 관심사
	 * 				-	횡단 관심사(Cross-Cutting Concern) : 핵심(Core) 비지니스 로직(메소드 실행 시작 시간 출력, 메소드 처리 후 시간 출력 등)과 다소 거리가 있지만,
	 * 													  분리해서 한 곳으로 모으는 것을 의미
	 * 				- 	횡단 관심사 분리(Separation Of Cross-Cutting Concern) : 횡단 관심사에 해당하는 부분(메소드 실행 시작 시간 출력, 메소드 처리 후 시간 출력 등)을
	 * 																	    분리해서 한 곳으로 모으는 것을 의미
	 * 				-	@Component : @Aspect와 짝꿍. component-scan시 "저 여기 있어요~ 여기 봐주세요!" 라는 의미
	 * 				-	JoinPoint : 어느바이스(부가기능)가 적용될 수 있는 위치
	 * 				-	Advise : 어드바이스(메소드 실행 시작 전, 메소드 실행 후 등)을 언제 (삼겹살을 굽기 전, 삼겹살을 다 먹은 후 등) 사용할지 정의
	 * 					* 언제?
	 * 					> Before : 조인포인트 전에 실행(삼겹살을 굽기 전에)
	 * 					> After : 조인 포인트에서 처리가 완료된 후 실행(삼겹살을 굽고 먹은 후 실행)
	 * 					> After Returning : 조인 포인트가 정상적으로 종료 후 실행
	 * 					> After Throwing : 조인 포인트에서 예외 발생 시 실행. 예외가 발생안되면 실행 안함
	 * 					> Around : 조인 포인트 전후에 실행(삼겹살을 굽기 전과 먹은 후 실행)
	 * 	
	 * 	AOP(관점 지향 프로그래밍 Aspect Oriented Prigramming)
	 * 
	 * 		1-1) 관점 지향 프로그래밍
	 * 		소스 코드의 여기저기에 흩어져 있는 횡단 관심사를 중심으로 설계와 구현을 하는 프로그래밍 기법이다.
	 * 		간단하게 설명하면 관점지향 프로그래밍은 횡단 관심사의 분리를 실현하는 방법이다.
	 * 		
	 * 		- 횡단 관심사
	 * 		> 핵심 비지니스 로직과 다소 거리가 있지만, 여러 모듈에서 공통적이고 반복적인 처리를 요구하는 내용이다.
	 * 		
	 * 		- 횡단 관심사와 분리
	 * 		> 어플리케이션을 개발할 때 횡단 관심사에 해당하는 부분을 분리해서 한 곳으로 모으는 것을 의미한다.
	 * 		
	 * 		1-2) AOP 개발 순서
	 * 		- (1) 핵심 비지니스 로직에만 근거해서 코드를 작성한다.
	 * 		- (2) 주변로직에 해당하는 관심사들을 분리해서 따로 작성한다.
	 * 		- (3) 핵심 비지니스 로직 대상 객체에 어떤 관심사들을 결합할 것인지를 설정한다.
	 * 
	 * 		1-3) AOP 사용 예(로.보.트.에)
	 * 		- 로깅
	 * 		- 보안적용
	 * 		- 트랜잭션 처리
	 * 		- 예외처리
	 * 			** AOP의 사용된 예에서 우리는 로깅을 이용
	 * 			 	기본적인 보안적용은 스프링 시큐리티에서 사용하고, 트랜잭션 관리는 @Transaction 어노테이션을 활용한 트랜잭션에서 사용하고,
	 * 				예외처리는 xml 설정을 통한 예외 처리에서 사용한다.
	 * 
	 *  	1-4) AOP 관련 용어
	 *  		
	 *  		- 애즈팩트(aspect)
	 *  			> AOP의 단위가 되는 횡단 관심사에 해당한다.
	 *  		- 조인포인트(joinPoint)
	 *  			> 횡단 관심사가 실행될 지점이나 시점(메소드 실행이나 예외 발생 등)을 말한다.
	 *  			> 어디에 적용할 것인지 결정, 메소드? 객체생성시? 필드접근시? 등등
	 *  		- 어드바이스(Advise)
	 *  			> 특정 조인 포인트에서 실행되는 코드로, 횡단 관심사를 실제로 구현해서 처리하는 부분이다.
	 *  			> 어떤 부가 기능을 구현할 것인지를 결정(Before, After, AfterReturnning, AfterThrowing, Around)
	 *  		- 포인트컷(Pointcut)
	 *  			> 수 많은 조인 포인트 중에서 실제로 어드바이스를 적용할 곳을 선별하기 위한 표현식(expression)을 말한다.
	 *  			> 어드바이스가 적용될 지점
	 *  		- 위빙(Weaving)
	 *  			> 어플리케이션 코드의 적절한 지점에 Aspect를 적용하는 것을 말한다.
	 *  		- 타겟(Target)
	 *  			> AOP 처리에 의해서 처리 흐름에 변화가 생긴 객체를 말한다.
	 *  			> 어떤 대상에 대해서 부가 기능을 설정할 것인지를 결정
	 *  
	 *  	1-5) 스프링 지원 어드바이스 유형(부가기능:Advise)
	 *  		
	 *  		- Before
	 *  			> 조인 포인트 전에 실행된다.
	 *  			> 예외가 발생하는 경우만 제외하고 항상 실행된다.
	 *  		- After Returning
	 *  			> 조인 포인트가 정상적으로 종료한 후에 실행된다.
	 *  			> 예외가 발생하면 실행되지 않는다.
	 *  		- After Throwing
	 *  			> 조인 포인트에서 예외가 발생했을 때 실행된다.
	 *  			> 예외가 발생하지 않고 정상적으로 종료하면 실행되지 않는다.
	 *  		- After
	 *  			> 조인 포인트에서 처리가 완료된 후 실행된다.
	 *  			> 예외가 발생하지 않고 정상적으로 종료하면 실행되지 않는다.
	 *  		- Around
	 *  			> 조인 포인트 전후에 실행된다.
	 *  			
	 *  	1-6) AOP의 기능을 활용하기 위한 설정
	 *  		
	 *  		(1) 의존 관계 등록
	 *  		- aspectjrt
	 *  		- aspectjweaver
	 *  		
	 *  		(2) 스프링 AOP 설정
	 *  		- root-context.xml 설정
	 *  		: 이곳에서는 AOP를 활성화하기 위한 태그를 작성한다.
	 *  
	 *  2. 포인트 컷 표현식
	 *  - execution 지시자에 대해서 알아본다.
	 *  
	 * 		2-1) execution 지시자의 표현 방식
	 * 		- execution 지시자를 활용해 포인트 컷을 표현한 것
	 * 			
	 * 			포인트 컷 표현 요소
	 * 				예) execution(Board kr.or.ddit.service.IBoardService.BoardService*.read*(..))
	 * 				
	 * 				표현요소			|		설명
	 * 			___________________________________________
	 * 				execution		|	지시자
	 * 				Board			|	반환값
	 * 			kr.or.ddit.service	|	패키지
	 * 				BoardService*	| 	클래스(타입)
	 * 				read*			|	메서드
	 * 				(..)			|	인수, 파라미터
	 * 			_____________________________________________
	 * 
	 * 		2-2) 포인트 컷 표현식에 사용되는 와일드 카드
	 * 			
	 * 			와일드카드		|		설명
	 * 		___________________________________________________
	 * 				*		|	임의의 패키지 1개 계층을 의미하거나 임의의 인수 1개를 의미한다.
	 * 				..		|	임의의 패키지 0개 이상 계층을 의미하거나 임의의 인수 0개 이상을 의미한다.
	 * 				+		|	클래스 명 뒤에 붙여쓰며, 해당 클래스와 해당 클래스의 서브 클래스, 혹은 구현 클래스 모두를 의미한다.
	 * 		
	 * 		2-3) 포인트 컷 표현식을 적용한 모습
	 * 		
	 * 			@Before("execution(* kr.or.ddit.service.IBoardService.BoardService*.*(..))")
	 * 		 	public void startLog(JoinPoint jp){
	 * 				log.info("startLog : " + jp.getSigatrue());		
	 * 			}
	 *  
	 *  		
	 *  3. Before 어드바이스
	 *   - 조인 포인트 전에 실행된다. 예외가 발생하는 경우만 제외하고 항상 실행된다.
	 *   	
	 *   	AOP는 서비스내의 비지니스 로직을 가로채서 컨트롤을 하는데, 이때 프록시라는 녀석을 이용하여 해당 기능을 실행하기 전 후 또는 예외 시에
	 *   	로그를 출력하는 등의 이벤트를 실행한다.
	 *   	
	 *   	[AOP 프록시]
	 * 		클라이언트가 요청한 해당 요청을 서비스 로직으로 전달되기 전 해당 비지니스 로직 부를 실제 대상인것처럼 위장해서 클라이언트의 요청을 받는다.
	 * 		AOP에서 프록시 개념은 대리자이다.
	 * 		우리는 서비스에서 이뤄지는 전역의 비지니스 로직들에서 관심사별로 위빙을 지정해 해당 서비스의 비지니스 로직이 실행될 때,
	 * 		인터페이스 기반의 포록시를 이용하여 설정된 횡단 관심사들을 실행한다.
	 * 		
	 * 			*** 인터페이스 기반의 프록시가 잘 설정되어 있는지 확인하기 위해서 CrudBoardController에서
	 * 				프록시 여부를 확인하도록 한다.(인터페이스/클래스 기반의 프록시 여부가 true/false로 출력 및 확인)
	 */		
	
	@Before("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void startLog(JoinPoint jp) {
		log.info("[@Before] startLog");
		// getSignature() : 어떤 클래스의 어떤 메소드가 실행되었는지를 보여준다.
		//					파라미터 타입은 무엇인지  보여준다.
		
		log.info("[@Before] startLog : " + jp.getSignature());
		// getArgs() : 전달된 파라미터 정보를 보여준다.
		//			예) [BoardVO [boardVO.No=127, title=개똥이]
		
		log.info("[@Before] startLog : " + Arrays.toString(jp.getArgs()));
		
		// 8. 메서드 정보 획득 시 사용
		// 프록시가 입혀지기 전의 원본 대상의 객체를 가져온다.
		Object targetObject = jp.getTarget();
		log.info("targetObject : " + targetObject);
		
		// 프록시를 가져온다.
		Object thisObject = jp.getThis();
		log.info("thisObject : " + thisObject);
		
		// 인수를 가져온다.
		Object[] args = jp.getArgs();
		log.info("args.length : "+ args.length);
		
		
	}
	/*
	 * 4. After Returning 어드바이스
	 * - 조인 포인트가 정상적으로 종료한 후에 실행된다. 예외가 발생하면 실행되지 않는다.
	 * 
	 */
	
	@AfterReturning("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void logReturning(JoinPoint jp) {
		log.info("[@aftterReturning] logReturning");
		log.info("[@aftterReturning] logReturning : " + jp.getSignature());
	}
	
	/*
	 * 5. AfterThrowing 어드바이스
	 * - 조인 포인트에서 예외가 발생했을 때 실행된다. 예외가 발생하지 않고 정상적으로 종료되면 실행되지 않는다.
	 * ** 예시) crud board에서 delete부를 에러로 실행해봅니다 (쿼리 no = 2, no 2 = )으로 변경해서 진행 
	 */
	
	@AfterThrowing(pointcut = "execution(* kr.or.ddit.service.IBoardService.*(..))", throwing ="e")
	public void logException(JoinPoint jp, Exception e) {
		log.info("[@aFftterThrowing] logException");
		log.info("[@aFftterThrowing] logException : " + jp.getSignature());
		log.info("[@aFftterThrowing] logException : " + e);
	}

	/*
	 * 6. After 어드바이스
	 * - 조인 포인트에서 처리가 완료된 후 실행된다. 
	 */
	
	@After("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public void endLog(JoinPoint jp) {
		log.info("[@After] endlog");
		log.info("[@After] endlog : " + jp.getSignature());
		log.info("[@After] endlog : " + Arrays.toString(jp.getArgs()));
	}
	
	/*
	 * 7. Around 어드바이스
	 * - 조인 포인트 전 후에 실행된다.
	 * 
	 * ProceedingJoinPoint : around 어드바이스에서 사용한다.
	 * 
	 * 스프링 프레임워크가 컨트롤 하고 있는 비지니스 로직 호출을 가로챈다.
	 * 책임이 around 어드바이스로 전가되고 그래서 비지니스 로직에 대한 정보를 around 어드바이스 메소드가 가지고 있어야하고
	 * 그 정보를 스프링 컨테이너가 around 어드바이스 메소드로 넘겨주면 ProcedingJoinPoint 객체로 받아서 around 어드바이스가 컨트롤 시 활용 함
	 */
	
	@Around("execution(* kr.or.ddit.service.IBoardService.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		// 메소드 실행 직전 시간 체킹
		long startTime = System.currentTimeMillis();
		log.info("[@Around] : " + Arrays.toString(pjp.getArgs()));
		
		// 메소드 실행
		Object result = pjp.proceed();
		
		// 메소드 실행 직후 시간 첵킹
		long endTime = System.currentTimeMillis();
		log.info("[@Around]pjpEnd : " + Arrays.toString(pjp.getArgs()));
		
		log.info("[@Around] : " + pjp.getSignature().getName() + "(메소드 실행 시간 : )" + (endTime - startTime));
		
		return result;
	}
	
	/*
	 * 8. 메소드 정보 획득
	 * - @Before 어노테이션 붙은 메서드는 joinPoint라는 매개변수를 통해 실행중인 메서드의 정보를 구할 수 있다.
	 * > @Before 어드바이스에서 테스트를 진행합니다.
	 * 
	 */
	

}
