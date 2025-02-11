package com.chatapp.chatapp.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "user_chat_room", // Name of the join table
            joinColumns = @JoinColumn(name = "chat_room_id"), // Foreign key in join table referencing ChatRoom
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in join table referencing User
    )
    @JsonIgnore
    private Set<User> participants = new HashSet<>();
}
