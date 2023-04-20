package org.zerock.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook.entity.GuestBook;

import jakarta.transaction.Transactional;


public interface GuestBookRepository extends JpaRepository<GuestBook, Integer>,QuerydslPredicateExecutor<GuestBook> {
		
		@Transactional  //이거 임포트는 두개 뜨는거 아무거나 해도 상관없으
		@Modifying
		@Query("delete from GuestBook gb where gb.gno>300") //이렇게 jpa로 할때는 @Modifying @Transactional 이렇게 2개를 해줘야지!!
		//얘가 select 쿼리로 인식안하고 제대로 인식함!! 즉 select 말고 insert,update,delete 해줄때는 저거 2개를 추가해줘야함!!
//		@Query(value="delete from guest_book where gno>300",nativeQuery = true)
		public void del300();  //삭제는 리턴타입이 int가 안됨! 
		
}
