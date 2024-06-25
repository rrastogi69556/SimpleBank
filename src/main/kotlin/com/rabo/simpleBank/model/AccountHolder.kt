package com.rabo.simpleBank.model

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import javax.persistence.*

@Data
@Entity
@Table(name = "account_holder")
data class AccountHolder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "account_number")
    val number: String,
    @Column(name = "account_name")
    val holderName: String,
    @Column
    @JsonIgnore
    val password: String,
)
