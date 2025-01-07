package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
	
}
