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
    }
}