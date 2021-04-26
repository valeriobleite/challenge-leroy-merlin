package pt.leite.valerio.challengeleroymerlin.mappers

import pt.leite.valerio.data.local.entities.TaskRoom
import pt.leite.valerio.domain.entities.TaskEntity

fun TaskRoom.toEntity() = TaskEntity(
    id = this.id,
    name = this.name,
    description = this.description
)

fun TaskEntity.toRoom() = TaskRoom(
    id = this.id,
    name = this.name,
    description = this.description,
    active = true
)