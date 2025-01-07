package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
}
