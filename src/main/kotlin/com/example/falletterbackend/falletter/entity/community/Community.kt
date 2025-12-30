package com.example.falletterbackend.falletter.entity.community

import com.example.falletterbackend.common.entity.EntityBase
import com.example.falletterbackend.falletter.entity.comment.Comment
import com.example.falletterbackend.falletter.entity.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tbl_community")
class Community(
    @Column(name = "title", columnDefinition = "VARCHAR(128)", nullable = false)
    var title: String,

    @Column(name = "content", columnDefinition = "VARCHAR(512)", nullable = false)
    var content: String,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false,

    @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments : MutableList<Comment> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    val author : User
) : EntityBase() {
    fun update(title: String, content: String){
        this.title = title
        this.content = content
    }

    fun delete() {
        this.isDeleted = true
    }
}
