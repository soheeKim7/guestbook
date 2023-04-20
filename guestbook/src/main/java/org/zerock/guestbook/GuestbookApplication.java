package org.zerock.guestbook;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 그 데이트들의 생성과 수정을 변하는것을 인지하고 작동할수 있기위해 필요한 어노테이션(BaseEntity 클래스의 Date들의 어노테이션 참고)
//자바엔터티팩토리(객체),엔티티매니저(객체),엔티티트랜잭션(객체) 3개를 만들고, 우리가 만든 엔티티객체를 이용해서, 엔티티매니저에서 관리하는
//persistence cotext라는 메모리 공간에서 (리스너..?) 에서 객체를 만들어서 삽입시에는(트랙잭션시작) 커밋하면 디비에 생성
//조회시에는 트랜잭션 안하고, 만든 객체에 읽어와서 조회됨! 이런것들이 생략되어 있음! 그래서 이런 변화들을 인지할 수 있게 위 어노테이션이 필요
//만약 수정을 하게 되면 '더티체크' 라고 해서  수정하고 커밋햇을때, 디비에 반영이 되서 수정이 됨! 

//엔티티를 감시하겠다! 선언해준겨!! 그래서 ->BaseEntity에 리스너!하라고 어노테이션잇어야! 그 해당 객체를 딱 감시해야해서 서로서로 어노테이션 필요!
public class GuestbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookApplication.class, args);
	}

}
