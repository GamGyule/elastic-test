package com.example.elastictest.model.entity.board

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.ZonedDateTime

@Entity
@DynamicInsert
@DynamicUpdate
class Board(title: String, content: String){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Positive
    var id:Long? = null

    @NotEmpty
    var title: String = title;

    @NotEmpty
    @Column(length = 4000)
    var content: String = content;


    lateinit var created: ZonedDateTime

    @PrePersist
    public fun setCreated(){
        created = ZonedDateTime.now()
    }
}

