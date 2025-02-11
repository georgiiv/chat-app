package com.chatapp.chatapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "messages")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User sender;

	@ManyToOne
	@JoinColumn(nullable = false, name = "chat_room_id")
	private ChatRoom chatRoom;

	@Column(columnDefinition = "TEXT")
	private String content;
}
