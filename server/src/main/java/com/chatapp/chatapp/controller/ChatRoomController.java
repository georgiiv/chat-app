package com.chatapp.chatapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.chatapp.entity.ChatRoom;
import com.chatapp.chatapp.entity.User;
import com.chatapp.chatapp.repository.ChatRoomRepository;
import com.chatapp.chatapp.repository.UserRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
public class ChatRoomController {
	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private UserRepository userRepository;

	@Getter
	@Setter
	@NoArgsConstructor
	public class UserDTO {
		private Integer id;
		private String email;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public class ChatRoomDTO {
		private Integer id;
		private List<UserDTO> participants;
	}

	@GetMapping("/room")
	public List<ChatRoomDTO> getRooms() {
		List<ChatRoom> chatRooms = chatRoomRepository.findAll();
		List<ChatRoomDTO> dtos = new ArrayList<>();

		for (ChatRoom chatRoom : chatRooms) {
			ChatRoomDTO dto = new ChatRoomDTO();
			dto.setId(chatRoom.getId());

			List<UserDTO> participantDtos = new ArrayList<>();
			if (chatRoom.getParticipants() != null) {
				for (User participant : chatRoom.getParticipants()) {
					UserDTO userDto = new UserDTO();
					userDto.setId(participant.getId());
					userDto.setEmail(participant.getEmail());
					participantDtos.add(userDto);
				}
			}
			dto.setParticipants(participantDtos);
			dtos.add(dto);
		}
		return dtos;
	}

	// @GetMapping("/room")
	// public List<ChatRoomDTO> getRooms() {
	// 	List<ChatRoom> chatRooms = chatRoomRepository.findAll();
	// 	List<ChatRoomDTO> chatRoomDTO = new ArrayList<>();

	// 	for (ChatRoom chatRoom : chatRooms) {
	// 		ChatRoomDTO dto = new ChatRoomDTO();
	// 		dto.setId(chatRoom.getId());
	// 		dto.setParticipants(chatRoom.getParticipants());
	// 		chatRoomDTO.add(dto);
	// 	}
	// 	return chatRoomDTO;
	// }

	@PostMapping("/room")
	public ResponseEntity<String> createRoom() {
		ChatRoom chatRoom = new ChatRoom();
		this.chatRoomRepository.saveAndFlush(chatRoom);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/room/{roomId}/user")
	public ResponseEntity<String> addUser(@PathVariable Integer roomId, @RequestBody Map<String, Integer> request) {
		ChatRoom chatRoom = this.chatRoomRepository.findById(roomId).get();
		User user = userRepository.findById(request.get("userId")).get();

		Set<User> participants = chatRoom.getParticipants();
		if (participants == null) {
			participants = new HashSet<>();
			chatRoom.setParticipants(participants);
		}

		participants.add(user);
		chatRoomRepository.save(chatRoom);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/room/{roomId}/message")
	public ResponseEntity<String> addMessage(@PathVariable Integer roomId, @RequestBody Map<String, Integer> request) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
