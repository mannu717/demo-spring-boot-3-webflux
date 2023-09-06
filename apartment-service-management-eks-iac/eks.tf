resource "aws_eks_cluster" "asms-cluster" {
  name     = var.aws_eks_cluster_name
  role_arn = aws_iam_role.asms-cluster-iam-role.arn

  vpc_config {
    subnet_ids = [var.subnet_id_1, var.subnet_id_2]
  }

  depends_on = [
    aws_iam_role.asms-cluster-iam-role
  ]
}
