output "Jenkins_URL" {
  value = "http://${aws_instance.jenkins_ec2.public_ip}:8080"
}