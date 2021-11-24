package com.dokapegroup.dokapebackend.models

import com.dokapegroup.dokapebackend.models.abstract.BaseEntity
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table
class DBShortCommentReaction(
    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    override var id: Long? = null,
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
    @OneToOne
    @MapsId
    @JoinColumn(name = "comment_id")
    var comment: DBShortComment
) : BaseEntity() {
    companion object {
        private const val SEQ = "reaction_short_comment_seq"
    }
}
