package org.zerock.guestbook.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

//@Entity 이렇게 하면 테이블이 생성됨!
@MappedSuperclass //테이블생성X (부모클래스로 사용)
@EntityListeners(value= {AuditingEntityListener.class})  //여기부분에 엔티티들을 감시해라! 즉 리스너들이라서!! 여기 부분 봐라!
public class BaseEntity {
	//작성시간
	@CreatedDate	//persistence context 에서 p134그림 참고, 관리해서 값을 읽거나 만들때, //객체자체를 생성해서 만든다! 그때의 생성 시간 아냐아냐
	// 그러니까 애초에 save라는 메소드 자체가 수정과 삽입을 같이 하기 때문에 조회해서 먼저 읽어와서 없으면 생성, 있으면 수정!
	// 그래서 @CreatedDate => 없어서 새로 만들때만 생성!!! 그래서 만약 이값이 존재하게 되면, 비교할때  null!!
	// 그래서 밑에 업데이트를 하지말아야!! null로 안바뀜!!!
	// 즉!!!!!!!!!! createdate는 없어서 딱 새로 만들때만 만들어지는거야!!!!!!!!! 
	//그런데 현재 생성을하고 나서 커밋을 다하기때문에, 만약에 이 부트를 이용해서 안하고, 수작업으로 하게되면 생성을 하고 나서 수정하고 커밋을 하게되면
	//의미가 바뀌지만!! 현재의 상태로면 createdate->lastmodifidate 바뀌어도 최초생성된거를 가져옴!(그리고 업데이트는 안하게 만들어야 처음의 시간을 읽게됨)
	@Column(updatable = false) // 그런데, 계속 읽거나 할때 객체를 만드니까, 그 업데이트를 하지말아라! 그래야 최초 생성시간이 유지가 됨!
	private LocalDateTime regDate;  // 그래서 그 생성시간이 여기 regDate변수에 들어가고
	
	//수정시간
	@LastModifiedDate  // 그 데이트를 수정한 시간, 라스트 수정시간이 modDate에 들어감
	private LocalDateTime modDate;
}
