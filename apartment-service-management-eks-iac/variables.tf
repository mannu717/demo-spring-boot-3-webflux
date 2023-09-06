variable "aws_region" {
  type = string
  default = "us-east-1"
}

variable "subnet_id_1" {
  type    = string
  default = "subnet-0dd9ef56468c91daa"
}

variable "subnet_id_2" {
  type    = string
  default = "subnet-089703cd4b4aa0051"
}

variable "aws_eks_cluster_name" {
  type    = string
  default = "asms-cluster"
}

variable "aws_eks_cluster_iam_role" {
  type    = string
  default = "asms-cluster-iam-role"
}

variable "aws_node_group_iam_role" {
  type    = string
  default = "node-group-iam-role"
}

variable "node_group_name" {
  type = string
  default = "asms-cluster-node-group"
}

variable "instance_types" {
  type = string
  default = "t3.medium"
}

variable "node_group_desired_size" {
  type = number
  default = 4
}

variable "node_group_max_size" {
  type = number
  default = 4
}

variable "node_group_min_size" {
  type = number
  default = 1
}