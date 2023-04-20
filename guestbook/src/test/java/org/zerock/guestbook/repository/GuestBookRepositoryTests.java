package org.zerock.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.entity.GuestBook;

@SpringBootTest //test를 위해 꼭 필요!
public class GuestBookRepositoryTests {
	
	@Autowired  //테스트에서는 생성자주입 안돼! 세터주입이나, 직접주입(필드주입)으로 가능!!
	GuestBookRepository guestBookRepository;
	
	@Test
	public void testInsert() {
		GuestBook guestBook=new GuestBook(null,"제목","내용3","작성자"); //상속받은 변수들은 기본생성자에 원래 포함 안됨! 넣고 싶으면 따로추가 해서 만들어야함!
		guestBookRepository.save(guestBook); //여기에서 guestBook은 우리가 만든 객체인거고, 이repository를 통해서 jpa가 알아서 그 pesistene context에서 객체 만들어서 막 처리함
	}
	
	@Test
	public void testModify() {
		GuestBook guestBook=new GuestBook(1,"제목","내용변경함","작성자");
		guestBookRepository.save(guestBook);
	}
	
	
}
