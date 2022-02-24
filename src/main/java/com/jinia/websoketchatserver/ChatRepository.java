package com.jinia.websoketchatserver;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String > {

    // flux : 데이터 흐름, stream 개념, response를 유지하면서 데이터 계속 흘려보내기
    @Tailable // 커서 안닫고 유지
    @Query("{sender: ?0, receiver: ?1}")
    Flux<Chat> mFindBySender(String sender, String receiver);

    @Tailable
    @Query("{ roomNum: ?0 }")
    Flux<Chat> mFindByRoomNum(Integer roomNum);
}
