resource "aws_s3_bucket" "data_science" {
  # bucket is not encrypted
  bucket = "bc-aviv-bla-data-science"
  acl    = "private"
  versioning {
    enabled = var.enabled
  }
  force_destroy = true
}
