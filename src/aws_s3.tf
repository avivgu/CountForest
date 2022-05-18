resource "aws_s3_bucket" "important_bucket" {
  bucket        = "${local.resource_prefix.value}-data"
  acl           = "public-read"
  force_destroy = true
}
