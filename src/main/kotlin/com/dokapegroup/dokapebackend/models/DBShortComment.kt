package com.dokapegroup.dokapebackend.models

import com.dokapegroup.dokapebackend.models.abstract.BaseEntity
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table
class DBShortComment(
    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    override var id: Long? = null,
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
    var comment: String,
    @OneToOne(mappedBy = "comment", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    var reaction: DBShortCommentReaction,
    @ManyToOne
    @JoinColumn(name = "short_id", nullable = false)
    var short: DBShort
) : BaseEntity() {
    companion object {
        private const val SEQ = "short_comment_seq"
    }
}
