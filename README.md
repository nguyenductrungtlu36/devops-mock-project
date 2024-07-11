# Demo Mock Projetc (DevOps)
----
## Overview
This project builds a model to automatically deploy applications to the cloud environment, helping to optimize the product deployment process. When users update changes, those changes will immediately be updated on the Server automatically. This will be very useful for users in shortening deployment time and minimizing errors during application deployment and operation.

![This is an alt text.](/Images/overview-project.jpg "This is a sample image.")

**Technologies used in this project include:**
* Terraform to build infrastructure
* Ansible to install resource packs on the infrastructure
* Amazon Web Services (AWS) to deploy infrastructure built with Terraform
* Jenkins Server for CI-CD deployment
* Docker to build images and host them on DockerHub
* Gitea Server to update commits and webhooks to jenkins
* Kubernetes (K8s) to deploy applications
-----
## Getting Started
**Diagram of work to be done in the project:**
* Use Terraform to build an infrastructure of 3 instances. Then use Ansible to install resource packs inside the instances.

    ![This is an alt text.](/Images/Ansible_Terraform.png "This is a sample image.")

* Instance running Jenkins are in the private subnet, instance used as Bastion Host and running Gitea are in the public subnet. And 1 EKS cluster to deploy pods.

    ![This is an alt text.](/Images/Instances.png "This is a sample image.")

-----
## Build infrastructure using Terraform
**Use the following commands to run build instances**

***Note:*** 
- Stand in the folder: "\mock-project\terraform" to build 2 instances of Jenkins and Bastion Host.
- Stand in the folder: "\mock-project\terraform-eks" to build EKS cluster.

    ```
    terraform init
    terraform plan
    terraform apply
    ```

- When we want to stop all this work, we use the command:

    ```
    terraform destroy
    ```
-----
## Install Docker, Jenkins, Java, and Gitea using Ansible 
To install application packages, we must connect to instances using a key pair. In this demo, I use 2 key pairs for 2 instances.

- Then I stood at the folder: "\mock-project\Ansible\" and run the following command to start the installation:

    ```
    ansible-playbook playbook.yml
    ```

When running the `playbook.yml`, the resource packs will be installed one by one.

To view installed resource pack tasks, visit `Ansible/roles/setup/tasks/`. This here will list the tasks that Ansible will perform on the Instance in AWS.

-----
## Configure Jenkins and Gitea
### Configure Gitea
* I create a repository and connect to the local repository by changing their path.
* I read the file containing the URL with the command:
    
    ```
    cat .git/config/
    ```

* And replace the URL with the path on Gitea.

![This is an alt text.](/Images/url_gitea.png "This is a sample image.")

### Configure Jenkins
* To connect Jenkins with Gitea and DockerHub we need to create credentials.

![This is an alt text.](/Images/credentials.png "This is a sample image.")

* After having the credential, we go to the `Jenkinsfile` file at the path `mock-project/application/` to change DockerHub credential accordingly so they can connect to each other.

* Connect Jenkins and Gitea using webhook to deploy CICD.

![This is an alt text.](/Images/webhook.png "This is a sample image.")

On the Jenkins server, we create `Scan Multibranch Pipeline Triggers` to get the token. 
Then replace the newly created token into the path to Gitea and Jenkins webhook. The path structure is:

`JENKINS_URL/multibranch-webhook-trigger/invoke?token=[Trigger token]`

In there:
  - `JENKINS_URL` here is the path of jenkins.
  - `Trigger token` this is the token created at `Scan Multibranch Pipeline Triggers`.

After the webhook is completed, all commits will automatically deploy to Jenkins Server.

## Kubernetes Deployment
- After building EKS cluster using terraform, we will connect and get data into the EKS cluster on the Server.

- We run the following commands to get the contexts and update the config file:
    ```
    sudo aws eks --region ap-southeast-1 update-kubeconfig --name eks_cluster
    sudo arn:aws:eks:ap-southeast-1:381492155480:cluster/eks_cluster
    sudo kubectl config get-contexts
    ```

- And finally we commit the code and watch them automatically deploy to the K8s cluster.

----

### Đường dẫn tới Jenkins Dashboard (DNS name):

[Jenkins Dashboard](http://app-lb-741458399.ap-southeast-1.elb.amazonaws.com/jenkins/).

----

***Deploying work automatically will help minimize errors and save time for users. I hope this project will help people better understand a CICD stream***


