package com.dokapegroup.dokapebackend.models

import com.dokapegroup.dokapebackend.models.abstract.BaseEntity
import lombok.Builder
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table
@Builder
class DBShort(
    @Id
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    override var id: Long? = null,
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
    var title: String,
    var video: String,
    var description: String,
    @OneToMany(mappedBy = "short")
    var comments: MutableList<DBShortComment> = mutableListOf(),
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: DBUser? = null
) : BaseEntity() {
    companion object {
        private const val SEQ = "short_seq"
    }
}
