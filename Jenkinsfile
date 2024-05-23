pipeline {
    agent any
    stages {
        stage('Jenkins Products') {
            steps {
                echo 'Products Service'
            }
        }
        stage('Build Interface') { 
            steps {
                build job: 'store.products', wait: true
            }
        }
        stage('Build Redis') {
            steps {
                build job: 'store.redis', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }  
        stage('Build Image'){
            steps {
                script {
                    products = docker.build("alemagno10/products:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }    
        stage('Push Image'){
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        products.push("${env.BUILD_ID}")
                        products.push("latest")
                    }
                }
            }
        }
        stage('Deploy on k8s') {
            steps {
                withCredentials([ string(credentialsId: 'minikube-credentials', variable: 'api_token') ]) {
                    sh 'kubectl --token $api_token --server https://host.docker.internal:51246  --insecure-skip-tls-verify=true apply -f ./k8s/deployment.yaml '
                    sh 'kubectl --token $api_token --server https://host.docker.internal:51246  --insecure-skip-tls-verify=true apply -f ./k8s/service.yaml '
                }
            }
        }
    }
}