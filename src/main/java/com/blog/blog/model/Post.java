package com.blog.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    private String title;

    private String subTitle;

    private String article;

    private LocalDateTime dtPost;

    private LocalDateTime dtEdit;

    @ManyToOne
    private User userPost;


}
