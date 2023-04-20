package org.zerock.guestbook.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.GuestBook;

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
		IntStream.rangeClosed(1, 300).forEach(i->guestBookRepository.save(new GuestBook(null,"제목"+i,"내용"+i,"작성자"+i)));		
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
		Pageable pageable=  PageRequest.of(0, 100,Sort.by("gno").descending()); //10개씩 봤을때 첫페이지
		//글번호로 내림차순 정렬해서 10개씩 봤을때 첫페이지
		
	}
	
	
	
	
	
}
