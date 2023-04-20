package org.zerock.guestbook.repository;

import java.util.Optional;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.entity.QGuestBook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;


@SpringBootTest //test를 위해 꼭 필요!
public class GuestBookRepositoryTests {
	
	@Autowired  //테스트에서는 생성자주입 안돼! 세터주입이나, 직접주입(필드주입)으로 가능!!
	GuestBookRepository guestBookRepository;
	
	@Test
	public void TestInsert() {
		GuestBook guestBook=new GuestBook(null,"제목","내용3","작성자"); //상속받은 변수들은 기본생성자에 원래 포함 안됨! 넣고 싶으면 따로추가 해서 만들어야함!
		guestBookRepository.save(guestBook); //여기에서 guestBook은 우리가 만든 객체인거고, 이repository를 통해서 jpa가 알아서 그 pesistene context에서 객체 만들어서 막 처리함
	}
	
	@Test
	public void TestModify() {
		GuestBook guestBook=new GuestBook(1,"제목","내용변경함","작성자");
		guestBookRepository.save(guestBook);
	}
	
	@Test
	public void TestInsertDummies() {
		for(int i=1;i<=300;i++) {
			guestBookRepository.save(new GuestBook(null,"제목"+i,"내용"+i,"작성자"+i));
		}		
//		IntStream.rangeClosed(1, 300).forEach(i->guestBookRepository.save(new GuestBook(null,"제목"+i,"내용"+i,"작성자"+i)));		
	}
	
	@Test
	public void TestUpdate() {
		//조회
		Optional<GuestBook> result=guestBookRepository.findById(304);
		if(result.isPresent()) {
			GuestBook guestBook=result.get();
			guestBook.changeContent("내용수정이야~");
			guestBook.changeTitle("제목수정이야~~");
			guestBookRepository.save(guestBook);			
		}		
	}
	
	//Querydsl 사용법
	@Test
	public void testQuery1() {
		//페이지처리를 위한 페이지객체 생성
//		PageRequest pageable=  PageRequest.of(0, 100,Sort.by("gno").descending()); //10개씩 봤을때 첫페이지
		//PageRequest가 Pageable 인터페이스를 구현한거라서 페이저블로 바로 인터페이스로 받을 수 있음!!
		Pageable pageable=  PageRequest.of(0, 10,Sort.by("gno").descending()); //10개씩 봤을때 첫페이지
		//글번호로 내림차순 정렬해서 10개씩 봤을때 첫페이지
		
		////////////////////////////////////Querydsl 사용법//////////////////////////////////////////////////
		//1. Querydsl 라이브러리 추가
		//2. Querydsl 관련 빌드정보들 추가
		//3. 빌드후에 QClass 이용해서 작업
		//4. 추가로 Repository 인터페이스 설정시 Querydsl 관련 추가 상속
		
		
		QGuestBook qGuestBook=QGuestBook.guestBook;
		
		//where 조건을 셋팅하기 위한 객체
		BooleanBuilder builder = new BooleanBuilder();
		
		//원하는 where절 조건 셋팅 (제목 or 작성자 or 내용)
		BooleanExpression expression=qGuestBook.title.contains("9");
		BooleanExpression expression2=qGuestBook.content.contains("8");
		BooleanExpression expression3=qGuestBook.writer.contains("7");
		expression.or(expression2).or(expression3);
		
		//where 조건 셋팅
//		builder.and(expression).or(expression2).or(expression3);
		builder.and(expression);  //최초는 무조건 and로 넣어야 함! 빈거에 넣은거라서 그리고!! 
		
		//실행                                    //query 인터페이스를 통해서 추가된 메소드//오버로딩 됨!!
		Page<GuestBook> list= guestBookRepository.findAll(builder,pageable);  //findAll 쓰기위해서 GuestBookRepository 인터페이스에 추가로 상속 받아야함!!!
		
		list.forEach(x->System.out.println(x));		
	}
	
	@Test
	public void TestDelete() {
		guestBookRepository.deleteById(310);		
	}
	
	//1~299까지만 남겨두는 
	//테스트 프로그램 만들어서 동작시키세요.
	@Test
	public void Test300delete() {
		QGuestBook qGuestBook=QGuestBook.guestBook;		
		BooleanBuilder builder = new BooleanBuilder();		
		BooleanExpression exp=qGuestBook.gno.goe(300);
//		BooleanExpression exp=qGuestBook.gno.between(1, 300);		
		builder.and(exp);		
		Iterable<GuestBook> list=guestBookRepository.findAll(builder);		
		guestBookRepository.deleteAll(list);		
	}
	
	@Test
	public void Test300delete2() {
		guestBookRepository.del300();
	}
	
	
	//콘솔 프로그램???
	
	
}
