# Demo Mock Projetc
----
# Overview
This project builds a model to automatically deploy applications to the cloud environment, helping to optimize the product deployment process. When users update changes, those changes will immediately be updated on the Server automatically. This will be very useful for users in shortening deployment time and minimizing errors during application deployment and operation.

![This is an alt text.](/Images/overview-project.jpg "This is a sample image.")

Technologies used in this project include:
* Terraform to build infrastructure
* Ansible to install resource packs on the infrastructure
* Amazon Web Services (AWS) to deploy infrastructure built with Terraform
* Jenkins Server for CI-CD deployment
* Docker to build images and host them on DockerHub
* Gitea Server to update commits and webhooks to jenkins
* Kubernetes (K8s) to deploy applications
-----
# Getting Started
Diagram of work to be done in the project:
* Use Terraform to build an infrastructure of 3 instances. Then use Ansible to install resource packs inside the instances

![This is an alt text.](/Images/Ansible_Terraform.jpg "This is a sample image.")










