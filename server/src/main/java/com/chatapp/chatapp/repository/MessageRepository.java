package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.entity.ChatRoom;
import com.chatapp.chatapp.entity.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	List<Message> findByChatRoom(ChatRoom chatRoom);
}
