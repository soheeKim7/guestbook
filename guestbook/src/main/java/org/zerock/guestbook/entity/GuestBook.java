package org.zerock.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 게시글 저장을 위한 엔티티 클래스
 * @author KSH
 * @since 2023.04.20
 * @version 1.0
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor  //JPA에서 기본생성자를 통해서 작업을해서 꼭 필요(값을 가져오려면! 객체를 만들어서(기본생성자를 이용) 하기때문에 꼭필요)
//!!!!!!!setter가 없는대도 값을 읽어올때, 기본생성자만으로 읽어올수 있는 이유!!! 
//     ==>JPA 에서 reflection 이라는 기능!!으로 자동으로 셋팅해서 값을 읽어옴!!!  (java reflection)
//디비에서 읽어와서 세터로 바꿀수 있기때문에! 세터를 보통 안씀!!(디비에서 가져온 값 변경해주면 안돼!!!! 그래서 세터 안씀!!)
//올아큐먼트 => 처음 만들때만 넣어서 만들고! 수정을 안하겠다는 의미!!! 그래서 세터아님! 값 바꾸는게 아님!!
@Getter  //디비에서 가져온 객체를 하나하나 읽어줘야해서!! 게터는 필요해!
@ToString
//@Builder 만약 빌더 쓰려면 기본과,올 생성자 있어야함!
public class GuestBook extends BaseEntity {   //테이블 지정안해줘서!!! GuestBook 을 이용해서 만들어져서 guest_book 이렇게!
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gno;  //첫글자 대문자는 클래스만, 전부 대문자일때 상수값!! 
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=1500,nullable=false)
	private String content;
	
	private String writer;	
	
	
	/**
	 * 수정시 제목수정용 메소드
	 * @param title 제목
	 */	
	public void changeTitle(String title) {
		this.title=title;
	}
	
	public void changeContent(String content) {
		this.content=content;
	}
}
