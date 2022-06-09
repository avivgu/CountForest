resource "aws_elasticache_replication_group" "default"{
  replication_group_id          = "default-1"
  transit_encryption_enabled    = false
}
